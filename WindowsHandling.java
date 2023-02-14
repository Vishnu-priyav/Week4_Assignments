package week4.activity;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsHandling {

	public static void main(String[] args) {
		/*
		 * //Pseudo Code
		 * 
		 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		 * 
		 * 2. Enter UserName and Password Using Id Locator
		 * 
		 * 3. Click on Login Button using Class Locator
		 * 
		 * 4. Click on CRM/SFA Link
		 * 
		 * 5. Click on contacts Button
		 * 	
		 * 6. Click on Merge Contacts using Xpath Locator
		 * 
		 * 7. Click on Widget of From Contact
		 * 
		 * 8. Click on First Resulting Contact
		 * 
		 * 9. Click on Widget of To Contact
		 * 
		 * 10. Click on Second Resulting Contact
		 * 
		 * 11. Click on Merge button using Xpath Locator
		 * 
		 * 12. Accept the Alert
		 * 
		 * 13. Verify the title of the page
		 */
		
        ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();	
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.xpath("//div[@class='x-panel-header']/a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//ul[@class='shortcuts']//a[text()='Merge Contacts']")).click();
		
		
		//Find the From Contact Widget
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following-sibling::a")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		String mergecontacts = driver.getWindowHandle();
		System.out.println(mergecontacts);   // prints the CDWindow2
		
		
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles1);	
		driver.switchTo().window(windows.get(1));		
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(mergecontacts);
		
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a")).click();
//		String windowHandle2 = driver.getWindowHandle();
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> windows2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(windows2.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
	   
	   
	    
	    driver.switchTo().window(mergecontacts);
	    
	   driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
	   

	  		Alert alert1 = driver.switchTo().alert();
	  		System.out.println(alert1.getText());
//	  		driver.getErrorHandler();
	  		alert1.accept();	  			
	  	    System.out.println(driver.getTitle());
	   
	}

}
