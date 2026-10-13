package com.iAssureit.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserManagementPage {

    WebDriver driver;
    WebDriverWait wait;


    // =====================================================
    // USER MANAGEMENT LOCATORS
    // =====================================================

    // User Management main menu
    private By userManagementMenu =
            By.xpath("//span[normalize-space()='User Management']");


    // Users List submenu
    private By usersListMenu =
            By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div/div/ul/div[3]/ul/div/li[1]/div/span[2]");


    // =====================================================
    // USERS LIST LOCATORS
    // =====================================================

    // Search field
    private By searchField =
            By.name("search");


    // =====================================================
    // CREATE USER LOCATORS
    // =====================================================

    // Create User button from Users List page
    private By createUserButton =
            By.xpath("//button//span[normalize-space()='Create User']");


    // First Name
    private By firstNameField =
            By.id("firstName");


    // Last Name
    private By lastNameField =
            By.id("lastName");


    // Email
    private By emailField =
            By.id("email");


    // Mobile Number
    private By mobileField =
            By.xpath("//input[@type='tel']");


    // Role dropdown
    private By roleDropdown =
            By.xpath("//button[.//span[contains(normalize-space(),'Select Role')]]");


    // Subscriber checkbox
    private By subscriberRoleOption =
            By.xpath("//input[@type='checkbox' and @value='subscriber']");


    // Create User Account button
    private By createUserAccountButton =
            By.xpath("//button[@type='submit']//span[normalize-space()='Create User Account']");


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public UserManagementPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );
    }


    // =====================================================
    // USER MANAGEMENT METHODS
    // =====================================================

    // Click User Management
    public void clickUserManagement() {

        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        userManagementMenu
                )
        );

        menu.click();

        System.out.println(
                "User Management menu clicked."
        );
    }


    // =====================================================
    // USERS LIST METHODS
    // =====================================================

    // Click Users List
    public void clickUsersList() {

        WebElement usersList = wait.until(
                ExpectedConditions.elementToBeClickable(
                        usersListMenu
                )
        );

        usersList.click();

        System.out.println(
                "Users List clicked."
        );


        wait.until(
                ExpectedConditions.urlContains(
                        "/admin/user-management"
                )
        );

        System.out.println(
                "Users List page opened successfully."
        );
    }


    // Search User
    public void searchUser(String value)
            throws InterruptedException {

        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        searchField
                )
        );


        JavascriptExecutor js =
                (JavascriptExecutor) driver;


        js.executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                search
        );


        Thread.sleep(1500);


        search.click();


        search.clear();


        search.sendKeys(value);


        System.out.println(
                "Searching user: " + value
        );


        By searchedUser = By.xpath(
                "//table//tbody//tr[contains(normalize-space(.),'" +
                        value +
                        "')]"
        );


        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        searchedUser
                )
        );


        js.executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                result
        );


        Thread.sleep(1500);


        System.out.println(
                "Scrolled to searched user result."
        );
    }


    // Verify searched user
    public boolean verifyUserInList(
            String expectedValue) {

        By searchedUser = By.xpath(
                "//table//tbody//tr[contains(normalize-space(.),'" +
                        expectedValue +
                        "')]"
        );

        try {

            WebElement userRow = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            searchedUser
                    )
            );


            JavascriptExecutor js =
                    (JavascriptExecutor) driver;


            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    userRow
            );


            System.out.println(
                    "User found successfully:"
            );

            System.out.println(
                    userRow.getText()
            );


            return true;

        } catch (Exception e) {

            System.out.println(
                    "User not found: " +
                            expectedValue
            );

            return false;
        }
    }


    // =====================================================
    // CREATE NEW USER METHODS
    // =====================================================

    // Click Create User button
    public void clickCreateUser() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        createUserButton
                )
        );


        JavascriptExecutor js =
                (JavascriptExecutor) driver;


        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                button
        );


        button.click();


        wait.until(
                ExpectedConditions.urlContains(
                        "/admin/user-management/create-user"
                )
        );


        System.out.println(
                "Create User page opened successfully."
        );
    }


    // Enter First Name
    public void enterFirstName(
            String firstName) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameField
                )
        );


        field.clear();


        field.sendKeys(firstName);


        System.out.println(
                "First Name entered: " +
                        firstName
        );
    }


    // Enter Last Name
    public void enterLastName(
            String lastName) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameField
                )
        );


        field.clear();


        field.sendKeys(lastName);


        System.out.println(
                "Last Name entered: " +
                        lastName
        );
    }


    // Enter Email
    public void enterEmail(
            String email) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        emailField
                )
        );


        field.clear();


        field.sendKeys(email);


        System.out.println(
                "Email entered: " +
                        email
        );
    }


    // Enter Mobile Number
    public void enterMobileNumber(
            String mobile) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        mobileField
                )
        );


        JavascriptExecutor js =
                (JavascriptExecutor) driver;


        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                field
        );


        field.click();


        /*
         * The field already displays +91.
         * Enter only the 10 digit mobile number.
         */
        field.sendKeys(mobile);


        System.out.println(
                "Mobile Number entered: " +
                        mobile
        );
    }


    // Select Subscriber Role
    public void selectSubscriberRole() {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        roleDropdown
                )
        );


        dropdown.click();


        System.out.println(
                "Role dropdown opened."
        );


        WebElement subscriber = wait.until(
                ExpectedConditions.elementToBeClickable(
                        subscriberRoleOption
                )
        );


        /*
         * If subscriber is already checked,
         * do not click it again.
         */
        if (!subscriber.isSelected()) {

            subscriber.click();
        }


        System.out.println(
                "Subscriber role selected."
        );
    }


    // Click Create User Account
    public void clickCreateUserAccount() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        createUserAccountButton
                )
        );


        JavascriptExecutor js =
                (JavascriptExecutor) driver;


        js.executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                button
        );


        button.click();


        System.out.println(
                "Create User Account button clicked."
        );
    }


    // Complete Create User Flow
    public void createNewUser(
            String firstName,
            String lastName,
            String email,
            String mobile)
            throws InterruptedException {


        enterFirstName(firstName);

        Thread.sleep(500);


        enterLastName(lastName);

        Thread.sleep(500);


        enterEmail(email);

        Thread.sleep(500);


        enterMobileNumber(mobile);

        Thread.sleep(500);


        selectSubscriberRole();

        Thread.sleep(1000);


        clickCreateUserAccount();


        System.out.println(
                "Create User form submitted."
        );
    }
}