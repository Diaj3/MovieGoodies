// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class RegistercustomerTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void registercustomer() {
    driver.get("http://localhost:8000/user/register");
    driver.manage().window().setSize(new Dimension(1853, 1053));
    driver.findElement(By.id("firstName")).click();
    driver.findElement(By.id("firstName")).sendKeys("Daniel");
    driver.findElement(By.id("lastName")).sendKeys("Pinto");
    driver.findElement(By.id("lastName")).click();
    driver.findElement(By.id("lastName")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("email1@email.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("password");
    driver.findElement(By.id("matchingPassword")).click();
    driver.findElement(By.id("matchingPassword")).sendKeys("password");
    driver.findElement(By.cssSelector(".flex-c-m")).click();
  }
}
