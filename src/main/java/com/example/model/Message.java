package com.example.model;

public class Message {
    private String id;
    private String content;
    private String topic;

    public Message() {
    }

    public Message(String id, String content, String topic) {
        this.id = id;
        this.content = content;
        this.topic = topic;
    }

    public Message(String content, String topic) {
        this.content = content;
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}