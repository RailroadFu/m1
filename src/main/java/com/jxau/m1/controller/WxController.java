package com.jxau.m1.controller;

import com.jxau.m1.dao.SucaiDao;
import com.jxau.m1.model.Sucai;
import com.jxau.m1.service.MessageService;
import com.jxau.m1.service.SucaiService;
import com.jxau.m1.service.WxService;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Controller
public class WxController {
    @Autowired
    private SucaiService sucaiService;
    @Autowired
    private WxService wxService;
    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET,value = "/check1")
    public void check2(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, IOException {

        //微信服务端发来的四个信息
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //校验签名，如果确认是微信服务端发来的信息，则给微信服务端返回echostr的值
        if(WxService.check(timestamp,nonce,signature)){
            PrintWriter out = response.getWriter();
            out.println(echostr);
            out.flush();
            out.close();
            System.out.println("接入成功");
        }else{
            System.out.println("接入失败");
        }
    }
    @RequestMapping(method = RequestMethod.POST,value = "/check1")
    public void check1(HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException {
        /*//获取request的输入流
        InputStream inputStream = request.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder stringBuilder = new StringBuilder();
        //将request输入流中的信息取出
        while((len=inputStream.read(bytes))!=-1){
            stringBuilder.append(new String(bytes, 0, len));
        }
        System.out.println(stringBuilder.toString());*/


        //第一步，将XML消息转换成可读的对象。
        // 利用面向的对象的设计方式，将所有操作放在WxService的parseRequest的方法内进行操作
        Map<String, String> requestMap = WxService.parseRequest(request.getInputStream());
        System.out.println(requestMap);

        //第二步，根据消息回复，得到需要回复的XML消息
        //同样是利用面向对象的设计方式，将所有的操作放在WxServcie的getResponseXML方法中
        String respXml = wxService.getResponseXml(requestMap);

        //第三部，处理需要回复的消息
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.flush();
        out.close();
    }

    @RequestMapping("/test")
    public void test(String type) throws IOException {
        //messageService.setKeywordMap("123456",1);
        //messageService.setEventMap("subscribe", 1);
        messageService.setUnknownMap(type,1);
    }
}
