package org.springframework.samples.petclinic;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = PetClinicApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaywrightTest {

	@Test
	void testCreateOwner() {
		var url = String.format("http://localhost:%s", randomServerPort);
		page.navigate(url);

		// Fill form
		page.locator("text=Find owners").click();
		page.locator("text=Add Owner").click();
		page.locator("input[name=\"firstName\"]").fill("Rory");
		page.locator("input[name=\"lastName\"]").fill("Preddy");
		page.locator("input[name=\"address\"]").fill("Blah");
		page.locator("input[name=\"city\"]").fill("Johannesburg");
		page.locator("input[name=\"telephone\"]").fill("1234567890");

		// Submit
		page.locator("text=Add Owner").click();

		var ownerName = page.locator("//b[@id='ownerName']").innerText();

		// Assert that the owner name is correct
		assertEquals("Rory Preddy", ownerName);
	}

	@LocalServerPort
	private int randomServerPort;

	static Playwright playwright;
	static Browser browser;

	BrowserContext context;

	Page page;

	@BeforeAll
	static void launchBrowser() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
	}

	@AfterAll
	static void closeBrowser() {
		playwright.close();
	}

	@BeforeEach
	void createContextAndPage() {
		context = browser.newContext();
		page = context.newPage();
	}

	@AfterEach
	void closeContext() {
		context.close();
	}

}