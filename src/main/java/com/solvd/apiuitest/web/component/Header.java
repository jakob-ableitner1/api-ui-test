package com.solvd.apiuitest.web.component;

import com.solvd.apiuitest.web.HomePage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractUIObject {

    @FindBy(xpath = ".//*[@aria-label='Main Navigation']")
    private MainNavigation mainNavigation;

    @FindBy(xpath = ".//*[@data-auto-id='logo']")
    private ExtendedWebElement logo;

    @FindBy(xpath = ".//*[@data-auto-id= 'auxiliary-menu']//div")
    private SearchLine searchLine;

    @FindBy(xpath = ".//*[@data-auto-id='customer-info-button']")
    private ExtendedWebElement signInIcon;

    public Header(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public MainNavigation getMainNavigation() {
        return mainNavigation;
    }

    public SearchLine getSearchLine() {
        return searchLine;
    }

    public boolean logoExists() {
        return logo.isElementPresent(1);
    }

    public boolean signInIconExists(){
        return signInIcon.isElementPresent(1);
    }

    public HomePage clickLogo(){
        logo.click();
        return new HomePage(getDriver());
    }

    public void clickSignInIcon(){
        signInIcon.click();
    }
}
