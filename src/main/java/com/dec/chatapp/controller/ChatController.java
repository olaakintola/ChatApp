package com.dec.chatapp.controller;


import com.dec.chatapp.model.ChatForm;
import com.dec.chatapp.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Model model){
        model.addAttribute("exchanges", this.messageService.getChatHistory());
        return "chat";
    }

    @PostMapping
    public String addMessage(@ModelAttribute("chatForm") ChatForm chatForm, Model model, Authentication authentication){
        chatForm.setUsernameInput(authentication.getName());
        this.messageService.addMessage(chatForm);
        model.addAttribute("exchanges", this.messageService.getChatHistory());
//        model.addAttribute("badwords", this.messageService.getBadWordsList());
        chatForm.setMessageInput("");
        chatForm.setUsernameInput("");
        return "chat";
    }


}
