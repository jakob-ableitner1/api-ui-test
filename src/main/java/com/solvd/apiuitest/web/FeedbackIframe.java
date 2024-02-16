package com.solvd.apiuitest.web;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FeedbackIframe extends AbstractPage {

    @FindBy(xpath = ".//a[contains(@href, 'adidas.com/help')]")
    private ExtendedWebElement getHelp;

    @FindBy(xpath = ".//*[@class='q-radio']")
    private List<ExtendedWebElement> feedbackOptions;

    public FeedbackIframe(WebDriver driver) {
        super(driver);
    }

    public int numberOfFeedbackOptions(){

        return feedbackOptions.size();
    }

    public HelpPage clickGetHelp(){
        getHelp.click();
        return new HelpPage(getDriver());
    }
}
