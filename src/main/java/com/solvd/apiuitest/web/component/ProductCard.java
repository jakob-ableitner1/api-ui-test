package com.solvd.apiuitest.web.component;

import com.solvd.apiuitest.web.ProductPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ProductCard extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class, 'glass-product-card__title')]")
    private ExtendedWebElement titleElement;

    @FindBy(xpath = ".//*[@data-auto-id= 'product-card-badge-container' and not(.//div)]")
    private ExtendedWebElement discountPercentage;

    @FindBy(xpath = ".//*[@class = 'glass-product-card__assets']//img[1]")
    private ExtendedWebElement mainProductImage;

    @FindBy(xpath = ".//*[contains(@class, 'gl-price-item')]")
    private ExtendedWebElement priceItem;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean titleElementExists() {
        return titleElement.isElementPresent(1);
    }

    public String getTitleText(){
        return titleElement.getText();
    }

    public boolean discountPercentageExists() {
        return discountPercentage.isElementPresent(Duration.ofSeconds(1));
    }

    public boolean mainProductImageExists(){
        return mainProductImage.isElementPresent(Duration.ofSeconds(1));
    }

    public boolean priceItemExists(){
        return priceItem.isElementPresent(Duration.ofSeconds(1));
    }

    public String getPriceItemText(){
        return priceItem.getText();
    }

    public ProductPage clickCard(){
        getRootExtendedElement().click(1);
        return new ProductPage(getDriver());
    }
}
