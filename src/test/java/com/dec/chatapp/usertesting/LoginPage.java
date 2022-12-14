package com.dec.chatapp.usertesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public LoginPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void userLogin(String username, String password){
        this.usernameField.clear();
        this.usernameField.sendKeys(username);

        this.passwordField.clear();
        this.passwordField.sendKeys(password);

        this.submitButton.click();
    }

}
