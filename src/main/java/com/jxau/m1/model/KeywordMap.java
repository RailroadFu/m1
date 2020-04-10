package com.jxau.m1.model;

public class KeywordMap {
    private int id;
    private String keyword;
    private String type;
    private String mediaid;
    private int userid;


    public KeywordMap() {

    }

    public KeywordMap(int id, String keyword, String type, String mediaid, int userid) {
        this.id = id;
        this.keyword = keyword;
        this.type = type;
        this.mediaid = mediaid;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }
}
