package com.iAssureit.project.tests;

import com.iAssureit.project.base.BaseTest;
import com.iAssureit.project.pages.LoginPage;
import com.iAssureit.project.pages.UserManagementPage;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserManagementTest
        extends BaseTest {


    // =====================================================
    // TEST 1 - SEARCH EXISTING USER
    // =====================================================

    @Test
    public void searchUserByName()
            throws InterruptedException {


        // STEP 1 - Open Login Page

        driver.get(
                "https://uatwebsite-kediaadvisory.iassureit.in/authadmin/login"
        );


        // STEP 2 - Login

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "admin@gmail.com",
                "admin123"
        );


        // STEP 3 - Wait for Admin Dashboard

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                d -> d.getCurrentUrl()
                        .contains("/admin")
        );

        System.out.println(
                "Login successful."
        );


        // STEP 4 - Create User Management Page Object

        UserManagementPage page =
                new UserManagementPage(driver);


        // STEP 5 - Click User Management

        page.clickUserManagement();


        // STEP 6 - Click Users List

        page.clickUsersList();


        // STEP 7 - Search Rani Kulkarni

        page.searchUser(
                "Rani Kulkarni"
        );


        // STEP 8 - Verify Rani Kulkarni

        boolean userFound =
                page.verifyUserInList(
                        "Rani Kulkarni"
                );


        Assert.assertTrue(
                userFound,
                "Rani Kulkarni was not found in Users List."
        );


        System.out.println(
                "TEST PASSED - Rani Kulkarni found successfully."
        );


        Thread.sleep(5000);
    }


    // =====================================================
    // TEST 2 - CREATE NEW USER
    // =====================================================

    @Test
    public void createNewUserTest()
            throws InterruptedException {


        // STEP 1 - Open Login Page

        driver.get(
                "https://uatwebsite-kediaadvisory.iassureit.in/authadmin/login"
        );


        // STEP 2 - Login

        LoginPage loginPage =
                new LoginPage(driver);

        loginPage.login(
                "admin@gmail.com",
                "admin123"
        );


        // STEP 3 - Wait for Admin Dashboard

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                d -> d.getCurrentUrl()
                        .contains("/admin")
        );


        System.out.println(
                "Login successful."
        );


        // STEP 4 - Create User Management Page Object

        UserManagementPage page =
                new UserManagementPage(driver);


        // STEP 5 - Click User Management

        page.clickUserManagement();


        // STEP 6 - Click Users List

        page.clickUsersList();


        // STEP 7 - Click Create User

        page.clickCreateUser();


        // =================================================
        // STEP 8 - Prepare New User Data
        // =================================================

        long currentTime =
                System.currentTimeMillis();


        String firstName =
                "Automation";


        String lastName =
                "User";


        // Unique email on every execution
        String email =
                "automation" +
                        currentTime +
                        "@gmail.com";


        // Generate unique 10 digit mobile number
        String timeValue =
                String.valueOf(currentTime);


        String mobile =
                "9" +
                        timeValue.substring(
                                timeValue.length() - 9
                        );


        System.out.println(
                "===================================="
        );

        System.out.println(
                "Creating New User"
        );

        System.out.println(
                "First Name : " + firstName
        );

        System.out.println(
                "Last Name : " + lastName
        );

        System.out.println(
                "Email : " + email
        );

        System.out.println(
                "Mobile : " + mobile
        );

        System.out.println(
                "Role : subscriber"
        );

        System.out.println(
                "===================================="
        );


        // =================================================
        // STEP 9 - Fill Form and Create User
        // =================================================

        page.createNewUser(
                firstName,
                lastName,
                email,
                mobile
        );


        // =================================================
        // STEP 10 - Wait to see result
        // =================================================

        Thread.sleep(5000);


        System.out.println(
                "CREATE USER TEST COMPLETED."
        );
    }
}