package com.iAssureit.project.tests;

import com.iAssureit.project.base.BaseTest;
import com.iAssureit.project.pages.LoginPage;
import com.iAssureit.project.pages.MultiLegTradingCallPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class MultiLegTradingCallTest extends BaseTest {

    @Test
    public void addMultiLegTradingCall()
            throws InterruptedException {

        // Login
        driver.get(
                "https://uatwebsite-kediaadvisory.iassureit.in/authadmin/login"
        );

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "admin@gmail.com",
                "admin123"
        );

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                d -> d.getCurrentUrl().contains("/admin")
        );

        // Open Add Trading Call Page
        driver.get(
                "https://uatwebsite-kediaadvisory.iassureit.in/admin/trading-call/add-trading-call"
        );

        MultiLegTradingCallPage page =
                new MultiLegTradingCallPage(driver);

        // Select Multi Leg
        page.selectMultiLeg();

        // Sub Call Type
        page.selectSubCallType("METEN");

        // Estimated Profit / Loss
        page.enterEstimatedProfit("10000");
        page.enterEstimatedLoss("2000");

        // =========================
        // LEG 1
        // =========================

        page.selectScript(
                "gold",
                "GOLD - GOLD25SEP26C132300"
        );

        page.selectAction("BUY");

        page.enterCmp("10000");

        page.enterEntry("12000");

        page.clickAddLeg();

        System.out.println(
                "Leg 1 added successfully."
        );

        // =========================
        // LEG 2
        // =========================

        page.selectScript(
                "silver",
                "SILVER - SILVER24SEP26C209000"
        );

        page.selectAction("SELL");

        page.enterCmp("20000");

        page.enterEntry("22000");

        page.clickAddLeg();

        System.out.println(
                "Leg 2 added successfully."
        );

        // =========================
        // SUBMIT
        // =========================

        page.clickSubmit();

        page.clickSuccessPopupOk();

        wait.until(
                d -> d.getCurrentUrl()
                        .contains(
                                "/trading-call/trading-call-list"
                        )
        );

        System.out.println(
                "Multi Leg Trading Call added successfully."
        );

        System.out.println(
                "Landed on: "
                        + driver.getCurrentUrl()
        );

        Thread.sleep(5000);
    }
}