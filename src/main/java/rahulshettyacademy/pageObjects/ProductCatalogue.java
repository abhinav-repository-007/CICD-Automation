package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponent.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".mb-3")
    List<WebElement> products;
    @FindBy(css = ".ng-animating")
    WebElement spinner;

    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".mb-3 button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProducts() {
        waitToBeElementAppear(productBy);
        return products;
    }

    public WebElement getProductByname(String productName) {
        WebElement prod = getProducts().stream().filter(product -> product.findElement(By.cssSelector("b")).getText()
                .equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement p = getProductByname(productName);
        p.findElement(addToCart).click();
        waitToBeElementAppear(toastMessage);
        waitForElementToBeDisappear(spinner);
    }


}
