package com.swaglabs.tests;

import com.google.gson.JsonObject;
import com.swaglabs.base.BaseTest;
import com.swaglabs.data.DataDriven;
import com.swaglabs.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void verifySuccessfulLogin() {

        JsonObject data = DataDriven.jsonReader()
                .getAsJsonObject("validLogin");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                data.get("username").getAsString(),
                data.get("password").getAsString()
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to the Inventory page"
        );
    }

    @Test
    public void verifyInvalidLogin() {

        JsonObject data = DataDriven.jsonReader()
                .getAsJsonObject("invalidLogin");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                data.get("username").getAsString(),
                data.get("password").getAsString()
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().contains(
                        "Username and password do not match"),
                "Expected an invalid credentials error"
        );
    }

    @Test
    public void verifyLoginWithoutPassword() {

        JsonObject data = DataDriven.jsonReader()
                .getAsJsonObject("validLogin");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                data.get("username").getAsString());

        loginPage.enterPassword("");

        loginPage.clickLogin();

        Assert.assertTrue(
                loginPage.getErrorMessage().contains(
                        "Password is required"),
                "Expected a password-required error"
        );
    }
}
