package com.fmsia2.quiz.service.entities;

public class Message {
    private String message;

    public Message (){
        this.message = "message not provided";
    }
    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
