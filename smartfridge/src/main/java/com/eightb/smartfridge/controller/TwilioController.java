package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.service.TwilioMessage;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/text")
public class TwilioController {

    @PostMapping(value = "/incoming-message", produces = {"application/xml"})
    public String incomingMessage(@RequestBody String text) {
        TwilioMessage.sendSMSMessage("You texted: " + text);
        Body body = new Body
                .Builder("The Robots are coming! Head for the hills!")
                .build();
        Message sms = new Message
                .Builder()
                .body(body)
                .build();
        MessagingResponse twiml = new MessagingResponse
                .Builder()
                .message(sms)
                .build();
        return twiml.toXml();
    }
}
