package com.jxau.m1;

import com.jxau.m1.model.menu.AbstractButton;
import com.jxau.m1.model.menu.Menu;
import com.jxau.m1.model.message.ImageMessage;
import com.jxau.m1.model.message.VoiceMessage;
import com.jxau.m1.model.message.label.Image;
import com.jxau.m1.model.message.label.Voice;
import com.thoughtworks.xstream.XStream;
import net.sf.json.JSONObject;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class M1ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testImageMessage() {
        Map<String, String> map = new HashMap<>();
        map.put("FromUserName", "123");
        map.put("ToUserName", "345");
        String mediaId = new String("123456");
        ImageMessage imageMessage = new ImageMessage(map,mediaId);
        imageMessage.setImage(new Image(mediaId));
        System.out.println(imageMessage.getImage().getMediaId());

        XStream xStream = new XStream();
        xStream.processAnnotations(ImageMessage.class);
        System.out.println(xStream.toXML(imageMessage));
    }

    @Test
    public void testVoiceMessage() {
        Map<String, String> map = new HashMap<>();
        map.put("FromUserName", "123");
        map.put("ToUserName", "345");
        String mediaId = new String("123456");
        VoiceMessage voiceMessage = new VoiceMessage(map);
        voiceMessage.setVoice(new Voice(mediaId));


        XStream xStream = new XStream();
        xStream.processAnnotations(VoiceMessage.class);
        System.out.println(xStream.toXML(voiceMessage));
    }

    @Test
    public void testJson() throws JSONException {
        Menu menu = new Menu();
        menu.getButtons().add(new AbstractButton("hhh"));
        JSONObject jsonObject = JSONObject.fromObject(menu);
        System.out.println(jsonObject);

    }

}
