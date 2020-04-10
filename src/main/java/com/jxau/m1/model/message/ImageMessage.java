package com.jxau.m1.model.message;

import com.jxau.m1.model.message.label.Image;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    @XStreamAlias("Image")
    private Image image;

    public ImageMessage(Map<String, String> requestMap,String mediaId) {
        super(requestMap);
        this.setMsgType("image");
        this.setImage(new Image(mediaId));
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }



}
