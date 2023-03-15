import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class herokuappUITestAutomation {

    WebDriver driver;

    @BeforeClass
     void setupClass() {
        // to set up driver
        WebDriverManager.safaridriver().setup();
    }

    @BeforeMethod
    void setupTest() {
        // to define the driver
        driver = new SafariDriver();
        // navigates to the-internet.herokuapp.com
        driver.get("https://the-internet.herokuapp.com");
        // waits till 5 seconds each element if the element not found in DOM
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // to maximize window
        driver.manage().window().maximize();
    }

    @Test
    public void testAdd_N_Number_Off_Elements(){

        // click Add/Remove Elements link
        driver.findElement(By.linkText("Add/Remove Elements")).click();

      //Verify whether Add Element button is displayed  .
        boolean addElement = driver.findElement(By.xpath("//*[text()='Add Element']")).isDisplayed();
        if (addElement) {
            Assert.assertTrue(addElement, "Add Element button is displayed");

        }
        else{
            Assert.assertTrue(addElement,"Add Element button is not displayed");

        }

         //Verify whether Add Element button is clickable
           addElement = driver.findElement(By.xpath("//*[text()='Add Element']")).isEnabled();
           Assert.assertTrue(addElement, "Add Element is enabled to click");

        WebElement addElementButton= driver.findElement(By.xpath("//*[text()='Add Element']"));
        int n= ThreadLocalRandom.current().nextInt(10, 100);
        for (int i=0;i<n;i++){
            addElementButton.click();
        }

        //Verify  that n number of elements exist on the page
        List<WebElement> elements=driver.findElements(By.xpath("//*[@class='added-manually']"));
        int elementsCount = elements.size();
        Assert.assertNotNull(elementsCount, "Number of elements exist on the page is "+elementsCount);
        Assert.assertEquals(n,elementsCount);
    }

    @AfterMethod
    void teardown() {
        driver.quit();
    }
}

