package com.jxau.m1.factory;

import com.jxau.m1.model.message.BaseMessage;
import com.jxau.m1.model.message.ImageMessage;
import com.jxau.m1.model.message.TextMessage;

import java.util.Map;

public class MessageFactory {
    public static BaseMessage createMessage(String type, Map requestMap,String mediaid) {
        if ("image".equals(type)) {
            return new ImageMessage(requestMap, mediaid);
        }else{
            return null;
        }
    }
}
