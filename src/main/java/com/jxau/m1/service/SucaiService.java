package com.jxau.m1.service;

import com.jxau.m1.dao.KeywordMapDao;
import com.jxau.m1.dao.SucaiDao;
import com.jxau.m1.model.KeywordMap;
import com.jxau.m1.model.Sucai;
import com.jxau.m1.utils.PropertiesUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class SucaiService {
    @Autowired
    private  SucaiDao sucaiDao;
    @Autowired
    private static KeywordMapDao keywordMapDao;
    public static List<String> upload(String type,String token,String path) throws IOException {
        /*byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while((inputStream.read(bytes)!=-1)){
            sb.append(new String(bytes));
        }
        System.out.println(sb.toString());*/
        File file = new File(path);
        String defaultUploadURL = PropertiesUtil.uploadString("upload");
        String uploadURL = defaultUploadURL.replace("ACCESS_TOKEN", token).replace("TYPE", type);
        //获取URL，并开链接
        URL url = new URL(uploadURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置连接信息
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);



        //设置头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "utf8");
        //数据边界
        String boundary = "-----"+System.currentTimeMillis();
        conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);

        InputStream is = new FileInputStream(file);
        OutputStream out = conn.getOutputStream();
        //头部信息
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        out.write(sb.toString().getBytes());
        //System.out.println(sb.toString());
        //文件内容
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            out.write(b,0,len);
        }

        //尾部
        String foot = "\r\n--" + boundary + "\r\n";
        out.write(foot.getBytes());



        InputStream is2 = conn.getInputStream();
        StringBuilder resp = new StringBuilder();
        while ((len = is2.read(b)) != -1) {
            resp.append(new String(b, 0, len));
        }

        String media_ID_JSON = resp.toString();
        JSONObject jsonObject = new JSONObject(media_ID_JSON);


        List<String> list = new ArrayList<>();
        list.add((String) jsonObject.get("media_id"));
        list.add((String) jsonObject.get("type"));
        return list;
    }

    //此时自动注入没有发生
    /*public static void main(String[] args) throws IOException {
        String access_token = WxService.getAccessToken("wx64e4b89c69afecb6","a3d9ba5be52c4bccb8ec4f757680eaa7");
        List<String> sucaiInfo = upload("image",access_token,"C:\\Users\\More" +
                "Zhuo\\Desktop\\croped-image.jpg");
        sucaiDao.insert(new Sucai(sucaiInfo.get(0), sucaiInfo.get(1), 1));
    }*/

    public void test() throws IOException {
        String access_token = WxService.getAccessToken("wx64e4b89c69afecb6","a3d9ba5be52c4bccb8ec4f757680eaa7");
        List<String> sucaiInfo = SucaiService.upload("image",access_token,"C:\\Users\\More" +
                "Zhuo\\Desktop\\croped-image.jpg");
        //sucaiDao.insert(new Sucai(sucaiInfo.get(0), sucaiInfo.get(1), 1));
    }
}
