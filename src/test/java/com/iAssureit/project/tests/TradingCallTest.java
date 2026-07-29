package com.iAssureit.project.tests;

import com.iAssureit.project.base.BaseTest;
import com.iAssureit.project.pages.LoginPage;
import com.iAssureit.project.pages.TradingCallListPage;
import com.iAssureit.project.pages.TradingCallPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TradingCallTest extends BaseTest {

    @Test
    public void addNewTradingCall() throws InterruptedException {

        // Login
        driver.get("https://uatwebsite-kediaadvisory.iassureit.in/authadmin/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin@gmail.com", "admin123");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait until login is successful
        wait.until(d -> d.getCurrentUrl().contains("/admin"));

        // Open Add Trading Call page
        driver.get("https://uatwebsite-kediaadvisory.iassureit.in/admin/trading-call/add-trading-call");

        TradingCallPage page = new TradingCallPage(driver);

        // Fill Trading Call Details
        page.selectSingleLeg();
        page.selectSubCallType("METEN");
        page.enterScriptName("GOLD", "GOLD29JUL26C103900");
        page.enterExpiryDate("2026-07-29");
        page.enterStrikePrice("103900");
        page.selectOptionType("CE");
        page.selectAction("BUY");
        page.enterCmp("100");
        page.enterEntry("100");
        page.enterTarget("110");
        page.enterStopLoss("90");

        Thread.sleep(2000);

        // Submit Trading Call
        page.clickSubmit();
        page.clickSuccessPopupOk();

        // Wait until Trading Call List page opens
        wait.until(d -> d.getCurrentUrl().contains("/trading-call/trading-call-list"));

        System.out.println("Landed on : " + driver.getCurrentUrl());

        // Verify Trading Call in List
        TradingCallListPage listPage = new TradingCallListPage(driver);

        Assert.assertTrue(
                listPage.verifyTradingCall("METEN"),
                "Trading Call was not found in Trading Call List."
        );

        System.out.println("Trading Call verified successfully.");
    }
}