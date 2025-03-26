package seleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameworkDesign.PageObjects.CartPage;
import seleniumFrameworkDesign.PageObjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	By cartBy = By.xpath("//button[@routerlink='/dashboard/cart']");
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement myOrdersEle;
	
	
	
	

	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForListOfWebElementsToAppear(List<WebElement> elements)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public void waitForElementToDisappear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".nngx-spinner-overlay")));
	}
	
	public CartPage clickOnCart()
	{
		waitForElementToAppear(cartBy);
		driver.findElement(cartBy).click();
		
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage clickOnOrders()
	{
		waitForWebElementToAppear(myOrdersEle);
		myOrdersEle.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

}
