package com.dec.chatapp;

import com.dec.chatapp.usertesting.ChatPage;
import com.dec.chatapp.usertesting.LoginPage;
import com.dec.chatapp.usertesting.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewApplicationTests {

	@LocalServerPort
	private Integer port;

	private static WebDriver webDriver;
	private SignupPage signupPage;
	private LoginPage loginPage;
	private ChatPage chatPage;
	public String firstUser;
	public String firstUserChatMessage;
	public String firstUserPassword;

	@BeforeAll
	public static void beforeAll(){
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
	}

	@AfterAll
	public static void afterAll(){
		webDriver.quit();
	}

	@BeforeEach
	public void beforeEach(){
		firstUser = "jane";
		firstUserChatMessage = "I love dog";
		firstUserPassword = "password";
	}

	@Test
	public void testUserSignupLoginAndAddChatMessage(){

		webDriver.get("http://localhost:" + port + "/signup");
		signupPage = new SignupPage(webDriver);
		signupPage.registerUser("Jane", "Doe", firstUser, firstUserPassword);
		webDriver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(webDriver);
		loginPage.userLogin(firstUser, firstUserPassword);
		chatPage = new ChatPage(webDriver);
		chatPage.addChatMessage(firstUserChatMessage, "Shout");
		String[] result = chatPage.firstUserViewedMessages().get(0).split(":");
		assertEquals(firstUser, result[0] );
		assertEquals(firstUserChatMessage.toUpperCase(), result[1].toUpperCase().trim() );
		chatPage.userLogOut();
	}

	@Test
	public void testAnotherUserViewOldChatMessage(){
		String secondUser = "don";
		String secondUserPassword = "trump";
		String secondUserChatMessage = "Lion King Lives on";

		webDriver.get("http://localhost:" + port + "/signup");
		signupPage = new SignupPage(webDriver);
		signupPage.registerUser("Jane", "Doe", firstUser, firstUserPassword);
		webDriver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(webDriver);
		loginPage.userLogin(firstUser, firstUserPassword);
		chatPage = new ChatPage(webDriver);
		chatPage.addChatMessage(firstUserChatMessage, "Shout");

		webDriver.get("http://localhost:" + port + "/signup");
		signupPage = new SignupPage(webDriver);
		signupPage.registerUser("Donald", "Trump", secondUser, secondUserPassword);

		webDriver.get("http://localhost:" + port + "/login");
		loginPage = new LoginPage(webDriver);
		loginPage.userLogin(secondUser, secondUserPassword);
		chatPage = new ChatPage(webDriver);
		chatPage.addChatMessage(secondUserChatMessage, "Whisper");
		String[] result = chatPage.secondUserViewedMessages().get(0).split(":");
		assertEquals(firstUser, result[0] );
		assertEquals(firstUserChatMessage.toUpperCase(), result[1].toUpperCase().trim() );
		chatPage.userLogOut();
	}
}
