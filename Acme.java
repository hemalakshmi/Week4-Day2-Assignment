package week4.day2;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Acme {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Launch URL
		driver.get("https://acme-test.uipath.com/account/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Enter UserName (kumar.testleaf@gmail.com) and TAB
		driver.findElementById("email").sendKeys("kumar.testleaf@gmail.com",Keys.TAB);
		//Enter Password (leaf@12)
		driver.findElementById("password").sendKeys("leaf@12",Keys.TAB);
		//Click Login
		driver.findElementById("buttonLogin").click();
		//Mouse Over on Vendors
		Actions builder=new Actions(driver);
		WebElement eleVendor = driver.findElementByXPath("//button[text()=' Vendors']");
		builder.moveToElement(eleVendor).perform();
		//Click Search Vendor
		driver.findElementByLinkText("Search for Vendor").click();
		//Enter Vendor Name (Blue Lagoon)
		driver.findElementById("vendorName").sendKeys("Blue Lagoon");
		//Click Search
		driver.findElementById("buttonSearch").click();
		
		//Find the Country Name based on the Vendor
		String text = driver.findElementByXPath("//table//tr//td[contains(text(),'Blue Lagoon')]//following::td[4]").getText();
		System.out.println("County name of the vendor: " + text);
		
		//Click Log Out
		driver.findElementByLinkText("Log Out").click();
		//Close browser
		driver.close();
	}

}

/*
 * OUTPUT: County name of the vendor: France
 */