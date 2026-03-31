package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

	public static void takeScreenshot(WebDriver driver, String testName) {

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String path = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
	    File dest = new File(path);

	    try {
	        FileUtils.copyFile(src, dest);
	        System.out.println("Screenshot saved at: " + path);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}