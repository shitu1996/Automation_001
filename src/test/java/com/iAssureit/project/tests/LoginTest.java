package com.iAssureit.project.tests;

import com.iAssureit.project.base.BaseTest;
import com.iAssureit.project.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        driver.get("https://uatwebsite-kediaadvisory.iassureit.in/authadmin/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin@gmail.com", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(d -> d.getCurrentUrl().contains("/admin"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/admin"), "Login failed - not redirected to admin dashboard");
    }
}