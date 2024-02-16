package com.solvd.apiuitest.web;


import com.solvd.apiuitest.web.component.PaypalCreditFrame;
import com.solvd.apiuitest.web.component.ProductCard;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import freemarker.core._ArrayEnumeration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage {

    @FindBy(xpath = "//iframe[contains(@name, '__zoid__paypal_message')]")
    private ExtendedWebElement paypalMessageFrame;

    @FindBy(xpath = "//iframe[contains(@name, '__zoid__paypal_credit')]")
    private PaypalCreditFrame paypalCreditFrame;

    @FindBy(xpath = "//body[contains(script, 'paypal')]//button")
    private ExtendedWebElement paypalButton;

    @FindBy(xpath = "//*[contains(@class, 'sidebar')]//*[contains(@class, 'product-description')]//h1[@data-auto-id='product-title']")
    private ExtendedWebElement productTitle;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public PaypalCreditFrame getPaypalCreditFrame(){
        return paypalCreditFrame;
    }

    public void switchToPaypalMessageFrame(){
        driver.switchTo().frame(paypalMessageFrame.getElement());
    }

    public boolean paypalMessageFrameExists(){
        return paypalMessageFrame.isElementPresent(1);
    }

    public boolean paypalCreditFrameExists(){
        return paypalCreditFrame.isUIObjectPresent();
    }

    public boolean paypalButtonExists(){
        return paypalButton.isElementPresent(1);
    }

    public void clickPaypalButton(){
        paypalButton.click(5);
    }

    public String getProductTitle(){
        return productTitle.getText();
    }

}
