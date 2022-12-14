package com.dec.chatapp.usertesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void registerUser(String firstName, String lastName, String username, String password){
        this.firstNameField.clear();
        this.firstNameField.sendKeys(firstName);

        this.lastNameField.clear();
        this.lastNameField.sendKeys(lastName);

        this.usernameField.clear();
        this.usernameField.sendKeys(username);

        this.passwordField.clear();
        this.passwordField.sendKeys(password);

        this.submitButton.click();
    }
}
