package com.eightb.smartfridge.controller;

import com.eightb.smartfridge.service.TwilioMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/text")
public class TwilioController {
    @PostMapping("/incoming-message")
    public void incomingMessage(@RequestBody String text) {
        TwilioMessage.sendSMSMessage("You texted: " + text);
    }
}
