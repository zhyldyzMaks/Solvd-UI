package com.solvd.qa.carina.demo.zebrunner.web.components.footer;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaFooterMenu extends CarinaFooterMenuBase {

    @FindBy(xpath = "//div[contains(text(),'2022 Zebrunner')]")
    private ExtendedWebElement footerCopyRightElement;

    public CarinaFooterMenu(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }

    public void scrollIntoFooterMenu() {
        footerCopyRightElement.scrollTo();
    }
}
