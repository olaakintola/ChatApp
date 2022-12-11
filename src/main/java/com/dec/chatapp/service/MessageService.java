package com.dec.chatapp.service;

import com.dec.chatapp.mapper.ChatMessageMapper;
import com.dec.chatapp.model.ChatForm;
import com.dec.chatapp.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

//    String message;
    List<String> badWordsList = new ArrayList<>();

    private final ChatMessageMapper chatMessageMapper;

    public MessageService(ChatMessageMapper chatMessageMapper){

        this.chatMessageMapper = chatMessageMapper;
    }

    public List<ChatMessage> getChatHistory() {
        return chatMessageMapper.getChatHistory();
    }

//    public String uppercase(){
//        return this.message.toUpperCase();
//    }
//
//    public String lowercase(){
//        return this.message.toLowerCase();
//    }

    public int addMessage(ChatForm chatForm){
        String messageMode = null;
        if(chatForm.getMode().equals("Shout")){
             messageMode = chatForm.getMessageInput().toUpperCase();
        }else if(chatForm.getMode().equals("Whisper")){
            messageMode = chatForm.getMessageInput().toLowerCase();
        }else{
            messageMode = chatForm.getMessageInput();
        }
        return chatMessageMapper.insertChatMessage(new ChatMessage(null, chatForm.getUsernameInput(), messageMode));
    }

//    public void createBadWords(){
//        this.badWordsList.add("Naughty");
//        this.badWordsList.add("ShutUp");
//    }
//
//    public List<String> getBadWordsList() {
//        createBadWords();
//        return this.badWordsList;
//    }

}