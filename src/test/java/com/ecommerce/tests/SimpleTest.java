package com.ecommerce.tests;

import org. junit.jupiter.api.Test;
import org.junit.jupiter. api. Assertions;

public class SimpleTest extends BaseTest {

    @Test
    public void testPageLoads() {
        String title = driver.getTitle();
        System.out.println("Page title: " + title);
        Assertions.assertNotNull(title, "Title should not be null");
    }
}