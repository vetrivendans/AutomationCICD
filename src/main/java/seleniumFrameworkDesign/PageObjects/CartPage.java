package seleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cartSection']//h3")
	List<WebElement> cartProductsEle;
	
	@FindBy(xpath="//button[@class='btn btn-primary' and text()='Checkout']")
	WebElement checkOutEle;
	
	By cartProductsBy = By.xpath("//div[@class='cartSection']//h3");
	By checkOutBy = By.xpath("//button[@class='btn btn-primary' and text()='Checkout']");
	
	public Boolean getCartProducts(String productName) throws InterruptedException
	{
		waitForElementToAppear(cartProductsBy);
		Boolean match = cartProductsEle.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	public CheckOutPage goToCheckOut()
	{
		waitForElementToAppear(checkOutBy);
		checkOutEle.click();
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
