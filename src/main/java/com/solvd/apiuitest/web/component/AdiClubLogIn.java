package com.solvd.apiuitest.web.component;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AdiClubLogIn extends AbstractUIObject {

    @FindBy(xpath = ".//input[not(@type='checkbox')]")
    private ExtendedWebElement inputBar;

    @FindBy(xpath = ".//button[@type='submit']")
    private ExtendedWebElement continueButton;

    @FindBy(xpath = ".//*[@class='gl-form-notice__error']")
    private ExtendedWebElement errorMessage;

    public AdiClubLogIn(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean inputBarExists(){
        return inputBar.isElementPresent(1);
    }

    public boolean continueButtonExists(){
        return continueButton.isElementPresent(1);
    }

    public boolean errorMessageExists(){
        return errorMessage.isElementPresent(1);
    }

    public void enterInput(String input){
        inputBar.type(input);
    }

    public void clickContinueButton(){
        continueButton.click();
    }
}
