package com.solvd.qa.carina.demo.zebrunner.web.components.footerMenu;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaFooterMenu extends AbstractUIObject {

    @FindBy(xpath = ".//div[contains(text(),'2022 Zebrunner')]")
    private ExtendedWebElement footerCopyRightElement;

    public CarinaFooterMenu(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }

    public void scrollIntoFooterMenu() {
        footerCopyRightElement.scrollTo();
    }
}
