package SeleniumFrameworkDesign.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.TestComponents.RetryAnalyzer;
import seleniumFrameworkDesign.PageObjects.CartPage;
import seleniumFrameworkDesign.PageObjects.CheckOutPage;
import seleniumFrameworkDesign.PageObjects.LandingPage;
import seleniumFrameworkDesign.PageObjects.OrderConfirmationPage;
import seleniumFrameworkDesign.PageObjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test (groups = {"ErrorHandling"}, retryAnalyzer = RetryAnalyzer.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	
	{
		//get Product catalog class from landing page
		ProductCatalog productCatalog = landingPage.loginToApplication("incorrect@user.com", "IncorrectPassword1");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		

	}
	
	
	@Test (groups = {"ErrorHandling"}, retryAnalyzer = RetryAnalyzer.class)
	public void ProductErrorValidation() throws InterruptedException
	{
				//get Product catalog class from landing page
				ProductCatalog productCatalog = landingPage.loginToApplication("testing@user.com", "Password1");
				
				//Add a product to cart
				String productName = "ZARA COAT 3";
				productCatalog.addToCart(productName);
				CartPage cartPage = productCatalog.clickOnCart();
				
				//Find the products in cart page
				Boolean match = cartPage.getCartProducts("ZARA COAT 33");
				Assert.assertFalse(match);
				
	}

}
