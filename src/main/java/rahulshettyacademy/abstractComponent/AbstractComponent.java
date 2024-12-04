package rahulshettyacademy.abstractComponent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {
    @FindBy(css = "[routerlink*='myorders']")
    public WebElement goToMyOrderIcon;
    WebDriver driver;
    @FindBy(css = "[routerlink*='cart']")
    WebElement goToCartIcon;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void waitToBeElementAppear(By findby) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForWebElementAppear(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeDisappear(WebElement ele) throws InterruptedException {

        /*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.invisibilityOf(ele));*/
        Thread.sleep(3000);
    }

    public CartPage goToCartPage() {
        goToCartIcon.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage() {
        goToMyOrderIcon.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
}
