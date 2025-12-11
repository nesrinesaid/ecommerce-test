package com.ecommerce.tests;

import org.openqa. selenium.By;
import org. openqa.selenium.WebElement;
import org.junit.jupiter. api.Test;
import org. junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PaymentProcessTest extends BaseTest {

    @BeforeEach
    public void loginAndAddProduct() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        // Add product to cart
        driver.findElement(By. id("add-to-cart-sauce-labs-backpack")).click();
    }

    @Test
    public void testAccessCheckout() {
        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        
        // Click checkout
        driver.findElement(By.id("checkout")).click();
        
        // Verify on checkout page
        String url = driver. getCurrentUrl();
        Assertions. assertTrue(url.contains("checkout-step-one"), 
                             "Should be on checkout page");
        
        System.out.println("✅ Checkout page accessible");
    }

    @Test
    public void testFillCheckoutInformation() {
        // Go to checkout
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        
        // Fill form
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("75001");
        
        // Continue
        driver.findElement(By. id("continue")).click();
        
        // Verify on overview page
        String url = driver.getCurrentUrl();
        Assertions.assertTrue(url.contains("checkout-step-two"), 
                             "Should be on checkout overview");
        
        System.out.println("✅ Checkout information filled successfully");
    }

    @Test
    public void testCompleteOrder() {
        // Go through checkout
        driver.findElement(By. className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("75001");
        driver.findElement(By.id("continue")).click();
        
        // Complete order
        driver.findElement(By.id("finish")).click();
        
        // Verify confirmation
        WebElement confirmation = driver.findElement(By. className("complete-header"));
        String message = confirmation.getText();
        
        Assertions.assertEquals("Thank you for your order!", message, 
                               "Should show confirmation");
        
        System.out. println("✅ Order completed:  " + message);
    }

    @Test
    public void testViewOrderSummary() {
        // Go to checkout overview
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("75001");
        driver.findElement(By.id("continue")).click();
        
        // Verify summary elements
        WebElement summaryInfo = driver.findElement(By. className("summary_info"));
        Assertions.assertTrue(summaryInfo.isDisplayed(), "Order summary should be visible");
        
        WebElement total = driver.findElement(By. className("summary_total_label"));
        Assertions.assertTrue(total.isDisplayed(), "Total should be visible");
        
        System.out.println("✅ Order summary displayed correctly");
    }
}