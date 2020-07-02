package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;

public class MergeLead {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Launch URL
		driver.navigate().to("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Enter UserName and Password Using Id Locator
		driver.findElementById("username").sendKeys("demosalesmanager",Keys.TAB);
		driver.findElementById("password").sendKeys("crmsfa",Keys.TAB);
		//Click on Login Button using Class Locator
		driver.findElementByClassName("decorativeSubmit").click();
		//Click on CRM/SFA Link
		driver.findElementByLinkText("CRM/SFA").click();
		
		//Click on contacts Button
		driver.findElementByLinkText("Contacts").click();
		//Click on Merge Contacts using Xpath Locator
		driver.findElementByXPath("(//ul[@class='shortcuts'])//li[4]//a").click();
	
		//Click on Widget of From Contact
		driver.findElementByXPath("//table[@class='twoColumnForm']//img").click();
		//Click on First Resulting Contact
		Set<String> childWindow = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(childWindow);
		driver.switchTo().window(list.get(1));
		System.out.println("Title of the child(From contact) window: " +driver.getTitle());
		driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[1]").click();
				
		//Click on Widget of To Contact
		driver.switchTo().window(list.get(0));
		driver.findElementByXPath("(//input[@id='partyIdTo'])//following::a[1]").click();
		//Click on Second Resulting Contact
		Set<String> childWindow1 = driver.getWindowHandles();
		List<String> list1 = new ArrayList<String>(childWindow1);
		driver.switchTo().window(list1.get(1));
		System.out.println("Title of the child(To contact) window: " +driver.getTitle());
		driver.findElementByXPath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']//a)[2]").click();
		driver.switchTo().window(list1.get(0));
		
		//Click on Merge button using Xpath Locator
		driver.findElementByXPath("//input[@id='partyIdTo']//following::tr[2]//td[2]//a").click();
		//Accept the Alert
		System.out.println("Alert msg is: "+ driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		//Verify the title of the page
		System.out.println("Title of the Homewindow: "+ driver.getTitle());
		driver.close();
	}

}

/*
 * OUTPUT: 
 * Title of the child(From contact) window: Find Contacts 
 * Title of the child(To contact) window: Find Contacts 
 * Alert msg is: Are you sure? 
 * Title of the Homewindow: Merge Contacts | opentaps CRM
 */
