package com.solvd.qa.carina.demo.zebrunner.web.components.searchComponent;

import com.solvd.qa.carina.demo.zebrunner.web.components.headerMenu.CarinaHeaderComponent;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaSearchComponentPage extends AbstractUIObject {

    private static final Logger logger = LogManager.getLogger(CarinaHeaderComponent.class);

    @FindBy(css = ".md-search__inner")
    private ExtendedWebElement searchComponent;

    @FindBy(xpath = ".//label[@class='md-search__icon md-icon']")
    private ExtendedWebElement searchIcon;

    @FindBy(xpath = ".//input[@placeholder='Search']")
    private ExtendedWebElement searchInput;

    public CarinaSearchComponentPage(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isSearchComponentDisplayed() {
        logger.info("Checking if search component is displayed...");
        return searchComponent.isElementPresent();
    }

    public boolean isSearchIconPresent() {
        logger.info("Validating search icon...");
        return searchIcon.isElementPresent();
    }

    public boolean isSearchTextPresent() {
        logger.info("Validating search text...");
        return searchInput.isElementPresent();
    }
}
