//Assignment 2 Amazon
//1.Load the URL https://www.amazon.in/
//2.search as oneplus 9 pro 
//3.Get the price of the first product
//4. Print the number of customer ratings for the first displayed product
//5. Click the first text link of the first image
//6. Take a screen shot of the product displayed
//7. Click 'Add to Cart' button
//8. Get the cart subtotal and verify if it is correct.
//9.close the browser

package week4.assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

public class Activity01 {
	public static void main(String[] args) throws IOException {
		ChromeDriver driver=new ChromeDriver();
		 driver.get("https://www.amazon.in/");
		 driver.manage().window().maximize();


		 driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro" , Keys.ENTER);
		 
		String Price= driver.findElement(By.xpath("(//span[@class='a-price-whole'])")).getText();
		System.out.println("Number of customer rating : "+ driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']")).getText());
		
		driver.findElement(By.xpath("//span[contains(text(),'OnePlus 9 Pro')]")).click();
		
		Set<String> windows = driver.getWindowHandles();
		List<String>wnds = new ArrayList<String>(windows);
		driver.switchTo().window(wnds.get(1));
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/amazonpr.png");
		FileUtils.copyFile(source, dest);
		driver.findElement(By.xpath("//span[text()='Add to Cart']/preceding-sibling::input")).click();
		String subtotal = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println(Price + subtotal);
		if (subtotal.equals(Price)) {
			System.out.println("Amount is matched");
		}else System.out.println("Amount is not matched");
		driver.quit(); 
	}


}
