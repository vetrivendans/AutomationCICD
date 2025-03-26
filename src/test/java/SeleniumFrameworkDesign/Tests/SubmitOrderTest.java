package SeleniumFrameworkDesign.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import seleniumFrameworkDesign.PageObjects.CartPage;
import seleniumFrameworkDesign.PageObjects.CheckOutPage;
import seleniumFrameworkDesign.PageObjects.LandingPage;
import seleniumFrameworkDesign.PageObjects.OrderConfirmationPage;
import seleniumFrameworkDesign.PageObjects.OrdersPage;
import seleniumFrameworkDesign.PageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test (dataProvider = "getData", groups = "Purchase")
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	
	{
		//get Product catalog class from landing page
		ProductCatalog productCatalog = landingPage.loginToApplication(input.get("userName"), input.get("password"));
		
		//Add a product to cart
		
		productCatalog.addToCart(input.get("productName"));
		CartPage cartPage = productCatalog.clickOnCart();
		
		//Find the products in cart page
		Boolean match = cartPage.getCartProducts(input.get("productName"));
		Assert.assertTrue(match);
		
		//Checkout
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		
		String cvv = "123";
		String nameOnCard = "Testing user";
		String countryName = "India";
		//Checkout Page
		//Enter CVV Code
		checkOutPage.enterCvv(cvv);
		//Enter Name on card
		checkOutPage.enterNameOnCard(nameOnCard);
		//select country India
		checkOutPage.selectCountry(countryName);
		//go to order confirmation page
		OrderConfirmationPage orderConfirmationPage = checkOutPage.goToOrderConfirmationPage();
		//verify the order confirmation message
		String orderConfirmationMessage = "Thankyou for the order.";
		AssertJUnit.assertTrue(orderConfirmationPage.getOrderConfirmationMessage().equalsIgnoreCase(orderConfirmationMessage));
		
		System.out.println(orderConfirmationPage.getOrderConfirmationMessage());

	}
	
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest()
	{
		//get Product catalog class from landing page
		ProductCatalog productCatalog = landingPage.loginToApplication("testing@user.com", "Password1");
		OrdersPage ordersPage = productCatalog.clickOnOrders();
		Boolean match = ordersPage.verifyProductsName(productName);
		Assert.assertTrue(match);
	}
	
	
	
	
	
	@DataProvider
	public  Object[][] getData() throws IOException
	{
		
		/*
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("userName", "testing@user.com");
		map.put("password", "Password1");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("userName", "testing1@user1.com");
		map1.put("password", "Password1");
		map1.put("productName", "ADIDAS ORIGINAL");
		*/
		
		//
		
		List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFrameworkDesign//Data//Purchase.json");
		
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	

}
