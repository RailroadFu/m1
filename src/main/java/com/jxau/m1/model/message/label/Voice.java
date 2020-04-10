package com.jxau.m1.model.message.label;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Voice {
    @XStreamAlias("MediaId")
    private String mediaId;

    public Voice(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
