package com.solvd.apiuitest.web;

import com.solvd.apiuitest.web.component.Header;
import com.solvd.apiuitest.web.component.ProductCard;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends AbstractPage {

    @FindBy(xpath = "//header")
    private Header header;

    @FindBy(xpath = "//*[contains(@class, 'glass-product-card-container')]")
    private List<ProductCard> cards;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getCards() {
        return cards;
    }

    public Header getHeader() {
        return header;
    }
}
