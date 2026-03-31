package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.ProductPage;

public class ProductTest extends BaseTest {

  

    @Test
    public void viewProductDetailsTest() {

        setup();

        ProductPage pp = new ProductPage(driver);

        pp.clickProducts();
        pp.handleAds();
        pp.scrollDown();

        pp.clickFirstProduct();

        tearDown();
    }

    @Test
    public void categoryTest() {

        setup();

        ProductPage pp = new ProductPage(driver);

        pp.clickProducts();
        pp.handleAds();
        pp.scrollDown();
        pp.scrollDown();

        pp.selectCategory();

        tearDown();
    }

    @Test
    public void brandTest() {

        setup();

        ProductPage pp = new ProductPage(driver);

        pp.clickProducts();
        pp.handleAds();
        pp.scrollDown();

        pp.selectBrand();

        tearDown();
    }

    @Test
    public void addReviewTest() {

        setup();

        ProductPage pp = new ProductPage(driver);

        pp.clickProducts();
        pp.handleAds();
        pp.scrollDown();

        pp.clickFirstProduct();

        pp.scrollDown();

        pp.addReview("Vaishnavi", "test@gmail.com", "Nice product");

        tearDown();
    }

    @Test
    public void verifyProductsPageTitleTest() {

        setup();

        ProductPage pp = new ProductPage(driver);

        pp.clickProducts();
        pp.handleAds();

        String title = driver.getTitle();

        System.out.println("Page Title: " + title);

        Assert.assertTrue(title.contains("Automation Exercise"));

        tearDown();
    }
    
    @Test
    public void forceFailTest() {

        setup();

        ProductPage pp = new ProductPage(driver);

        pp.clickProducts();

        // ❌ Force failure
        Assert.assertTrue(false);

        tearDown();
    } 
}