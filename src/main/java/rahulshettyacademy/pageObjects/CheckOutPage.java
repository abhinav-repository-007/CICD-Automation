package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".action__submit")
    WebElement submitButton;
    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;
    @FindBy(css = ".ta-results button:last-of-type")
    WebElement selectIndia;
    By results = By.cssSelector(".ta-results");

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String country1) {
        Actions a = new Actions(driver);
        a.sendKeys(country, country1).build().perform();
        waitToBeElementAppear(results);
        selectIndia.click();

    }

    public ConfirmationPage submitOrder() {
        submitButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
