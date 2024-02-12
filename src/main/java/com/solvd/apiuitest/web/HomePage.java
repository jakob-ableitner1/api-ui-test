package com.solvd.apiuitest.web;

import com.solvd.apiuitest.web.component.AdiClubLogIn;
import com.solvd.apiuitest.web.component.MissionStatement;
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

    @FindBy(xpath = "//*[@data-auto-id='seo']")
    private MissionStatement missionStatement;

    @FindBy(xpath = "//*[@id='account-portal-modal']//*[@class='gl-modal__main']")
    private AdiClubLogIn adiClubLogIn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        openURL(Configuration.getRequired("home_url"));
    }

    public Header getHeader() {
        return header;
    }

    public ProductPreview getProductPreview() {
        return productPreview;
    }

    public MissionStatement getMissionStatement() {
        return missionStatement;
    }

    public boolean adiClubLoginExists(){
        return adiClubLogIn.isUIObjectPresent(1);
    }

    public AdiClubLogIn getAdiClubLogIn(){
        return adiClubLogIn;
    }
}
