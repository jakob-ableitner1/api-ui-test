package com.solvd.apiuitest.web.component;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class PaypalCreditFrame extends AbstractUIObject {
    public PaypalCreditFrame(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void switchToPaypalCreditFrame(){
        driver.switchTo().frame(getRootExtendedElement().getElement());
    }
}
