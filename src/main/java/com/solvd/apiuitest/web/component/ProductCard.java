package com.solvd.apiuitest.web.component;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductCard extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class, 'glass-product-card__title')]")
    private ExtendedWebElement titleElement;

    @FindBy(xpath = ".//*[@data-auto-id= 'product-card-badge-container' and not(.//div)]")
    private ExtendedWebElement discountPercentage;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getTitleElement() {
        return titleElement;
    }

    public String getTitleText(){
        return titleElement.getText();
    }

    public ExtendedWebElement getDiscountPercentage() {
        return discountPercentage;
    }
}
