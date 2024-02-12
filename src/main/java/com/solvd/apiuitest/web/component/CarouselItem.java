package com.solvd.apiuitest.web.component;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarouselItem extends AbstractUIObject {

    @FindBy (xpath = ".//img")
    private ExtendedWebElement image;

    @FindBy (xpath = ".//*[contains(@class, 'gl-price-item')]")
    private ExtendedWebElement priceItem;

    public CarouselItem(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean imageExists(){
        return image.isElementPresent(1);
    }

    public boolean priceItemExists(){
        return priceItem.isElementPresent(1);
    }
}
