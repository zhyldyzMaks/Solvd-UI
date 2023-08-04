package com.solvd.qa.carina.demo.zebrunner.web.pages;

import com.solvd.qa.carina.demo.zebrunner.web.components.footerMenu.CarinaFooterMenu;
import com.solvd.qa.carina.demo.zebrunner.web.components.headerMenu.CarinaHeaderComponent;
import com.solvd.qa.carina.demo.zebrunner.web.components.navigationMenu.CarinaNavigationComponent;
import com.solvd.qa.carina.demo.zebrunner.web.components.searchComponent.CarinaSearchComponentPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaHomePage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(CarinaHomePage.class);

    @FindBy(css = ".md-header")
    private CarinaHeaderComponent header;

    @FindBy(xpath = "//div[@class='md-footer-meta md-typeset']")
    private CarinaFooterMenu footerMenu;

    @FindBy(xpath = "//div[@class='md-sidebar md-sidebar--primary']")
    private CarinaNavigationComponent navigation;

    @FindBy(css = ".md-search")
    private CarinaSearchComponentPage searchComponent;

    @FindBy(css = "h1#overview")
    private ExtendedWebElement overviewHeader;

    public CarinaHomePage(WebDriver driver) {
        super(driver);
    }

    public CarinaHeaderComponent getHeaderObject() {
        return header;
    }

    public CarinaFooterMenu getFooterMenuObject() {
        return footerMenu;
    }

    public CarinaNavigationComponent getNavigationObject() {
        return navigation;
    }

    public CarinaSearchComponentPage getSearchComponentObject() {
        return searchComponent;
    }

    public boolean isOverviewHeaderVisible() {
        logger.info("Checking if the overview header is visible...");
        header.clickZebRunnerLogo();
        return overviewHeader.isVisible();
    }

    public boolean isHeaderSticky() {
        logger.info("Checking if the header is sticky...");
        return header.getHeaderPanel().isElementPresent();
    }
}
