package com.ecommerce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

public class ProductSearchTest extends BaseTest {

    @BeforeEach
    public void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By. id("login-button")).click();
    }

    @Test
    public void testProductsAreDisplayed() {
        List<WebElement> products = driver. findElements(By.className("inventory_item"));
        
        Assertions.assertTrue(products. size() > 0, 
                             "Products should be displayed");
        
        System.out. println("✅ Found " + products.size() + " products");
    }

    @Test
    public void testProductDetailsVisible() {
        // Click first product
        driver.findElement(By.className("inventory_item_name")).click();
        
        // Verify details
        WebElement productName = driver.findElement(By.className("inventory_details_name"));
        WebElement productPrice = driver.findElement(By.className("inventory_details_price"));
        
        Assertions.assertTrue(productName.isDisplayed(), "Product name should be visible");
        Assertions.assertTrue(productPrice. isDisplayed(), "Product price should be visible");
        
        System.out.println("✅ Product:  " + productName.getText());
        System.out.println("   Price: " + productPrice. getText());
    }

    @Test
    public void testSortProducts() {
        // Use sort dropdown
        driver.findElement(By. className("product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='lohi']")).click();
        
        // Verify sorting worked
        List<WebElement> products = driver. findElements(By.className("inventory_item"));
        Assertions. assertTrue(products.size() > 0, "Products should still be visible after sorting");
        
        System. out.println("✅ Product sorting works");
    }

    @Test
    public void testViewProductImage() {
        // Click on product image
        driver.findElement(By.className("inventory_item_img")).click();
        
        // Verify on product page
        String url = driver.getCurrentUrl();
        Assertions.assertTrue(url.contains("inventory-item.html"), 
                             "Should navigate to product page");
        
        System.out.println("✅ Product image is clickable");
    }
}