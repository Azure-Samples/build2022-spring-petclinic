package org.springframework.samples.petclinic;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class PlaywrightTest {

	// Shared between all tests in this class.
	static Playwright playwright;
	static Browser browser;

	// New instance for each test method.
	BrowserContext context;

	Page page;

	@BeforeAll
	static void launchBrowser() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
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

	@Test
	void testCreateOwner() {
		page.navigate("https://springpetclinicrpza.wittystone-2257bdab.westeurope.azurecontainerapps.io");

		// Click text=Find owners
		page.locator("text=Find owners").click();
		// Click text=Add Owner
		page.locator("text=Add Owner").click();
		// Click input[name="firstName"]
		page.locator("input[name=\"firstName\"]").click();
		// Fill input[name="firstName"]
		page.locator("input[name=\"firstName\"]").fill("Rory");
		// Click input[name="lastName"]
		page.locator("input[name=\"lastName\"]").click();
		// Fill input[name="lastName"]
		page.locator("input[name=\"lastName\"]").fill("Preddy");
		// Click input[name="address"]
		page.locator("input[name=\"address\"]").click();
		// Fill input[name="address"]
		page.locator("input[name=\"address\"]").fill("Blah");
		// Click input[name="city"]
		page.locator("input[name=\"city\"]").click();
		// Fill input[name="city"]
		page.locator("input[name=\"city\"]").fill("Johannesburg");
		// Click input[name="telephone"]
		page.locator("input[name=\"telephone\"]").click();
		// Fill input[name="telephone"]
		page.locator("input[name=\"telephone\"]").fill("1234567890");
		// Click text=Add Owner
		page.locator("text=Add Owner").click();

		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")).setFullPage(true));

	}

}