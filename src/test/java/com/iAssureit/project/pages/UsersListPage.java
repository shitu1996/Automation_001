package com.iAssureit.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;

public class UsersListPage {

    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public UsersListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ===== LOGIN ELEMENTS =====
    @FindBy(xpath = "//input[@id='userName' and @type='text']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;


    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    // ===== LEFT MENU NAVIGATION =====
    @FindBy(xpath = "//span[text()='User Management']")
    private WebElement userManagementMenu;

    @FindBy(xpath = "//span[text()='Users List']")
    private WebElement pageHeader;

    @FindBy(xpath = "(//span[text()='Users List'])[2]")
    private WebElement usersListMenu;

    // ===== SEARCH SECTION =====
    @FindBy(xpath = "//input[@placeholder='Search By Name, Email and Mobile Number...']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[contains(@class,'search-btn')]")
    private WebElement searchButton;

    // ===== FILTER DROPDOWNS =====
    @FindBy(xpath = "//select[contains(@class,'role-dropdown')]")
    private WebElement roleDropdown;

    @FindBy(xpath = "//select[contains(@class,'status-dropdown')]")
    private WebElement statusDropdown;

    // ===== TABLE =====
    @FindBy(xpath = "//table//tbody/tr")
    private List<WebElement> userRows;

    // ===== PAGINATION =====
    @FindBy(xpath = "//button[contains(@class,'next')]")
    private WebElement nextButton;

    @FindBy(xpath = "//button[contains(@class,'prev')]")
    private WebElement prevButton;

    // =============================================
    // LOGIN METHOD
    // =============================================
    public void login(String username, String password) {
        // Wait for login page to load
        wait.until(ExpectedConditions.visibilityOf(usernameInput));

        // Enter credentials
        usernameInput.clear();
        usernameInput.sendKeys(username);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        // Click login button
        loginButton.click();

        // Wait for dashboard/menu to appear after login
        wait.until(ExpectedConditions.visibilityOf(userManagementMenu));
        System.out.println("✅ Login successful");
    }

    // =============================================
    // NAVIGATION METHOD
    // =============================================
    public void navigateToUsersList() {

        // Click User Management
        wait.until(ExpectedConditions.elementToBeClickable(userManagementMenu));
        userManagementMenu.click();

        // Wait for Users List menu
        wait.until(ExpectedConditions.elementToBeClickable(usersListMenu));

        // Click Users List
        usersListMenu.click();

        // Wait for Users List page header
        wait.until(ExpectedConditions.visibilityOf(pageHeader));

        System.out.println("✅ Navigated to Users List page");
    }

    // =============================================
    // COMPLETE LOGIN + NAVIGATION (One Method)
    // =============================================
    public void loginAndNavigateToUsersList(String username, String password) {
        login(username, password);
        navigateToUsersList();
    }

    // =============================================
    // VERIFICATION METHODS
    // =============================================
    public boolean isPageLoaded() {
        try {
            return pageHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(pageHeader));
    }

    public String getPageHeaderText() {
        return pageHeader.getText();
    }

    // =============================================
    // SEARCH METHODS
    // =============================================
    public void searchUser(String searchTerm) {
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        searchButton.click();
        waitForPageLoad();
    }

    public void clearSearch() {
        searchInput.clear();
        searchButton.click();
        waitForPageLoad();
    }

    // =============================================
    // FILTER METHODS
    // =============================================
    public void filterByRole(String role) {
        Select select = new Select(roleDropdown);
        select.selectByVisibleText(role);
        waitForPageLoad();
    }

    public void filterByStatus(String status) {
        Select select = new Select(statusDropdown);
        select.selectByVisibleText(status);
        waitForPageLoad();
    }

    // =============================================
    // TABLE METHODS
    // =============================================
    public int getUserCount() {
        return userRows.size();
    }

    public boolean isUserPresent(String username) {
        for (WebElement row : userRows) {
            if (row.getText().contains(username)) {
                return true;
            }
        }
        return false;
    }

    public List<WebElement> getAllUsers() {
        return userRows;
    }

    // =============================================
    // PAGINATION METHODS
    // =============================================
    public void goToNextPage() {
        if (nextButton.isEnabled()) {
            nextButton.click();
            waitForPageLoad();
            System.out.println("✅ Navigated to next page");
        } else {
            System.out.println("⚠️ No next page available");
        }
    }

    public void goToPreviousPage() {
        if (prevButton.isEnabled()) {
            prevButton.click();
            waitForPageLoad();
            System.out.println("✅ Navigated to previous page");
        } else {
            System.out.println("⚠️ No previous page available");
        }
    }

    public boolean isNextPageEnabled() {
        return nextButton.isEnabled();
    }

    public boolean isPreviousPageEnabled() {
        return prevButton.isEnabled();
    }
}