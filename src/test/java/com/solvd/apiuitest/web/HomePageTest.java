package com.solvd.apiuitest.web;

import com.solvd.apiuitest.web.component.*;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePageTest extends AbstractTest {

    @Test
    public void verifySearchLineTest() {
        String clothingType = "socks";

        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLine searchLine = homePage.getHeader().getSearchLine();
        Assert.assertTrue(searchLine.searchInputExists(), "Search input is not present");
        sa.assertEquals(searchLine.getSearchInputPlaceholder(), "Search", "Search input has an incorrect placeholder");

        searchLine.typeSearchInputValue(clothingType);

        SearchPage searchPage = searchLine.clickEnterButton();

        sa.assertTrue(driver.getCurrentUrl().contains(clothingType.toLowerCase()), "Url doesn't contain the clothing type");

        List<ProductCard> cards = searchPage.getCards();
        cards.stream().forEach(card -> {
            sa.assertTrue(card.getTitleText().toLowerCase().contains(clothingType.toLowerCase()), String.format("Product with name '%s' doesn't contain the clothing type in its name", card.getTitleText()));
        });

        sa.assertAll();
    }

    @Test
    public void logoTest() {
        String clothingType = "socks";

        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        SearchLine searchLine = homePage.getHeader().getSearchLine();
        searchLine.typeSearchInputValue(clothingType);

        SearchPage searchPage = searchLine.clickEnterButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.adidas.com/us/socks", "Incorrect url");
        Assert.assertTrue(searchPage.getHeader().logoExists(), "Logo is not a search page");

        searchPage.getHeader().clickLogo();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.adidas.com/us", "Incorrect url");
        Assert.assertTrue(searchPage.getHeader().logoExists(), "Logo is not a search page");
    }

    @Test
    public void saleButtonTest() {
        WebDriver driver = getDriver();

        SoftAssert sa = new SoftAssert();
        HomePage homePage = new HomePage(driver);
        homePage.open();

        MainNavigation mainNavigation = homePage.getHeader().getMainNavigation();
        SearchPage searchPage = mainNavigation.clickSaleButton();

        Assert.assertEquals(driver.getCurrentUrl().toLowerCase(), "https://www.adidas.com/us/sale", "Incorrect url");

        List<ProductCard> cards = searchPage.getCards();
        cards.stream().forEach(card -> {
            sa.assertTrue(card.discountPercentageExists(), String.format("Product with name '%s' doesn't contain the discount display", card.getTitleText()));
        });
        sa.assertAll();
    }

    @Test
    public void productPreviewTest(){
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        ProductPreview productPreview = homePage.getProductPreview();

        Assert.assertTrue(productPreview.newArrivalsTabExists(), "New Arrivals tab does not exist");
        Assert.assertTrue(productPreview.whatsTrendingTabExists(), "Whats trending tab does not exist");

        productPreview.clickWhatsTrendingTab();

        Assert.assertTrue(productPreview.hasCorrectActiveTab(), "Active tab wasn't changed on click");
        Assert.assertTrue(productPreview.carouselItemsExists(), "No carousel items present in whats trending tab");

        productPreview.clickNewArrivalsTab();

        Assert.assertTrue(productPreview.hasCorrectActiveTab(), "Active tab wasn't changed on click");
        Assert.assertTrue(productPreview.carouselItemsExists(), "No carousel items present in new arrivals tab");
    }

    @Test
    public void carouselItemTest(){
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        List<CarouselItem> carouselItems = homePage.getProductPreview().getCarouselItems();

        SoftAssert sa = new SoftAssert();

        carouselItems.stream().forEach(carouselItem -> {
            sa.assertTrue(carouselItem.imageExists());
            sa.assertTrue(carouselItem.priceItemExists());
        });
    }

    @Test
    public void missionStatementTest(){
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        MissionStatement missionStatement = homePage.getMissionStatement();

        Assert.assertTrue(missionStatement.firstHeaderExists(), "First header does not exist");
        Assert.assertTrue(missionStatement.secondHeaderExists(), "Second header does not exist");
        Assert.assertTrue(missionStatement.paragraphBodysExist(), "No body paragraphs");
    }

    @Test
    public void signInWithInvalidEmail(){
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Header header = homePage.getHeader();

        Assert.assertTrue(header.signInIconExists(), "Sign in icon does not exist");
        Assert.assertFalse(homePage.adiClubLoginExists(), "adiClub login page exists before icon is clicked");
        header.clickSignInIcon();

        Assert.assertTrue(homePage.adiClubLoginExists(), "adiClub login page did not appear after icon is clicked");

        AdiClubLogIn adiClubLogIn = homePage.getAdiClubLogIn();
        Assert.assertTrue(adiClubLogIn.inputBarExists(), "Input bar does not exist");
        Assert.assertTrue(adiClubLogIn.continueButtonExists(), "Continue button does not exist");
        Assert.assertFalse(adiClubLogIn.errorMessageExists(), "Error message occured before any operations were performed");

        adiClubLogIn.enterInput("23");
        adiClubLogIn.clickContinueButton();
        Assert.assertTrue(adiClubLogIn.errorMessageExists(), "Error message did not appear when using invalid email");
    }
}
