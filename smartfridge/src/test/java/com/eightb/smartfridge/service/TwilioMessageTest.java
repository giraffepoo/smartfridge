package com.eightb.smartfridge.service;

import org.junit.jupiter.api.Test;

class TwilioMessageTest {
    @Test
    void SendText() {
        TwilioMessage.sendMessage("test message from backend to you :)");
    }
}
