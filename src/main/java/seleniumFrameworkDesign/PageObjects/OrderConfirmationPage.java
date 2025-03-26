package seleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent{

	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//h1")
	WebElement orderMessageEle;
	
	By orderMessageBy = By.xpath("//h1");
	
	public String getOrderConfirmationMessage()
	{
		waitForElementToAppear(orderMessageBy);
		String orderMessage = orderMessageEle.getText().trim();
		return orderMessage;
	}
	

}
