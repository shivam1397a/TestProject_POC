package Steps;

import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

class TestClass1 implements Runnable {
    public void run() {
        Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
        capsHashtable.put("device", "iPhone 12 Pro");
        capsHashtable.put("real_mobile", "true");
        capsHashtable.put("os_version", "14");
        capsHashtable.put("build", "BStack-[Java] Sample Build");
        capsHashtable.put("name", "Thread 1");
        WebConnector r1 = new WebConnector();
        r1.executeTest(capsHashtable);
    }
}
class TestClass2 implements Runnable {
    public void run() {
        Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
        capsHashtable.put("os_version", "10");
        capsHashtable.put("resolution", "1920x1080");
        capsHashtable.put("browser", "Chrome");
        capsHashtable.put("browser_version", "latest");
        capsHashtable.put("os", "Windows");
        capsHashtable.put("name", "Thread1"); // test name
        capsHashtable.put("build", "BStack Build Number 1");
        WebConnector r1 = new WebConnector();
        r1.executeTest(capsHashtable);
    }
}
class TestClass3 implements Runnable {
    public void run() {
        Hashtable<String, String> capsHashtable = new Hashtable<String, String>();
        capsHashtable.put("browser", "safari");
        capsHashtable.put("browser_version", "14");
        capsHashtable.put("os", "OS X");
        capsHashtable.put("os_version", "Big Sur");
        capsHashtable.put("build", "BStack-[Java] Sample Build");
        capsHashtable.put("name", "Thread 2");
        WebConnector r1 = new WebConnector();
        r1.executeTest(capsHashtable);
    }
}

public class WebConnector<V> {

    public static WebDriver driver = null;
    public SessionId session = null;
    public static Properties prop = new Properties();

//    public WebConnector() {
//        try {
//            prop.load(new FileInputStream("./configs/application.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static final String USERNAME = "bendavis3";
    public static final String AUTOMATE_KEY = "tqLxHQPqCMpifFwtYHDM";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static void main(String[] args) throws Exception {
        Thread object1 = new Thread(new TestClass1());
        object1.start();
        Thread object2 = new Thread(new TestClass2());
        object2.start();
        Thread object3 = new Thread(new TestClass3());
        object3.start();
    }

    public void executeTest(Hashtable<String, String> capsHashtable) {
        String key;
        DesiredCapabilities caps = new DesiredCapabilities();
        // Iterate over the hashtable and set the capabilities
        Set<String> keys = capsHashtable.keySet();
        Iterator<String> itr = keys.iterator();
        while (itr.hasNext()) {
            key = itr.next();
            caps.setCapability(key, capsHashtable.get(key));
        }
    }

    public WebDriver getDriver() {
        return this.getDriver();
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setUpDriver(String browser, String reportName) throws Throwable {
        if (browser == null) {
            browser = "chrome";
        }
        switch (browser) {
            case "chrome":
                driver = new ChromeDriver("r73l73D4hqOuTwJGP77dkV8hkq7y6WT6450Xuj_84Nc1", new ChromeOptions(), "Cucumber", reportName);
                driver.manage().window().maximize();
                break;
            case "firefox":
                driver = new FirefoxDriver("r73l73D4hqOuTwJGP77dkV8hkq7y6WT6450Xuj_84Nc1", new FirefoxOptions(), "Cucumber", reportName);
                driver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");
        }
    }

    public void waitForPageLoad(int timeout) {
        ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
    }

    public By getElementWithLocator(String WebElement) throws Exception {
        String locatorTypeAndValue = WebElement;
        String[] locatorTypeAndValueArray = locatorTypeAndValue.split(",");
        String locatorType = locatorTypeAndValueArray[0].trim();
        String locatorValue = locatorTypeAndValueArray[1].trim();
        switch (locatorType.toUpperCase()) {
            case "ID":
                return By.id(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "TAGNAME":
                return By.tagName(locatorValue);
            case "LINKTEXT":
                return By.linkText(locatorValue);
            case "PARTIALLINKTEXT":
                return By.partialLinkText(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "CLASSNAME":
                return By.className(locatorValue);
            default:
                return null;
        }
    }

    public WebElement FindAnElement(String WebElement) throws Exception {
        return driver.findElement(getElementWithLocator(WebElement));
    }

    public void PerformActionOnElement(String WebElement, String Action, String Text) throws Exception {
        switch (Action) {
            case "Click":
                FindAnElement(WebElement).click();
                break;
            case "JSClick":
                JavascriptExecutor js1 = (JavascriptExecutor) driver;
                js1.executeScript("arguments[0].click()", WebElement);
                break;
            case "Type":
                FindAnElement(WebElement).sendKeys(Text);
                break;
            case "Clear":
                FindAnElement(WebElement).clear();
                break;
            case "WaitForElementDisplay":
                waitForCondition("Presence", WebElement, 60);
                break;
            case "WaitForElementClickable":
                waitForCondition("Clickable", WebElement, 60);
                break;
            case "ElementNotDisplayed":
                waitForCondition("NotPresent", WebElement, 60);
                break;
            default:
                throw new IllegalArgumentException("Action \"" + Action + "\" isn't supported.");
        }
    }

    public void waitForCondition(String TypeOfWait, String WebElement, int Time) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(Time)).pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);
            switch (TypeOfWait) {
                case "PageLoad":
                    wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
                    break;
                case "Clickable":
                    wait.until(ExpectedConditions.elementToBeClickable(FindAnElement(WebElement)));
                    break;
                case "Presence":
                    wait.until(ExpectedConditions.presenceOfElementLocated(getElementWithLocator(WebElement)));
                    break;
                case "Visibility":
                    wait.until(ExpectedConditions.visibilityOfElementLocated(getElementWithLocator(WebElement)));
                    break;
                case "NotPresent":
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(getElementWithLocator(WebElement)));
                    break;
                default:
                    Thread.sleep(Time * 1000);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("wait For Condition \"" + TypeOfWait + "\" isn't supported.");
        }
    }
}