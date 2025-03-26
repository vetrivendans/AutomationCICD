package seleniumFrameworkDesign.PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;


public class ProductCatalog extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver)
	{
		super(driver);
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'mb-3')]")
	List<WebElement> productsListEle;
	
	
	By productsBy = By.xpath("//div[contains(@class,'mb-3')]");
	By addToCartBy = By.xpath("//div[@class='card-body']//button[last()]");
	By toastContainerBy = By.xpath("//div[@id='toast-container']");
	By spinnerBy = By.cssSelector(".nngx-spinner-overlay");
	
	public By getXpathForAddToCartBasedOnProductName(String productName)
	{
		
		return By.xpath("//b[text()='"+productName+"']/parent::h5/parent::div//button[text()=' Add To Cart']");
		
	}
	
	//get product list
	public List<WebElement> getProductList()
	{
		waitForListOfWebElementsToAppear(productsListEle);
		return productsListEle;
	}
	/*
	public WebElement getProduct(String productName)
	{
	System.out.println("to select product ->"+productName);
	List<WebElement> getProductListEle = getProductList();
	
	return getProductListEle.stream().filter(
			product-> product.findElement(By.xpath("")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
			//findAny().orElse(null);
	}
	*/
	
	
	
	public void addToCart(String productName) throws InterruptedException
	{
		driver.findElement(getXpathForAddToCartBasedOnProductName(productName)).click();
		waitForElementToAppear(toastContainerBy);
		//waitForElementToDisappear(spinnerBy);
		Thread.sleep(1000);
		
	}
	
	

}
