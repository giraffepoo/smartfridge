package com.eightb.smartfridge.service;

import org.springframework.stereotype.Service;

@Service
public interface TwilioService {
    String handleIncomingMessage(String body);
}
