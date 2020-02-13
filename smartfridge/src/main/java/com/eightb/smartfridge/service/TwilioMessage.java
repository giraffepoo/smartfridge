package com.eightb.smartfridge.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioMessage {
    private static String ACCOUNT_SID = "ACd435af01060ecb1c322617feae2f8ca4";
    private static String AUTH_TOKEN = "f9129f571515b4204b46f568389dc906";
    private static String FROM_PHONE_NUMBER = "+12034576258";
//    private static String TO_PHONE_NUMBER = "+14039038103";
    private static String TO_PHONE_NUMBER = "+17787980645";

    public static void sendMessage(String messageText) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(TO_PHONE_NUMBER),
                new PhoneNumber(FROM_PHONE_NUMBER), messageText).create();

        System.out.println("Twilio message sent with ID: " + message.getSid());
    }
}
