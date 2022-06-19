package webTestNybula;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//CardData class to store infomation of individual Card
class CardData{
	String title;
	String price;
	String link;
	public CardData(String title, String price, String link){
		this.title=title;
		this.price=price;
		this.link=link;
	}
	public void ViewCard() {
		System.out.println("Title: "+title);
		System.out.println("Price: "+price);
		System.out.println("Link: "+link);
		System.out.println();
	}
}


public class ScrapCardData {
	public static void main(String[] args) {
		// Intializing Webdriver for Chrome with special Chrome option to open default profile in chrome
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nakul\\Downloads\\chromedriverMy\\chromedriver.exe");
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("user-data-dir=C:\\Users\\nakul\\AppData\\Local\\Google\\Chrome\\User Data");
		opt.addArguments("--start-maximized");
		WebDriver driver=new ChromeDriver(opt);
		
    //passing website url to the driver
		String url="https://nbl.one/listings";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
    //intializing a HashSet for validation that no card gets repeated
		Set<String> cards=new HashSet<>();
		List<WebElement> supp=new ArrayList<>();
		
    //casting driver to JavascriptExcuter 
		JavascriptExecutor js=(JavascriptExecutor)driver;
    
    //scrolling to get all cards into list
		int scrolls=5;
		int scrollheight=0;
		for(int i=1;i<=scrolls;i++) {
			js.executeScript("window.scrollBy(0,"+scrollheight+")","");
			List<WebElement> list=driver.findElements(By.cssSelector("div[class*=\"slick-slide slick-active slick-current\"]"));
			try {
				Thread.sleep(2500);
			}catch(Exception ex) {};
			supp.addAll(list);
			scrollheight+=300;
		}
		
    //Iterating through list and processing each Card
		for(WebElement e:supp) {
      
      //checking if card is already processed
			if(cards.contains(e.getText())) {
				continue;
			}else {
				cards.add(e.getText());
			}
			
			String[] lines=e.getText().split("\n");
			if(lines.length<5) {
				continue;
			}
			String link=getLink(e,driver,lines[0].trim()+" "+lines[1].trim(),lines[2]);
			String priceLine=lines[lines.length-2];
			priceLine=priceLine.replace('$', '\n');;
			String[] priceLinePieces=priceLine.split("\n");
			CardData card=new CardData(lines[0].trim()+" "+lines[1].trim(),"$"+priceLinePieces[priceLinePieces.length-1],link);
			card.ViewCard();
			System.out.println();
		}
		
		driver.quit();
	}
  
  //function to make link
	public static String getLink(WebElement x,WebDriver driver,String title,String by) {
		String[] byPieces=by.split(" ");
		by=byPieces[1];
		byPieces=by.split("@");
		by=byPieces[0];
		byPieces=by.split(",");
		by=byPieces[0];
		String link="https://nbl.one/gigs/listings/"+by+"1/";
		title=title.replace(' ', '-');
		link+=title;
		link+="-1";
		return link;
	}
}
