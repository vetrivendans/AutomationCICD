package SeleniumFrameworkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		String productName = "ZARA COAT 3";
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		
		driver.findElement(By.id("userEmail")).sendKeys("testing@user.com");
		
		driver.findElement(By.id("userPassword")).sendKeys("Password1");
		
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(
				product-> product.findElement(By.cssSelector("b")).getText().contains(productName)).findAny().orElse(null);
		
		prod.findElement(By.xpath("//div[@class='card-body']//button[last()]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='toast-container']")));
		
		
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".nngx-spinner-overlay")));
		
		
		WebElement cart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")));
		cart.click();
		
		
		List<WebElement> cartProducts = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//div[@class='cartSection']//h3"))));
		
		
		
		
		Boolean match = cartProducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		WebElement checkOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-primary' and text()='Checkout']")));
		
		checkOut.click();
		
		
		WebElement cvvCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='title' and text()='CVV Code ']//parent::div//input")));
		cvvCode.sendKeys("123");
		
		//Name on Card
		WebElement nameOnCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='title' and text()='Name on Card ']//parent::div//input")));
		nameOnCard.sendKeys("Testing user");
		
		/*
		//Apply Coupon 
		WebElement applyCoupon = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='title' and text()='Apply Coupon']//parent::div//input")));
		applyCoupon.sendKeys("Test");
		
		WebElement applyCouponButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text() = 'Apply Coupon ']")));
		applyCouponButton.click();
		*/
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ng-star-inserted' and text()=' India']")));
		
		List<WebElement> countries = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[contains(@class,'ng-star-inserted')]")));
		
		countries.stream().filter(country->country.getText().trim().equalsIgnoreCase("India")).findAny().orElse(null).click();
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		WebElement thankYou = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1")));
		
		Assert.assertTrue(thankYou.getText().trim().equalsIgnoreCase("Thankyou for the order."));
		
		System.out.println(thankYou.getText());
		
		

	}

}
