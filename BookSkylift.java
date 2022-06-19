package webTestNybula;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BookSkyLift {

	public static void main(String[] args) {
		// Intilize WebDriver for Chrome with ChromeOption to open in default Profile
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nakul\\Downloads\\chromedriverMy\\chromedriver.exe");
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("user-data-dir=C:\\Users\\nakul\\AppData\\Local\\Google\\Chrome\\User Data");
		opt.addArguments("--start-maximized");
		WebDriver driver=new ChromeDriver(opt);
		
    //passing website url to driver
		String url="https://nbl.one/gigs/listings/careers1/chitkara-university-campus-hiring-software-test-engineer-internship-1";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
    //Getting the Book button and clicking to book the skylift
		WebElement bookButton=driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/section/section[1]/section[2]/section/div/section[2]/section/section[2]/button"));
		System.out.println(bookButton.getText());
		bookButton.click();
		
		driver.quit();
	}

}
