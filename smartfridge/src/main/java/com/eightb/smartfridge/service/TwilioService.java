package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.twilio.WebhookRequest;
import org.springframework.stereotype.Service;

@Service
public interface TwilioService {
    String handleIncomingMessage(WebhookRequest request);
}
