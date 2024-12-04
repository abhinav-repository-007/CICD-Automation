package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;

import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups = {"errorValidations"})
    public void loginErrorValidation() {
        String productName = "ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue = landingPage.loginApplication("xxxxabhinav007@gmail.com", "Pass@Word1");
        // String actual = landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", "Incorrect email or password.");
    }

    @Test(groups = {"errorValidations"})
    public void producterrorValidation() throws InterruptedException {
        String productName = "ADIDAS ORIGINAL";
        ProductCatalogue productCatalogue = landingPage.loginApplication("abhinav007@gmail.com", "Pass@Word1");

        List<WebElement> products = productCatalogue.getProducts();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplayed("ADIDAS ORIGINAL132245");
        Assert.assertFalse(match);

    }
}
