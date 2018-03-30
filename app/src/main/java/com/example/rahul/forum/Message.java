package com.example.rahul.forum;

/**
 * Created by Rahul on 3/28/2018.
 */

public class Message {

    private String Question;
    private Long ftime;


    public Message(String content,Long ftime) {
        this.ftime = ftime;

         this.Question = content;
    }

    public Message() {
    }
    public void setfTime(Long ftime) {
        this.ftime = ftime;
    }

    public void setQuestion(String content) {
        this.Question = content;
    }

    public String getQuestion() {
        return Question;
    }

    public Long getfTime() {
        return ftime;
    }
}




