package com.solvd.apiuitest.web.component;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MissionStatement extends AbstractUIObject {

    @FindBy(xpath = ".//h1")
    private ExtendedWebElement firstHeader;

    @FindBy(xpath = ".//h2")
    private ExtendedWebElement secondHeader;

    @FindBy(xpath = ".//p")
    private List<ExtendedWebElement> paragraphBodys;

    public MissionStatement(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean firstHeaderExists(){
        return firstHeader.isElementPresent(1);
    }

    public boolean secondHeaderExists(){
        return secondHeader.isElementPresent(1);

    }

    public boolean paragraphBodysExist(){
        return paragraphBodys.stream().findFirst().isPresent();
    }
}
