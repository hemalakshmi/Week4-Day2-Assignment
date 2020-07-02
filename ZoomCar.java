package week4.day2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ZoomCar {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		//Launch URL
		driver.navigate().to("https://www.zoomcar.com/chennai");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click on the Start your wonderful journey link
		driver.findElementByLinkText("Start your wonderful journey").click();
		//In the Search page, Click on any pick up point under POPULAR PICK-UP
		driver.findElementByXPath("(//div[@class='items'])[1]").click();
		//Click on the Next button
		driver.findElementByXPath("//button[text()='Next']").click();
		
		//Specify the Start Date as tomorrow Date
		Date date = new Date(); // Get the current date
		DateFormat sdf = new SimpleDateFormat("dd"); //Get only the date (and not month, year, time etc)
		String today = sdf.format(date); // Get today's date
		int tomorrow = Integer.parseInt(today)+1; // Convert to integer and add 1 to it
		System.out.println("Tomorrow's date is: " + tomorrow);
		driver.findElementByXPath("//div[contains(text(),'"+ tomorrow +"')]").click();
		String date1=Integer.toString(tomorrow);
		System.out.println("String converted date is: "+date1);
		List<WebElement> text = driver.findElementsByXPath("//div[@class='days']/div");
		for(WebElement webElement: text) {
			if(webElement.getAttribute("innerHTML").contains(date1)){
				webElement.click();
				break;
			}
		}
		
		//Click on the Next Button
		driver.findElementByXPath("//button[text()='Next']").click();
		
		//Confirm the Next day of Start Date and Click on the Done button
		String pickUpDate = driver.findElementByXPath("(//div[@class='label time-label'])[1]").getText();
		System.out.println("Pick up date displayed is: "+ pickUpDate);
		if(pickUpDate.contains(date1)) {
			System.out.println("Selected start date is displayed here successfully");
		}else {
			System.out.println("Selected start date is not displayed");
		}
		driver.findElementByXPath("//button[@class='proceed']").click();
		
			
		//In the result page, capture the number of results displayed.
		List<WebElement> noOfResults = driver.findElementsByXPath("//div[@class='car-listing']//h3");
		int size = noOfResults.size();
		System.out.println("Number of Car Results displayed: "+ size);
		
		//Find the highest priced car ride using Map Interface.
		List<WebElement> keyList = driver.findElements(By.xpath("//div[@class='car-listing']//h3"));
		List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='car-book']//div//div[1]"));
		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
		for(int i=0;i<priceList.size();i++) {
			String keyText = keyList.get(i).getText();
			String priceText = priceList.get(i).getText().replaceAll("[^0-9]", "");
	   		map.put(keyText,(Integer.parseInt(priceText)));
		}
		   for(Entry<String,Integer>eachEntry:map.entrySet()) {
	        	System.out.println(eachEntry.getKey() + "->" + eachEntry.getValue());
	        }
		int maximumValue = Collections.max(map.values());
        System.out.println("Maximum Price of the Car and name is : ");
        for(Entry<String,Integer>eachEntry:map.entrySet()) {
        if(eachEntry.getValue()==maximumValue) {
        	String str=eachEntry.getKey();
        	System.out.println(eachEntry.getKey() + "->" + eachEntry.getValue());
        	Thread.sleep(3000);
        	//Click on the Book Now button for it.
        	driver.findElementByXPath("//h3[contains(text(),'"+ str +"')]//following::button[1]").click();
        }
        }
     	
		Thread.sleep(3000);
		//Close the Browser.
		driver.close();
	}

}

/*
 * OUTPUT: Tomorrow's date is: 4 
 * String converted date is: 4 
 * Pick up date displayed is: Sat 4 Jul, 2020 01:00early morning 
 * Selected start date is displayed here successfully 
 * Number of Car Results displayed: 12
 * Volkswagen Polo (Petrol)->1576 Mahindra KUV 100->1484 Ford Ecosport->1908 Mahindra
 * Scorpio->2124 Hyundai i20->1718 Tata Tiago->1347 Hyundai Verna->1727 Maruti
 * Swift AT->1696 Maruti Brezza->1782 Maruti Baleno->1593 Ford Freestyle->1696
 * Ford Figo->1351 
 * Maximum Price of the Car and name is : Mahindra Scorpio->2124
 */