package com.jxau.m1.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jxau.m1.dao.EventMapDao;
import com.jxau.m1.dao.KeywordMapDao;
import com.jxau.m1.dao.SucaiDao;
import com.jxau.m1.dao.UnknownMapDao;
import com.jxau.m1.factory.MessageFactory;
import com.jxau.m1.model.*;
import com.jxau.m1.model.message.BaseMessage;
import com.jxau.m1.model.message.ImageMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private KeywordMapDao keywordMapDao;
    @Autowired
    private SucaiDao sucaiDao;
    @Autowired
    private EventMapDao eventMapDao;
    @Autowired
    private UnknownMapDao unknownMapDao;
    public static BaseMessage dealTextMessage(Map<String, String> requestMap) {
        return null;
    }

    public static BaseMessage dealImageMessage(Map<String, String> requestMap) {
        return null;
    }

    public static BaseMessage dealVoiceMessage(Map<String, String> requestMap) {
        return null;
    }

    public static BaseMessage dealVideoMessage(Map<String, String> requestMap) {
        return null;
    }

    public static BaseMessage dealShortVideoMessage(Map<String, String> requestMap) {
        return null;
    }

    public static BaseMessage dealLocationMessage(Map<String, String> requestMap) {
        return null;
    }

    public static BaseMessage dealLinkMessage(Map<String, String> requestMap) {
        return null;
    }



    public  Map<String, BaseMessage> getKeywordMap(Map requestMap) {
        List<KeywordMap> list = keywordMapDao.selectByUserid(1);
        Map<String, BaseMessage> map = new HashMap<>();
        BaseMessage baseMessage;
        for (KeywordMap keywordMap : list) {
            baseMessage = MessageFactory.createMessage(keywordMap.getType(), requestMap,keywordMap.getMediaid());
            map.put(keywordMap.getKeyword(), baseMessage);
        }
        return map;
    }

    public  Map<String, BaseMessage> getEventMap(Map<String, String> requestMap) {

        List<EventMap> list = eventMapDao.selectByUserid(1);
        Map<String, BaseMessage> map = new HashMap<>();
        BaseMessage baseMessage;
        for (EventMap eventMap : list) {
            baseMessage = MessageFactory.createMessage(eventMap.getType(), requestMap,eventMap.getMediaid());
            map.put(eventMap.getEventType(), baseMessage);
        }
        return map;
    }
    public Map<String, BaseMessage> getUnknownMap(Map<String, String> requestMap) {
        List<UnknownMap> list = unknownMapDao.selectByUserid(1);
        Map<String, BaseMessage> map = new HashMap<>();
        BaseMessage baseMessage;
        for (UnknownMap unknownMap : list) {
            baseMessage = MessageFactory.createMessage(unknownMap.getType(), requestMap,unknownMap.getMediaid());
            map.put(unknownMap.getMsgtype(), baseMessage);
        }
        return map;
    }

    public void setKeywordMap(String keyword,int id) {
        KeywordMap keywordMap = new KeywordMap();
        Sucai sucai = sucaiDao.selectById(id);

        System.out.println(sucai);
        keywordMap.setKeyword(keyword);
        keywordMap.setMediaid(sucai.getMediaid());
        keywordMap.setType(sucai.getType());
        keywordMap.setUserid(sucai.getUserid());
        keywordMapDao.insert(keywordMap);
    }

    public void setEventMap(String eventType, int id) {
        EventMap eventMap = new EventMap();
        Sucai sucai = sucaiDao.selectById(id);

        eventMap.setEventType(eventType);
        eventMap.setMediaid(sucai.getMediaid());
        eventMap.setType(sucai.getType());
        eventMap.setUserid(sucai.getUserid());

        eventMapDao.insert(eventMap);
    }

    public void setUnknownMap(String msgType, int id) {
        UnknownMap unknownMap = new UnknownMap();
        Sucai sucai = sucaiDao.selectById(id);

        unknownMap.setMsgtype(msgType);
        unknownMap.setMediaid(sucai.getMediaid());
        unknownMap.setType(sucai.getType());
        unknownMap.setUserid(sucai.getUserid());

        unknownMapDao.insert(unknownMap);
    }


}
