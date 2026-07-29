package com.iAssureit.project.tests;

import com.iAssureit.project.base.BaseTest;
import org.testng.annotations.Test;

public class SanityTest extends BaseTest {

    @Test
    public void verifyBrowserOpensAndLoadsUrl() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
    }
}