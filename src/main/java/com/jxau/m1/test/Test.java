package com.jxau.m1.test;

import com.sun.org.apache.xml.internal.resolver.readers.SAXCatalogReader;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {
    public static final String URLS = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String APPID = "wx64e4b89c69afecb6";
    public static  final  String APPSECRET = "a3d9ba5be52c4bccb8ec4f757680eaa7";
    public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
    public static final String ACCESS_TOKEN = "31_xoiPaqRsH7hydk4fJZ0Dp9P1AIXBbT0htCP6co6E-mTfZ60Im6ImS5l_FLEfuw1KsGIxDNb7ryAKxBobnbNwc8KI9bPueNkVnly4yflY4mwyulgB4sUc7tWt4vXL6DEYwNeVNqTwSXmLlebEWADbAHACGJ";
    public static void main(String[] args) throws IOException {
           String file = "C:\\Users\\MoreZhuo\\Desktop\\croped-image.jpg";
        String result = upload(file, "image");
        System.out.println(result);
    }
    public static String getAccessToken(String url) throws IOException {
        url = url.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        URL urlObject = new URL(url);
        HttpsURLConnection urlConnection = (HttpsURLConnection)urlObject.openConnection();
       // urlConnection.setDoOutput(true);
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((len=inputStream.read(bytes))!=-1){
            sb.append(new String(bytes, 0, len));
        }

        return sb.toString();
    }
    public static String upload(String path, String type) throws IOException {
        File file = new File(path);
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("TYPE", type);
        URL urlObject = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection)urlObject.openConnection();
        //设置连接信息
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        //设置头信息
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Charset", "utf8");
        //数据边界
        String boundary = "-----"+System.currentTimeMillis();
        connection.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

        InputStream is = new FileInputStream(file);
        OutputStream out = connection.getOutputStream();
        //头部信息
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        out.write(sb.toString().getBytes());
        System.out.println(sb.toString());
        //文件内容
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            out.write(b,0,len);
        }

        //尾部
        String foot = "\r\n--" + boundary + "\r\n";
        out.write(foot.getBytes());



        //读取数据
        InputStream is2 = connection.getInputStream();
        StringBuilder resp = new StringBuilder();
        while ((len = is2.read(b)) != -1) {
            resp.append(new String(b, 0, len));
        }
         return resp.toString();
    }
}
