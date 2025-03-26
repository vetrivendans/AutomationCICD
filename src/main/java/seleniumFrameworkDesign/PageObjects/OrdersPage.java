package seleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//tbody//td[2]")
	List<WebElement> productNameEle;
	
	public Boolean verifyProductsName(String productName)
	{
		waitForListOfWebElementsToAppear(productNameEle);
		Boolean match = productNameEle.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
