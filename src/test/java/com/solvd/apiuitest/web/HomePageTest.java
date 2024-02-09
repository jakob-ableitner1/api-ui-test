package com.solvd.apiuitest.web;

import com.solvd.apiuitest.web.component.MainNavigation;
import com.solvd.apiuitest.web.component.ProductCard;
import com.solvd.apiuitest.web.component.ProductPreview;
import com.solvd.apiuitest.web.component.SearchLine;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends AbstractTest {

    @Test
    public void verifySearchLineTest(){
        String clothingType = "socks";

        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLine searchLine = homePage.getHeader().getSearchLine();
        Assert.assertTrue(searchLine.getSearchInput().isElementPresent(1), "Search input is not present");
        sa.assertEquals(searchLine.getSearchInputPlaceholder(), "Search", "Search input has an incorrect placeholder");

        searchLine.typeSearchInputValue(clothingType);

        SearchPage searchPage = searchLine.clickEnterButton();

        sa.assertTrue(driver.getCurrentUrl().contains(clothingType.toLowerCase()), "Url doesn't contain the clothing type");

        List<ProductCard> cards = searchPage.getCards();
        for (ProductCard card : cards){
            sa.assertTrue(card.getTitleText().toLowerCase().contains(clothingType.toLowerCase()), String.format("Product with name '%s' doesn't contain the clothing type in its name", card.getTitleText()));
        }

        sa.assertAll();
    }

    @Test
    public void logoClickTest(){
        String clothingType = "socks";

        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLine searchLine = homePage.getHeader().getSearchLine();
        searchLine.typeSearchInputValue(clothingType);

        SearchPage searchPage = searchLine.clickEnterButton();

        Assert.assertEquals("https://www.adidas.com/us/socks", driver.getCurrentUrl(), "Incorrect url");

        homePage = searchPage.getHeader().clickLogo();

        Assert.assertEquals("https://www.adidas.com/us", driver.getCurrentUrl(), "Incorrect url");
    }

    @Test
    public void saleButtonTest(){
        WebDriver driver = getDriver();

        SoftAssert sa = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.open();

        MainNavigation mainNavigation = homePage.getHeader().getMainNavigation();
        SearchPage searchPage = mainNavigation.clickSaleButton();

        Assert.assertEquals("https://www.adidas.com/us/sale", driver.getCurrentUrl().toLowerCase(), "Incorrect url");

        List<ProductCard> cards = searchPage.getCards();
        for (ProductCard card : cards) {
            sa.assertTrue(card.getDiscountPercentage().isPresent(), String.format("Product with name '%s' doesn't contain the discount display", card.getTitleText()));
            System.out.println("d");
        }
        sa.assertAll();
    }

//    @Test
//    public void clickWhatsTrendingTabTest(){
//        WebDriver driver = getDriver();
//
//        HomePage homePage = new HomePage(driver);
//        homePage.open();
//
//        ProductPreview productPreview = homePage.getProductPreview();
//
//        Assert.assertEquals(productPreview.getActiveTab(), productPreview.getNewArrivalsTab(), "Active tab wasn't on new arrivals at page start up");
//
//        productPreview.clickWhatsTrendingTab();
//
//        Assert.assertEquals(productPreview.getActiveTab(), productPreview.getWhatsTrendingTab(), "Active tab wasn't changed on click");
//    }
}
