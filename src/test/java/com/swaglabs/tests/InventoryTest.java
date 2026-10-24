
package com.swaglabs.tests;

import com.google.gson.JsonObject;
import com.swaglabs.base.BaseTest;
import com.swaglabs.data.DataDriven;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyInventoryElementsAfterSuccessfulLogin() {

        JsonObject data = DataDriven.jsonReader()
                .getAsJsonObject("validLogin");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                data.get("username").getAsString(),
                data.get("password").getAsString()
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/inventory.html"),
                "User should be on the Inventory page"
        );

        InventoryPage inventoryPage =
                new InventoryPage(driver);

        Assert.assertEquals(
                inventoryPage.getPageTitle(),
                "Swag Labs",
                "Incorrect page title"
        );

        Assert.assertTrue(
                inventoryPage.isCartIconDisplayed(),
                "Shopping cart icon should be displayed"
        );

        Assert.assertEquals(
                inventoryPage.getProductCount(),
                6,
                "Inventory page should display six products"
        );
    }
}
