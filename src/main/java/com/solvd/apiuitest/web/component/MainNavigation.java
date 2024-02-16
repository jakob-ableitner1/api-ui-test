package com.solvd.apiuitest.web.component;

import com.solvd.apiuitest.web.HomePage;
import com.solvd.apiuitest.web.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MainNavigation extends AbstractUIObject {

    @FindBy(xpath = ".//a[text()='SALE']")
    private ExtendedWebElement saleButton;

    public MainNavigation(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean saleButtonExists() {
        return saleButton.isElementPresent(1);
    }

    public SearchPage clickSaleButton(){
        saleButton.click(1);
        return new SearchPage(getDriver());
    }
}
