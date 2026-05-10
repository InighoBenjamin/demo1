package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.CSVUtils;

public class AutomationExerciseTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return CSVUtils.readCSV("testdata/loginData.csv");
    }

    @Test(dataProvider = "loginData")
    public void invalidLoginTest(String email, String password) {

        HomePage homePage = new HomePage(driver, wait);
        LoginPage loginPage = new LoginPage(driver, wait);

        homePage.clickSignupLogin();

        loginPage.login(email, password);

        String actualError = loginPage.getErrorMessage();

        Assert.assertEquals(actualError, "Your email or password is incorrect!");

        System.out.println("Invalid login test passed");
    }

    @Test
    public void searchAndAddProductToCartTest() {

        HomePage homePage = new HomePage(driver, wait);
        ProductsPage productsPage = new ProductsPage(driver, wait);

        homePage.clickProducts();

        productsPage.searchProduct("dress");
        productsPage.addFirstProductToCart();
        productsPage.clickViewCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"));

        System.out.println("Product cart test passed");
    }
}
