package com.ecommerce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit. jupiter.api.Test;
import org.junit.jupiter.api. Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util. List;

public class AddToCartTest extends BaseTest {

    @BeforeEach
    public void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void testAddProductToCart() {
        // Add first product
        driver.findElement(By. id("add-to-cart-sauce-labs-backpack")).click();
        
        // Verify cart badge shows 1
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String count = cartBadge.getText();
        
        Assertions.assertEquals("1", count, "Cart should show 1 item");
        
        System.out.println("✅ Product added to cart.  Count: " + count);
    }

    @Test
    public void testAddMultipleProducts() {
        // Add 3 products
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By. id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        
        // Verify count
        WebElement cartBadge = driver. findElement(By.className("shopping_cart_badge"));
        String count = cartBadge.getText();
        
        Assertions. assertEquals("3", count, "Cart should show 3 items");
        
        System.out.println("✅ Multiple products added.  Count: " + count);
    }

    @Test
    public void testViewCart() {
        // Add product
        driver.findElement(By. id("add-to-cart-sauce-labs-backpack")).click();
        
        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();
        
        // Verify cart page
        String url = driver.getCurrentUrl();
        Assertions.assertTrue(url.contains("cart.html"), "Should be on cart page");
        
        // Verify product in cart
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        Assertions. assertEquals(1, cartItems.size(), "Should have 1 item in cart");
        
        System. out.println("✅ Cart page displays correctly");
    }

    @Test
    public void testRemoveFromCart() {
        // Add product
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        
        // Remove it
        driver.findElement(By. id("remove-sauce-labs-backpack")).click();
        
        // Verify cart badge is gone
        List<WebElement> badges = driver.findElements(By. className("shopping_cart_badge"));
        Assertions.assertEquals(0, badges.size(), "Cart should be empty");
        
        System.out.println("✅ Product removed from cart");
    }
}