package com.dec.chatapp.model;

public class ChatMessage {

    private Integer messageid;
    private String usernameinput;
    private String messageinput;

    public ChatMessage(Integer messageid, String usernameinput, String messageinput) {
        this.messageid = messageid;
        this.usernameinput = usernameinput;
        this.messageinput = messageinput;
    }

    public String getUsernameInput() {
        return usernameinput;
    }

    public void setUsernameInput(String usernameInput) {
        this.usernameinput = usernameInput;
    }

    public String getMessageInput() {
        return messageinput;
    }

    public void setMessageInput(String messageInput) {
        this.messageinput = messageInput;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }
}
