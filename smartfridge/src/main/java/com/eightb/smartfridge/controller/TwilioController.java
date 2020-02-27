package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/text")
public class TwilioController {
    @Autowired
    TwilioService twilioService;

    @PostMapping(value = "/incoming-message", produces = {"application/xml"})
    public String incomingMessage(@RequestParam("Body") String body) {
        return twilioService.handleIncomingMessage(body);
    }
}
