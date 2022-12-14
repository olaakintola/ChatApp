package com.dec.chatapp.usertesting;

import org.apache.ibatis.javassist.tools.web.Webserver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageTextField;

    @FindBy(id = "messageType")
    private WebElement messageTypeField;

    @FindBy(id = "chatHistory")
    private List<WebElement> chatMessages;

    @FindBy(how = How.CSS, using = "input[type='submit']")
    private WebElement submitButton;

    @FindBy(id = "submit-button")
    private WebElement logoutButton;

    private WebDriverWait webDriverWait;

    public ChatPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    }

    public void addChatMessage(String messageText, String option){

        webDriverWait.until(ExpectedConditions.visibilityOf(messageTextField));
        this.messageTextField.clear();
        this.messageTextField.sendKeys(messageText);

        Select dropdown = new Select(messageTypeField);
        dropdown.selectByValue(option);

        this.submitButton.click();
    }

    public List<String> firstUserViewedMessages(){
        List<String> chatList = new ArrayList<>();

        for (WebElement chatMessage : chatMessages) {
            chatList.add(chatMessage.getText());
        }
        return chatList;
    }

    public List<String> secondUserViewedMessages(){
        List<String> chatList = new ArrayList<>();

        for (WebElement chatMessage : chatMessages) {
            chatList.add(chatMessage.getText());
        }
        return chatList;
    }

    public void userLogOut(){
        this.logoutButton.click();
    }


}
