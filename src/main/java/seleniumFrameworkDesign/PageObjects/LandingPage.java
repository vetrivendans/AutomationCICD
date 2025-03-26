package seleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{	
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userEmailEle;
	
	@FindBy(id="userPassword")
	WebElement userPasswordEle;
	
	@FindBy(id="login")
	WebElement loginEle;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessageEle;

	
	
	//Action Methods
	
	//go to URL
	public void goToUrl()
	{
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	//login to application
	public ProductCatalog loginToApplication(String userEmail, String userPassword)
	{
		System.out.println(userEmail+"   "+userPassword);
		userEmailEle.sendKeys(userEmail);
		userPasswordEle.sendKeys(userPassword);
		loginEle.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;	
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessageEle);
		return errorMessageEle.getText();
		
		//div[@aria-label='Incorrect email or password.']
		
		
	}
	
}
