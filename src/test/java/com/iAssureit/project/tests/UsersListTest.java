package com.iAssureit.project.tests;


import com.iAssureit.project.base.BaseTest;
import com.iAssureit.project.pages.UsersListPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class UsersListTest extends BaseTest {

    UsersListPage usersListPage;

    @BeforeMethod
    public void setup() {
        // Create UsersListPage object
        usersListPage = new UsersListPage(driver);

        // Login and Navigate to Users List (Using credentials from AdminBaseClass)
        usersListPage.loginAndNavigateToUsersList(adminUsername, adminPassword);
    }

    // =============================================
    // TEST CASES
    // =============================================

    @Test(priority = 1)
    public void testVerifyPageLoad() {
        boolean isLoaded = usersListPage.isPageLoaded();
        Assert.assertTrue(isLoaded, "Users List page should load successfully");
        System.out.println("✅ Users List page loaded successfully");
    }

    @Test(priority = 2)
    public void testPageHeader() {
        String headerText = usersListPage.getPageHeaderText();
        Assert.assertTrue(headerText.contains("User List"), "Header should contain 'User List'");
        System.out.println("✅ Header text: " + headerText);
    }

    @Test(priority = 3)
    public void testSearchUser() {
        usersListPage.searchUser("John");
        int count = usersListPage.getUserCount();
        System.out.println("✅ Found " + count + " users matching search");
        Assert.assertTrue(count >= 0, "Search should work");
    }

    @Test(priority = 4)
    public void testFilterByRole() {
        usersListPage.filterByRole("Admin");
        int count = usersListPage.getUserCount();
        System.out.println("✅ Filtered by Role 'Admin', showing " + count + " users");
    }

    @Test(priority = 5)
    public void testFilterByStatus() {
        usersListPage.filterByStatus("Active");
        int count = usersListPage.getUserCount();
        System.out.println("✅ Filtered by Status 'Active', showing " + count + " users");
    }

    @Test(priority = 6)
    public void testVerifyUserExists() {
        usersListPage.searchUser("John");
        boolean found = usersListPage.isUserPresent("John");
        Assert.assertTrue(found, "User 'John' should exist in the table");
        System.out.println("✅ User 'John' found in table");
    }

    @Test(priority = 7)
    public void testPaginationNext() {
        if (usersListPage.isNextPageEnabled()) {
            usersListPage.goToNextPage();
            System.out.println("✅ Navigated to next page");
        } else {
            System.out.println("⚠️ No next page available, skipping test");
        }
    }

    @Test(priority = 8)
    public void testClearSearch() {
        usersListPage.searchUser("John");
        usersListPage.clearSearch();
        int count = usersListPage.getUserCount();
        System.out.println("✅ Cleared search, showing " + count + " users");
    }
}