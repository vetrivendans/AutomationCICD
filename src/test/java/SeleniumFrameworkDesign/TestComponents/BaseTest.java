package SeleniumFrameworkDesign.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumFrameworkDesign.PageObjects.LandingPage;

public class BaseTest {
	
	protected WebDriver driver;
	protected LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	{	
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+
				"//src//main//java//seleniumFrameworkDesign//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		
		if(browserName.contains("Chrome"))
		{
		ChromeOptions options = new ChromeOptions();
		if(browserName.contains("headless"))
		{
		options.addArguments("--headless");
		}
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));
		
		}else
			if(browserName.equalsIgnoreCase("firefox"))
			{
				driver = new FirefoxDriver();
			}
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;


	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		
	//Read JSON file values to String	
	String jsonContent = FileUtils.readFileToString(
						new File(filePath)
						,StandardCharsets.UTF_8);
	
	//String to HashMap Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			});
	
	return data;
	
	
	}
	
	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
	    if (testCaseName == null || testCaseName.trim().isEmpty()) {
	        throw new IllegalArgumentException("Test case name cannot be null or empty");
	    }

	    // Ensure driver is not null
	    if (driver == null) {
	        throw new NullPointerException("WebDriver instance is null");
	    }

	    // Take screenshot
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    // Use File.separator for cross-platform compatibility
	    String filePath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + ".png";
	    File destination = new File(filePath);

	    // Copy the screenshot to the destination
	    FileUtils.copyFile(source, destination);

	    return filePath; // Return the file path
	}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToUrl();
		return landingPage;
	}
	
	@AfterMethod (alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	
}