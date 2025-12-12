package com.ecommerce.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome. ChromeOptions;
import org. junit.jupiter.api.AfterEach;
import org.  junit.jupiter.api.BeforeEach;

public class BaseTest {
    protected WebDriver driver;
    protected String baseUrl = "https://www.saucedemo.com";

    @BeforeEach
    public void setUp() {
        // Use Selenium Manager (no WebDriverManager needed)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            // Pause 3 seconds so you can see the final state
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            
            
            driver.quit();
        }
    }
    }