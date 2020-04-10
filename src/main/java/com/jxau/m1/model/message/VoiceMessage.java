package com.jxau.m1.model.message;

import com.jxau.m1.model.message.label.Voice;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage{
    @XStreamAlias("Voice")
    private Voice voice;
    public VoiceMessage(Map<String, String> requestMap) {
        super(requestMap);
        this.setMsgType("voice");
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
