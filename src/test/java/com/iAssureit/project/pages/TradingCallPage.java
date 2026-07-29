package com.iAssureit.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TradingCallPage {

    WebDriver driver;
    WebDriverWait wait;

    private By singleLegBtn = By.xpath("//button[normalize-space()='Single Leg']");
    private By subCallTypeDropdown = By.id("subCallType");
    private By scriptNameInput = By.xpath("//input[@placeholder='--Start Typing For Script Name--']");
    private By expiryDateField = By.id("expDate");
    private By strikePriceField = By.id("strikePrice");
    private By optionTypeDropdown = By.id("optionType");
    private By actionDropdown = By.cssSelector("select[id='buy/sell']");
    private By cmpField = By.id("cmp");
    private By entryField = By.id("entry");
    private By targetField = By.id("target1");
    private By stopLossField = By.id("stopLoss");
    private By submitButton = By.xpath("//button[@type='submit' and contains(text(),'Submit')]");
    private By successPopupOk = By.cssSelector(".swal2-confirm");

    public TradingCallPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void selectSingleLeg() {
        wait.until(ExpectedConditions.elementToBeClickable(singleLegBtn)).click();
    }

    public void selectSubCallType(String value) {
        driver.findElement(subCallTypeDropdown).click();
        By anyOption = By.cssSelector("li.p-dropdown-item");
        wait.until(ExpectedConditions.presenceOfElementLocated(anyOption));

        List<WebElement> options = driver.findElements(anyOption);
        for (WebElement opt : options) {
            if (opt.getText().trim().equalsIgnoreCase(value)) {
                opt.click();
                return;
            }
        }
        throw new RuntimeException("Sub Call Type not found: " + value);
    }

    public void enterScriptName(String searchTerm, String exactMatch) {
        WebElement scriptField = wait.until(ExpectedConditions.visibilityOfElementLocated(scriptNameInput));
        scriptField.clear();
        scriptField.sendKeys(searchTerm);

        By suggestionItems = By.xpath("//ul[contains(@class,'absolute') and contains(@class,'z-10')]/li");
        wait.until(ExpectedConditions.presenceOfElementLocated(suggestionItems));

        List<WebElement> suggestions = driver.findElements(suggestionItems);
        if (suggestions.isEmpty()) {
            throw new RuntimeException("No script suggestions appeared for: " + searchTerm);
        }

        WebElement target = null;
        for (WebElement s : suggestions) {
            if (s.getText().trim().contains(exactMatch)) {
                target = s;
                break;
            }
        }

        if (target == null) {
            throw new RuntimeException("No suggestion matched: " + exactMatch);
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", target);

        try {
            target.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", target);
        }
    }

    public void enterExpiryDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(expiryDateField)).sendKeys(date);
    }

    public void enterStrikePrice(String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(strikePriceField));
        field.sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.CONTROL, "a"));
        field.sendKeys(org.openqa.selenium.Keys.DELETE);
        field.sendKeys(value);
    }

    public void selectOptionType(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionTypeDropdown));
        new Select(driver.findElement(optionTypeDropdown)).selectByValue(value);
    }

    public void selectAction(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(actionDropdown));
        new Select(driver.findElement(actionDropdown)).selectByVisibleText(value);
    }

    public void enterCmp(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cmpField)).sendKeys(value);
    }

    public void enterEntry(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(entryField)).sendKeys(value);
    }

    public void enterTarget(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(targetField)).sendKeys(value);
    }

    public void enterStopLoss(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stopLossField)).sendKeys(value);
    }

    public void clickSubmit() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        wait.until(d -> btn.getAttribute("disabled") == null);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);

        try {
            btn.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", btn);
        }
    }

    public void clickSuccessPopupOk() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successPopupOk));
        driver.findElement(successPopupOk).click();
    }
}