package com.solvd.apiuitest.web.component;

import com.solvd.apiuitest.web.SearchPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SearchLine extends AbstractUIObject {

    @FindBy(xpath = ".//*[@data-auto-id= 'searchinput-desktop']")
    private ExtendedWebElement searchInput;

    @FindBy(xpath = ".//div[.//*[@class='gl-icon']]")
    private ExtendedWebElement deleteButton;

    public SearchLine(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean searchInputExists(){
        return searchInput.isElementPresent(1);
    }

    public String getSearchInputPlaceholder(){
        return searchInput.getText();
    }

    public void typeSearchInputValue(String value) {
        searchInput.type(value);
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public SearchPage clickEnterButton() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.RETURN).perform();
        return new SearchPage(getDriver());
    }
}
