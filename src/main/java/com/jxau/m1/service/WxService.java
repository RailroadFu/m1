package com.jxau.m1.service;

import com.jxau.m1.model.User;
import com.jxau.m1.model.message.*;
import com.jxau.m1.utils.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class WxService {

    /**
     * 校验是否为微信服务端发来的信息
     * 
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     * @throws NoSuchAlgorithmException
     */

    @Autowired
    private MessageService messageService;

    public static boolean check(String timestamp,String nonce,String signature) throws NoSuchAlgorithmException {
        //将三个东西排序
        String[] strs = new String[]{Constants.TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        //拼成一个字符串
        String str = strs[0] + strs[1] + strs[2];
        //进行加密
        String mysig = sha1(str);


        return mysig.equals(signature);
    }

    public static String sha1(String str) throws NoSuchAlgorithmException {
        if (null == str || str.length() == 0){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<String, String> parseRequest(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();

        Document document = reader.read(inputStream);

        Element root = document.getRootElement();

        List<Element> elements = root.elements();

        Map<String, String> map = new HashMap<>();
        for (Element e : elements) {
            map.put(e.getName(), e.getStringValue());
        }
        return map;
    }

    public  String getResponseXml(Map<String, String> requestMap) {
        BaseMessage baseMessage = null;
        String msgType = requestMap.get("MsgType");
        Map<String, BaseMessage> map = messageService.getKeywordMap(requestMap);
        Map<String, BaseMessage> map2 = messageService.getEventMap(requestMap);
        Map<String, BaseMessage> map3 = messageService.getUnknownMap(requestMap);
        if(msgType.equals("text") && map!= null && map.containsKey(requestMap.get("Content"))){
            baseMessage = map.get((String)requestMap.get("Content"));
        }else if(msgType.equals("event") && map2!=null && ((String)(requestMap.get("Event"))).equals("subscribe")){
            baseMessage = map2.get("subscribe");
        }else{
            if(map3.containsKey((String)msgType)){
                baseMessage = map3.get(msgType);
            }else{
                baseMessage = new TextMessage(requestMap, "success");
            }
        }


/*

*/




        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        xStream.processAnnotations(ImageMessage.class);
        xStream.processAnnotations(VoiceMessage.class);
        xStream.processAnnotations(VideoMessage.class);
        xStream.processAnnotations(ShortVideoMessage.class);
        xStream.processAnnotations(VoiceMessage.class);
        xStream.processAnnotations(LocationMessage.class);
        xStream.processAnnotations(LinkMessage.class);
        String xml = xStream.toXML(baseMessage);
        System.out.println(xml);
        return  xml;
    }


    public static String getAccessToken(String appId,String appSecret) throws IOException {
        String defaultAccessURL = PropertiesUtil.accessString("access_token");
        String accessURL = defaultAccessURL.replace("APPID", appId).replace("APPSECRET", appSecret);
        System.out.println(accessURL);
        //url开链接
        URL url = new URL(accessURL);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        //读取微信后台返回的含access_token的json包
        InputStream is = urlConnection.getInputStream();
        byte[] bytes = new byte[1024];
        StringBuilder sb = new StringBuilder();
        int len;
        while((len = is.read(bytes))!=-1){
            sb.append(new String(bytes,0,len));
        }
        //处理json包，得到access_token
        String access_json = sb.toString();
        JSONObject jsonObject = new JSONObject(access_json);
        System.out.println(jsonObject.get("access_token").toString());
        return (String) jsonObject.get("access_token");
    }

    public static void main(String[] args) {

    }
}
