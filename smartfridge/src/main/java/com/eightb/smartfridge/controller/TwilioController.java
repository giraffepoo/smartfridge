package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.model.twilio.WebhookRequest;

import com.eightb.smartfridge.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/text")
public class TwilioController {
    @Autowired
    TwilioService twilioService;

    @PostMapping(value = "/incoming-message", produces = {"application/xml"})
    public String incomingMessage(@RequestBody WebhookRequest request) {
//        return twilioService.handleIncomingMessage(request);
        WebhookRequest whr = new WebhookRequest();
        whr.setBody("WOOWOWOWOW");

        return twilioService.handleIncomingMessage(whr);
    }
}
