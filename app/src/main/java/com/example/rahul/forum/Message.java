package com.example.rahul.forum;

/**
 * Created by Rahul on 3/28/2018.
 */

public class Message {

    private String Question;


    public Message(String content) {

         this.Question = content;
    }

    public Message() {
    }

    public void setQuestion(String content) {
        this.Question = content;
    }

    public String getQuestion() {
        return Question;
    }
}




