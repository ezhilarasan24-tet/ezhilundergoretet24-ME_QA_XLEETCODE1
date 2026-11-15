package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    
    ChromeDriver driver;
    WebDriverWait wait;
   
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        
        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    
    public void endTest()
    {
        System.out.println("End Test: TestCases");
        if(driver !=null){
            driver.quit();
        }

    }
    public void clickMethod(String xpathValue){
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathValue)));
        if (element.isDisplayed() && element.isEnabled()) {
            element.click();                       
        }
    }
   

  

    //TestCase01: Verify the Leetcode Homepage URL
    public void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/");
        String curUrl=driver.getCurrentUrl();
        if (curUrl.contains("leetcode")) {                        
            System.out.println("TestCase 01 PASS");
        }
        else{
            System.out.println("TestCase 01 Fail");
            
        }
        System.out.println("end Test case: testCase01");

    }
    //TestCase02: Verify Problem Set URL and Display First 5 Questions

    public void testCase02(){
        System.out.println("Start Test case02");
        String quexpath="//p[contains(text(),'View Questions ')]";

        //p[contains(text(),'View Questions ')]
       
        clickMethod(quexpath);

        //step 2
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Register")));
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("problemset")) {
            System.out.println("The currentURL is verified ");            
        }
        else{
            System.out.println("Verify the URl is faill");
        }

        // //div[contains(text(),'1. Two Sum')]
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("//a[@data-e2e-locator='question-list-row' and @id='1']")));

        // //a[contains(@href,'/problems/')]
        List<WebElement>allQuestions=driver.findElements(By.xpath("//a[@data-e2e-locator='question-list-row' and @id >= 1 and @id <= 5]"));
        for (WebElement question : allQuestions) {
            System.out.println(question.getText());          
        }
        if (!allQuestions.isEmpty()) {
        String firstQuestionText = allQuestions.get(0).getText().trim();
            if (firstQuestionText.contains("1. Two Sum")) {
                System.out.println("PASS: First question is verified as Two Sum.");
            }else{
            System.out.println("step fail First question is not as Two Sum");
            }
        }
        System.out.println("End Test case02 ");       
    }
    // TestCase03: Verify the Two Sum problem
    public void testCase03(){
        System.out.println("test case03 : Start Test Case!");  
        String twoSum="//*[text()='1. Two Sum']";
        clickMethod(twoSum);
        wait.until(ExpectedConditions.urlContains("two-sum"));
        if(driver.getCurrentUrl().contains("two-sum")){
            System.out.println("test case03 : Verify the Two Sum is Done.");
        }
        else{
            System.out.println("test case03 : Verify the Two Sum is Not Done.");            
        }
        
        System.out.println("test case03 : End Test Case.");           

    }
    public void testCase04(){
        String submissionXpath="//div[text()='Submissions' and contains(@class,'normal')]";
        clickMethod(submissionXpath);
        // WebElement msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Register or Log in")));
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
            "//a[@text='Register or Log in']")));
        if (msg.isDisplayed() && msg.getText().contains("Register or Log in")){
            System.out.println("TestCase 04 PASS: Register or Login is displayed");
        } else {
            System.out.println("TestCase 04 FAIL: Register or Login is not displayed");
        }
        
        System.out.println("End TestCase 04");
    }

}