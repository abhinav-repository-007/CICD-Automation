package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.data.DataReaderFromJsonFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("username"), input.get("password"));

        List<WebElement> products = productCatalogue.getProducts();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.verifyProductDisplayed(productName);
        Assert.assertTrue(match);

        CheckOutPage checkOutPage = cartPage.goTocheckOut();
        checkOutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();

        String expected = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(expected.equalsIgnoreCase("Thankyou for the order."));


    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("abhinav007@gmail.com", "Pass@Word1");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyProductDisplayed(productName));


    }


    @DataProvider
    public Object[][] getData() throws IOException {
        /*HashMap<String, String> map = new HashMap<>();
        map.put("username", "abhinav007@gmail.com");
        map.put("password", "Pass@Word1");
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", "anshika@gmail.com");
        map1.put("password", "Iamking@000");*/
        List<HashMap<String, String>> list = DataReaderFromJsonFile.getJsonDataToMap();
        Object[][] data = new Object[][]{{list.get(0)}, {list.get(1)}};
        return data;

    }
}
