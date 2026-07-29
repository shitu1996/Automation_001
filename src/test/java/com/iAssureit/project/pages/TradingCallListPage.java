package com.iAssureit.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TradingCallListPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locator for all table rows
    private By tradingRows = By.xpath("//tr[@class='bg-white']");

    public TradingCallListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Open Trading Call List page
    public void openTradingCallList() {
        driver.get("https://uatwebsite-kediaadvisory.iassureit.in/admin/trading-call/trading-call-list");
    }

    // Verify Script Name in the table
    public boolean verifyTradingCall(String scriptName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(tradingRows));

        List<WebElement> rows = driver.findElements(tradingRows);

        for (WebElement row : rows) {

            String script = row.findElement(By.xpath("./td[2]")).getText().trim();

            if (script.equalsIgnoreCase(scriptName)) {
                return true;
            }
        }

        return false;
    }
}