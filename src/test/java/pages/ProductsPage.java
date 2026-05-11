package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {

    WebDriver driver;
    WebDriverWait wait;

    By searchBox = By.id("search_product");
    By firstAddToCartButton = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    By viewCartLink = By.xpath("//u[text()='View Cart']");

    public ProductsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void makeSureProductsPageIsOpen() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
            System.out.println("Products page already opened");
        } catch (TimeoutException e) {
            System.out.println("Products page not loaded. Opening directly again.");
            driver.get("https://automationexercise.com/products");
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        }
    }

    public void searchProduct(String productName) {
        makeSureProductsPageIsOpen();
        removeAds();

        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(productName);
        search.sendKeys(Keys.ENTER);

        System.out.println("Searched product: " + productName);
    }

    public void addFirstProductToCart() {
        removeAds();

        WebElement addToCart = wait.until(ExpectedConditions.presenceOfElementLocated(firstAddToCartButton));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", addToCart);
        js.executeScript("arguments[0].click();", addToCart);

        System.out.println("First product added to cart");
    }

    public void clickViewCart() {
        removeAds();

        WebElement viewCart = wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", viewCart);

        System.out.println("Clicked View Cart");
    }

    public void removeAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                    "var iframes = document.getElementsByTagName('iframe');" +
                            "for(var i = 0; i < iframes.length; i++){" +
                            "iframes[i].style.display='none';" +
                            "}"
            );

            System.out.println("Ads hidden if present");

        } catch (Exception e) {
            System.out.println("No ads found");
        }
    }
}