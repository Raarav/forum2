package com.example.rahul.forum;

/**
 * Created by Rahul on 3/30/2018.
 */

public class fcm {

    private String Reply;
    private Long time;


    public fcm(String Reply,Long time) {
        this.time = time;
        this.Reply = Reply;
    }

    public fcm() {
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String setReply(String Reply) {
        this.Reply = Reply;
        return Reply;
    }

    public String getReply() {
        return Reply;
    }

    public Long getTime() {
        return time;
    }
}
