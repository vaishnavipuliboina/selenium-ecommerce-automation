package tests;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.ContactPage;

public class UITest extends BaseTest {

    @Test
    public void testContactForm() throws Exception {
        ContactPage cp = new ContactPage(driver);
        cp.submitContactForm();
        System.out.println("PASS: Contact form filled and file upload executed");
    }

 // ✅ Test Case 10: Subscription (Home)
    @Test
    public void testSubscriptionHome() {
        HomePage hp = new HomePage(driver);

        ((JavascriptExecutor) driver)
            .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        hp.subscribe("test@gmail.com");
    }

    // ✅ Test Case 11: Subscription (Cart)
    @Test
    public void testSubscriptionCart() {
        driver.get("https://automationexercise.com/view_cart");

        HomePage hp = new HomePage(driver);

        ((JavascriptExecutor) driver)
            .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        hp.subscribe("test@gmail.com");
    }



    // ✅ Test Case 25: Scroll Up using button
    @Test
    public void testScrollUpArrow() {
        HomePage hp = new HomePage(driver);

        ((JavascriptExecutor) driver)
            .executeScript("window.scrollTo(0, document.body.scrollHeight)");

        hp.clickScrollUp();
    }

    // ✅ Test Case 26: Scroll Up without button
    @Test
    public void testScrollUpManual() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        js.executeScript("window.scrollTo(0, 0)");
    }
}