import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
;

public class testme {

	public static void main(String[] args) {

	//This Class is created for a quick test
		
		WebDriverManager.chromedriver().setup();
		WebDriver Driver = new ChromeDriver();
		Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Driver.manage().window().maximize();
		Driver.get("http://automationpractice.com/index.php");
		Actions actions = new Actions(Driver);
		actions.click();
		actions.sendKeys(Keys.ARROW_DOWN);
		
		actions.moveToElement(Driver.findElement(By.xpath("//ul[@id='homefeatured']//div/a[@title ='Blouse']"))).perform();
		actions.moveToElement(Driver.findElement(By.xpath("//ul[@id='homefeatured']//div/a[@title ='Blouse']/ancestor::div[@class='left-block']/following-sibling::div/div[@class='button-container']/a"))).click().build().perform();
//		Driver.findElement(By.xpath("//ul[@id='homefeatured']//div/a[@title ='Blouse']")).click();;
//		List<WebElement> frames = Driver.findElements(By.tagName("iframe"));
//		for (WebElement e : frames){
//			System.out.println(e.getAttribute("name"));//		}
//		//Driver.switchTo().frame("Driver")
		Driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		Set<String> wh = Driver.getWindowHandles();
		for (String s :wh){
			System.out.println(s+"hi");
		}
		Driver.findElement(By.xpath("//a[@title='Continue shopping']")).click();
		actions.moveToElement(Driver.findElement(By.xpath("//ul[@id='homefeatured']//div/a[@title ='Printed Dress']"))).perform();
		actions.moveToElement(Driver.findElement(By.xpath("//ul[@id='homefeatured']//div/a[@title ='Printed Dress']/ancestor::div[@class='left-block']/following-sibling::div/div[@class='button-container']/a"))).click().build().perform();
		Driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
				}
		
	
	
		

}
