package Aplitools.Tutorials.AppiumNativeJavaTutorial;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.appium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/*
 * 		RUN ANDROID WITH SAUCE LABS
 */

public class TestApplitools {
	public static void main(String[] args) throws MalformedURLException {
        Eyes eyes = new Eyes();
        eyes.setLogHandler(new StdoutLogHandler(true));
        // This is your api key, make sure you use it in all your tests.
        eyes.setApiKey("APIKEY");

        // Setup appium - Make sure the capabilities meets your environment.
        // Refer to http://appium.io documentation if required.
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.APPIUM_VERSION, "1.3.4");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
        dc.setCapability(MobileCapabilityType.APP, "/Users/mattjasaitis/Downloads/test-apps/Applitools XCUI Demo.zip");

        //WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        IOSDriver<WebElement> driver = new IOSDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), dc); 
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            // Start visual UI testing
            eyes.open(driver, "iOS test application", "test");

            eyes.check("tag", Target.window()); 

            // End visual UI testing. Validate visual correctness.
            eyes.close();
        } finally {
            eyes.abortIfNotClosed();
            driver.quit();
        }
    }
}
