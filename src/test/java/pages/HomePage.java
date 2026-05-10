package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    By signupLoginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    By productsLink = By.cssSelector("a[href='/products']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
        System.out.println("Clicked Signup / Login link");
    }

    public void clickProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
        System.out.println("Clicked Products link");
    }
}
