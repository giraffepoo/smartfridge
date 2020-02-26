package com.eightb.smartfridge.service;

import org.junit.jupiter.api.Test;

class TwilioMessageTest {
    @Test
    void SendText() {
        TwilioMessage.sendSMSMessage("test message from backend to you :)");
    }

    @Test
    void SendTextMMS() {
        TwilioMessage.sendMMSMessge("Test single recipe with image MMS text", "https://www.edamam.com/web-img/e42/e42f9119813e890af34c259785ae1cfb.jpg");
    }

}
