package com.example.rahul.forum;

/**
 * Created by Rahul on 3/30/2018.
 */

public class fcm {

    private String Reply;


    public fcm(String Reply) {

        this.Reply = Reply;
    }

    public fcm() {
    }

    public String setReply(String Reply) {
        this.Reply = Reply;
        return Reply;
    }

    public String getReply() {
        return Reply;
    }
}
