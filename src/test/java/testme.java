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
        WebDriver webDriver = new ChromeDriver();
        //Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.google.com");
        Actions actions = new Actions(webDriver);
        actions.click();
        actions.sendKeys(Keys.ARROW_DOWN);

        webDriver.quit();
    }


}
