package com.eightb.smartfridge.service;

import com.eightb.smartfridge.model.FoodItem;
import com.eightb.smartfridge.model.twilio.WebhookRequest;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TwilioServiceImpl implements TwilioService {
    @Autowired
    FoodItemService foodItemService;

    @Override
    public String handleIncomingMessage(WebhookRequest request) {
        switch (request.getBody()) {
            case "items":  //text user all items in fridge
                List<FoodItem> allItems = foodItemService.getAllFoodItems();
                return buildStringToSMSXML(foodItemService.formatListFoodItemIntoString(allItems));
            case "low":  //text user all low qty items in fridge w/ restock
                List<FoodItem> allLowItems = foodItemService.getAllLowQuantityItems();
                return buildStringToSMSXML(foodItemService.formatListFoodItemIntoStringWithRestock(allLowItems));
            case "stores":  // text user nearby grocery stores/retailers
                return buildStringToSMSXML(BingSearch.searchNearbyGroceryStores("UBC"));
            default:
                String infoMsg = "Valid Text Commands Are:\n" +
                        "1. items (to get all items in your fridge)" + "\n" +
                        "2. low (to get all items you're running low on)" + "\n" +
                        "3. stores (to get the nearest grocery stores)" + "\n";
                return buildStringToSMSXML(infoMsg);
        }
    }

    private String buildStringToSMSXML (String str) {
        Body body = new Body
                .Builder(str)
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
