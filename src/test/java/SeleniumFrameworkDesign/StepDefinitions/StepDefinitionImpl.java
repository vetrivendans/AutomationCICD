package SeleniumFrameworkDesign.StepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.PageObjects.CartPage;
import seleniumFrameworkDesign.PageObjects.CheckOutPage;
import seleniumFrameworkDesign.PageObjects.LandingPage;
import seleniumFrameworkDesign.PageObjects.OrderConfirmationPage;
import seleniumFrameworkDesign.PageObjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public CartPage cartPage;
	public CheckOutPage checkOutPage;
	public OrderConfirmationPage orderConfirmationPage;

	String cvv = "123";
	String nameOnCard = "Testing user";
	String countryName = "India";

	@Given("I landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	
	//Given Logged in with <userName> and <password> to application
	@Given("^Logged in with (.+) and (.+) to application$")
	public void Logged_In_With_UserName_and_Password_to_application(String userName, String password) {
		// get Product catalog class from landing page
		System.out.println("Step definition executed for user: " + userName);
		productCatalog = landingPage.loginToApplication(userName, password);
	}
	//When I add product <productName> to the cart
	
	@When("^I add product (.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException {
		System.out.println("Step definition executed for productName: " + productName);
		productCatalog.addToCart(productName);
	}

	@And("^I checkout the (.+) from cart$")
	public void I_checkout_the_Product_from_cart(String productName) throws InterruptedException {
		cartPage = productCatalog.clickOnCart();

		// Find the products in cart page
		Boolean match = cartPage.getCartProducts(productName);
		Assert.assertTrue(match);

		// Checkout
		checkOutPage = cartPage.goToCheckOut();
	}

	@And("I submit the order")
	public void I_submit_the_order() {
		// Checkout Page
		// Enter CVV Code
		checkOutPage.enterCvv(cvv);
		// Enter Name on card
		checkOutPage.enterNameOnCard(nameOnCard);
		// select country India
		checkOutPage.selectCountry(countryName);
		// go to order confirmation page
		orderConfirmationPage = checkOutPage.goToOrderConfirmationPage();

	}

	@Then("I verify the {string} message is displayed")
	public void I_verify_the_Thankyou_for_the_order_message_is_displayed(String string) {
		// verify the order confirmation message
		AssertJUnit.assertTrue(
				orderConfirmationPage.getOrderConfirmationMessage().equalsIgnoreCase(string));
		System.out.println(orderConfirmationPage.getOrderConfirmationMessage());
		driver.close();
	}
	
	@Then("I verify the error {string} message is displayed")
	public void I_verify_the_error_message_is_displayed(String string) {
		// verify the order confirmation message
		Assert.assertEquals(landingPage.getErrorMessage(), string);
		driver.close();
	}

}
