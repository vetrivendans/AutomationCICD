package seleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//div[@class='title' and text()='CVV Code ']//parent::div//input")
	WebElement cvvCodeEle;
	
	@FindBy (xpath="//div[@class='title' and text()='Name on Card ']//parent::div//input")
	WebElement NameOnCardEle;
	
	@FindBy (xpath="//input[@placeholder='Select Country']")
	WebElement countryNameEle;
	
	@FindBy (xpath="//span[contains(@class,'ng-star-inserted')]")
	List<WebElement> listOfCountriesEle;
	
	@FindBy (xpath="//a[text()='Place Order ']")
	WebElement placeOrderEle;
	
	By cvvCodeBy = By.xpath("//div[@class='title' and text()='CVV Code ']//parent::div//input");
	By NameOnCardBy = By.xpath("//div[@class='title' and text()='Name on Card ']//parent::div//input");
	By listOfCountriesBy = By.xpath("//span[contains(@class,'ng-star-inserted')]");
	
	
	public void enterCvv(String cvvValue)
	{	
		waitForElementToAppear(cvvCodeBy);
		cvvCodeEle.sendKeys(cvvValue);
	}
	
	public void enterNameOnCard(String nameOnCardValue)
	{	
		waitForElementToAppear(NameOnCardBy);
		NameOnCardEle.sendKeys(nameOnCardValue);
	}
	
	public void selectCountry(String countryName)
	{
		countryNameEle.sendKeys(countryName);
		waitForElementToAppear(listOfCountriesBy);
		listOfCountriesEle.stream().filter(country->country.getText().trim().equalsIgnoreCase(countryName)).
							findAny().orElse(null).click();
		
	}
	
	
	public OrderConfirmationPage goToOrderConfirmationPage()
	{
		placeOrderEle.click();
		OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
		return orderConfirmationPage;
	}

}
