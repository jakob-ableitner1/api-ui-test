package com.solvd.apiuitest.web;

import com.solvd.apiuitest.web.component.ProductPreview;
import com.solvd.apiuitest.web.component.Header;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "(//*[@class='grid-row'])[1]")
    private ProductPreview productPreview;

    @FindBy(xpath = "//header")
    private Header header;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open(){
        openURL(Configuration.getRequired("home_url"));
    }

    public Header getHeader() {
        return header;
    }

    public ProductPreview getProductPreview() {
        return productPreview;
    }
}
