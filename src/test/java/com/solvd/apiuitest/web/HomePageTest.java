package com.solvd.apiuitest.web;

import com.solvd.apiuitest.enums.ProductPreviewTab;
import com.solvd.apiuitest.web.component.*;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.IntStream;

public class HomePageTest extends AbstractTest {

    @Test
    public void verifySearchLineTest() {
        String shoeLine = "gazelle";

        SoftAssert sa = new SoftAssert();
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(IndexOutOfBoundsException.class);

        SearchLine searchLine = homePage.getHeader().getSearchLine();
        Assert.assertTrue(searchLine.searchInputExists(), "Search input is not present");
        sa.assertEquals(searchLine.getSearchInputPlaceholder(), "Search", "Search input has an incorrect placeholder");

        searchLine.typeSearchInputValue(shoeLine);

        AtomicReference<SearchPage> searchPage = new AtomicReference<>(searchLine.clickEnterButton());
        sa.assertTrue(searchPage.get().getCurrentUrl().contains(shoeLine.toLowerCase()), "Url doesn't contain the shoe line");
        AtomicReference<List<ProductCard>> cards = new AtomicReference<>(searchPage.get().getCards());
        int size = cards.get().size();

        IntStream.range(0, size).forEach(i -> {
            searchPage.set(new SearchPage(driver));
            cards.set(searchPage.get().getCards());

            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    ProductCard card = cards.get().get(i);
                    String titleText = card.getTitleText().toLowerCase();
                    Assert.assertTrue(titleText.contains(shoeLine.toLowerCase()), String.format("Product with name '%s' doesn't contain the shoe line in its name", card.getTitleText()));
                    Assert.assertTrue(card.mainProductImageExists(), String.format("Product with name '%s' doesn't contain an image", card.getTitleText()));
                    if (card.priceItemExists()) {
                        Assert.assertTrue(card.getPriceItemText().contains("$"), String.format("Product with name '%s' doesn't contain a dollar sign in the price", card.getTitleText()));
                    } else {
                        Assert.fail("Price item not found");
                    }
                    ProductPage productPage = card.clickCard();
                    Assert.assertTrue(productPage.getProductTitle().toLowerCase().equals(titleText));
                    return true;
                }
            });
            driver.navigate().back();
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

        productPreview.clickTab(ProductPreviewTab.WHATSTRENDING);

        Assert.assertTrue(productPreview.hasCorrectActiveTab(), "Active tab wasn't changed on click");
        Assert.assertTrue(productPreview.carouselItemsExists(), "No carousel items present in whats trending tab");

        productPreview.clickTab(ProductPreviewTab.NEWARRIVALS);

        Assert.assertTrue(productPreview.hasCorrectActiveTab(), "Active tab wasn't changed on click");
        Assert.assertTrue(productPreview.carouselItemsExists(), "No carousel items present in new arrivals tab");

        //Assert that trending tab has black letter and transparent background without hover
        Assert.assertEquals(productPreview.getTrendingTabTextColor(), "rgba(0, 0, 0, 1)");
        Assert.assertEquals(productPreview.getTrendingTabBackgroundColor(), "rgba(0, 0, 0, 0)");

        productPreview.hoverTrendingTab();

        Assert.assertEquals(productPreview.getTrendingTabTextColor(), "rgba(255, 255, 255, 1)");
        Assert.assertEquals(productPreview.getTrendingTabBackgroundColor(), "rgba(0, 0, 0, 1)");
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

//    @Test
//    public void iframeTest(){
//        WebDriver driver = getDriver();
//
//        HomePage homePage = new HomePage(driver);
//        homePage.open();
//
//        List<Class<? extends Exception>> waitExceptions = Arrays.asList(ElementNotInteractableException.class, NoSuchElementException.class, IndexOutOfBoundsException.class);
//
//        Wait<WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(30))
//                .pollingEvery(Duration.ofSeconds(5))
//                .ignoreAll(waitExceptions);
//
//        ProductPage productPage = wait.until(new Function<WebDriver, ProductPage>() {
//            public ProductPage apply(WebDriver webDriver) {
//                return homePage.getProductPreview().getCarouselItems().get(1).clickCarouselItem();
//            }
//        });
//
//        Assert.assertTrue(productPage.paypalMessageFrameExists(), "Paypal message frame does not exist on product page");
//        productPage.switchToPaypalMessageFrame();
//        productPage.clickPaypalButton();
//
//        driver.switchTo().defaultContent();
//
//        PaypalCreditFrame paypalCreditFrame = productPage.getPaypalCreditFrame();
//        wait.until(new Function<WebDriver, Boolean>() {
//            public Boolean  apply(WebDriver webDriver) {
//                paypalCreditFrame.switchToPaypalCreditFrame();
//                return true;
//            }
//        });
//    }

    @Test
    public void iframeTest(){
        WebDriver driver = getDriver();

        HomePage homePage = new HomePage(driver);
        homePage.open();

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(ElementClickInterceptedException.class);

        Assert.assertFalse(homePage.feedbackIframeExists(), "Iframe exists before feedback button is pressed");

        FeedbackIframe feedbackIframe = homePage.clickFeedback();
        String homePageHandle = driver.getWindowHandle();

        HelpPage helpPage = wait.until(new Function<WebDriver, HelpPage>() {
            @Override
            public HelpPage apply(WebDriver webDriver) {
                return feedbackIframe.clickGetHelp();
            }
        });
        driver.switchTo().defaultContent();
        Assert.assertTrue(helpPage.headerExists(), "Header does not exist on help page");

        driver.getWindowHandles().stream().forEach(handle -> {
            if (!handle.equals(homePageHandle)){
                driver.switchTo().window(handle);
                driver.close();
            }
        });

        driver.switchTo().window(homePageHandle);
        homePage.switchToFeedbackIframe();

        Integer numberOfFeedbackOptions = wait.until(new Function<WebDriver, Integer>() {
            public Integer apply(WebDriver webDriver) {
                Integer number = 0;
                while(number < 11){
                    number = feedbackIframe.numberOfFeedbackOptions();
                }
                return number;
            }
        });

        Assert.assertEquals(numberOfFeedbackOptions, 11, "Number of options is not correct");
    }
}
