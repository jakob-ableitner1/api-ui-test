package com.solvd.apiuitest.web.component;

import com.solvd.apiuitest.enums.ProductPreviewTab;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPreview extends AbstractUIObject {

    @FindBy(xpath = ".//*[@data-testid='tab-button-1']")
    private ExtendedWebElement newArrivalsTab;

    @FindBy(xpath = ".//*[@data-testid='tab-button-2']")
    private ExtendedWebElement whatsTrendingTab;

    @FindBy(xpath = ".//*[contains(@class, 'gl-carousel-system__item')]")
    private List<CarouselItem> carouselItems;

    private ExtendedWebElement currentTab = newArrivalsTab;

    public ProductPreview(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean newArrivalsTabExists() {
        return newArrivalsTab.isElementPresent(1);
    }

    public boolean whatsTrendingTabExists() {
        return whatsTrendingTab.isElementPresent(1);
    }

    public boolean carouselItemsExists(){
        return carouselItems.stream().findFirst().isPresent();
    }

    public void clickTab(ProductPreviewTab tab) {

        if (tab.equals(ProductPreviewTab.NEWARRIVALS)){
            newArrivalsTab.click();
            currentTab = newArrivalsTab;
        }

        if (tab.equals(ProductPreviewTab.WHATSTRENDING)){
            whatsTrendingTab.click();
            currentTab = whatsTrendingTab;
        }
    }

    public boolean hasCorrectActiveTab(){
        if (newArrivalsTab.getAttribute("class").contains("_tab-button--active")){
            return newArrivalsTab.equals(currentTab);
        } else {
            return whatsTrendingTab.equals(currentTab);
        }
    }

    public List<CarouselItem> getCarouselItems() {
        return carouselItems;
    }

    public String getTrendingTabTextColor(){
        return whatsTrendingTab.getElement().getCssValue("color");
    }

    public String getTrendingTabBackgroundColor(){
        return whatsTrendingTab.getElement().getCssValue("background-color");
    }

    public void hoverTrendingTab(){
        Actions action = new Actions(driver);
        action.moveToElement(whatsTrendingTab.getElement()).build().perform();
    }
}
