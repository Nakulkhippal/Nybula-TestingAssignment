package webTestNybula;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class QueryValidation {

	public static void main(String[] args) {
		// Initializing WebDriver for Chrome using ChromeOption for default profile
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nakul\\Downloads\\chromedriverMy\\chromedriver.exe");
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("user-data-dir=C:\\Users\\nakul\\AppData\\Local\\Google\\Chrome\\User Data");
		opt.addArguments("--start-maximized");
		WebDriver driver=new ChromeDriver(opt);
		
    //passing the website url to the driver
		String url="https://nbl.one/";
		driver.get(url);
    //wait for site to load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    
    //get no. of quests
		int questCount=driver.findElements(By.cssSelector("section[id*='quest-card-']")).size();
    
		if(questCount==0)
			System.out.println("No Quest Present");
		else
			System.out.println("Yes, "+questCount+" Quests are Present");
    
		driver.quit();
		
	}

}
