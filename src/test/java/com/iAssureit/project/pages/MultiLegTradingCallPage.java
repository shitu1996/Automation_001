package com.iAssureit.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MultiLegTradingCallPage {

    WebDriver driver;

    public MultiLegTradingCallPage(WebDriver driver) {
        this.driver = driver;
    }

    // Multi Leg button
    private By multiLegButton =
            By.xpath("//button[text()='Multi Leg']");

    // Sub Call Type
    private By subCallTypeDropdown =
            By.xpath("//*[@id=\"subCallType\"]/span");

    private By subCallTypeSearch =
            By.xpath("/html/body/div[5]/div[1]/div/input");

    private By subCallTypeOption =
            By.xpath("/html/body/div[5]/div[2]/ul/li/span/div");

    // Estimated Profit / Loss
    private By estimatedProfit =
            By.xpath("//input[@id='estimatedProfit']");

    private By estimatedLoss =
            By.xpath("//input[@id='estimatedLoss']");

    // Script Name field
    private By scriptName =
            By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/section[1]/section/div/div[2]/div[2]/div[1]/div/div/div[2]/div/input");

    // Action
    private By actionDropdown =
            By.id("action");

    // CMP
    private By cmpField =
            By.id("cmp");

    // Entry
    private By entryField =
            By.id("entry");

    // Add Leg button
    private By addLegButton =
            By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/section[1]/section/div/div[2]/div[3]/div[2]/button");

    // Submit
    private By submitButton =
            By.xpath("/html/body/div[2]/div/div/div[2]/div[2]/section[1]/section/div/div[2]/div[8]/button");

    // Success Popup OK
    private By successPopupOk =
            By.xpath("/html/body/div[5]/div/div[6]/button[1]");


    public void selectMultiLeg() throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(multiLegButton).click();
    }


    public void selectSubCallType(String value) throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(subCallTypeDropdown).click();

        Thread.sleep(3000);

        driver.findElement(subCallTypeSearch)
                .sendKeys(value);

        Thread.sleep(3000);

        driver.findElement(subCallTypeOption)
                .click();
    }


    public void enterEstimatedProfit(String value)
            throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(estimatedProfit)
                .sendKeys(value);
    }


    public void enterEstimatedLoss(String value)
            throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(estimatedLoss)
                .sendKeys(value);
    }


    public void selectScript(
            String searchText,
            String exactVisibleText
    ) throws InterruptedException {

        Thread.sleep(3000);

        WebElement script =
                driver.findElement(scriptName);

        script.click();

        Thread.sleep(3000);

        script.sendKeys(searchText);

        Thread.sleep(3000);

        driver.findElement(
                By.xpath(
                        "//li[text()='" + exactVisibleText + "']"
                )
        ).click();

        Thread.sleep(3000);
    }


    public void selectAction(String value) {

        WebElement action =
                driver.findElement(actionDropdown);

        Select select =
                new Select(action);

        select.selectByVisibleText(value);
    }


    public void enterCmp(String value)
            throws InterruptedException {

        driver.findElement(cmpField)
                .sendKeys(value);

        Thread.sleep(3000);
    }


    public void enterEntry(String value)
            throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(entryField)
                .sendKeys(value);
    }


    public void clickAddLeg()
            throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(addLegButton)
                .click();
    }


    public void clickSubmit()
            throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(submitButton)
                .click();
    }


    public void clickSuccessPopupOk()
            throws InterruptedException {

        Thread.sleep(3000);

        driver.findElement(successPopupOk)
                .click();
    }
}