package com.solvd.apiuitest.web;

import com.solvd.apiuitest.web.component.Header;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HelpPage extends AbstractPage {

    @FindBy(xpath = "//header")
    private Header header;

    public HelpPage(WebDriver driver) {
        super(driver);
    }

    public boolean headerExists(){
        return header.isUIObjectPresent();
    }

}
