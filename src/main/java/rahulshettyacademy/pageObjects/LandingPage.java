package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(id = "userEmail")
    WebElement usermail;

    @FindBy(id = "userPassword")
    WebElement userpassword;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = ".flyInOut")
    WebElement errorMessage;

    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProductCatalogue loginApplication(String username, String password) {
        usermail.sendKeys(username);
        userpassword.sendKeys(password);
        loginButton.click();
        return new ProductCatalogue(driver);
    }

    public String getErrorMessage() {
        waitForWebElementAppear(errorMessage);
        return errorMessage.getText();
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
