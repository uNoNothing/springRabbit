package com.spring.rabbit.model;

public class Message {

    private String text;
    private String username;
    private String key;

    public Message() {
    }

    public Message(String text, String username, String key) {
	this();
	this.text = text;
	this.username = username;
	this.key = key;

    }

    public String getText() {
	return text;
    }

    public String getUsername() {
	return username;
    }

    public String getKey() {
	return key;
    }

    public void setText(String text) {
	this.text = text;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public void setKey(String key) {
	this.key = key;
    }

    @Override
    public String toString() {
	return "Message [text=" + text + ", username=" + username + ", key=" + key + "]";
    }

}
