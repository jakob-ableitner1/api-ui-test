package com.solvd.apiuitest.web.component;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPreview extends AbstractUIObject {

    @FindBy(xpath = ".//*[@data-testid='tab-button-1']")
    private ExtendedWebElement newArrivalsTab;

    @FindBy(xpath = ".//*[@data-testid='tab-button-2']")
    private ExtendedWebElement whatsTrendingTab;

    public ProductPreview(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getNewArrivalsTab() {
        return newArrivalsTab;
    }

    public ExtendedWebElement getWhatsTrendingTab() {
        return whatsTrendingTab;
    }

    public void clickNewArrivalsTab(){
        newArrivalsTab.click();
    }

    public void clickWhatsTrendingTab(){
        whatsTrendingTab.click();
    }

//    public ExtendedWebElement getActiveTab(){
//        System.out.println("CSS ELEMENT ------ " + newArrivalsTab.getElement().getCssValue("class"));
//        if (newArrivalsTab.getCssValue("class").contains("_tab-button--active")){
//            return newArrivalsTab;
//        } else {
//            return whatsTrendingTab;
//        }
//    }
}
