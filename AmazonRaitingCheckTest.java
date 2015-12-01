package amazonpackage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition; 
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait; 

public class AmazonRaitingCheckTest {

	static ProfilesIni allProfiles = new ProfilesIni();
	static FirefoxProfile profile = allProfiles.getProfile("Selenium");
	static WebDriver driver = new FirefoxDriver(profile); 
	static WebDriverWait wait = new WebDriverWait(driver, 10);
	
	public static void main(String[] args) {
		AmazonRaitingCheck();
	}

	public static void AmazonRaitingCheck() {
		// comment: please make sure browser cache is disabled.				
				driver.get("http://www.google.com");
				
				WebElement googleSearchfield = driver.findElement(By.name("q")); 
				googleSearchfield.sendKeys("Amazon"); 	
				googleSearchfield.submit();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				
				clickAnElementByLinkText("Amazon.com: Online Shopping");
				WebElement amazonSearchBox = driver.findElement(By.id("twotabsearchtextbox")); 
				amazonSearchBox.sendKeys("apple watch"); 
				amazonSearchBox.submit();
				
				clickAnElementByLinkText("Apple Watch Sport 42mm Space Gray Aluminum Case with Black Sport Band");
				WebElement rating=driver.findElement(By.xpath(".//*[@class='a-icon a-icon-star a-star-4-5']"));
				Actions builder=new Actions(driver);
				builder.moveToElement(rating).perform();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
				if(driver.findElement(By.xpath(".//div[@class='a-popover-inner']//span[@class='a-size-base a-color-secondary']")).getText().equalsIgnoreCase("4.5 out of 5 stars"))
				{
				System.out.print("Product has 4.5 starts review");
				}
				else
				{
				System.out.print("Product doens't have 4.5 starts review");
				}
				driver.quit(); 
			}
	
	static public void clickAnElementByLinkText(String linkText) {
		  new WebDriverWait(driver, 25).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(linkText)));
		  driver.findElement(By.partialLinkText(linkText)).click();
		}
		 }
