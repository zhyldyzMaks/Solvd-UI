package com.solvd.qa.carina.demo.zebrunner.web.pages.desktop;

import com.solvd.qa.carina.demo.zebrunner.web.components.footer.CarinaFooterMenu;
import com.solvd.qa.carina.demo.zebrunner.web.components.header.CarinaHeaderPage;
import com.solvd.qa.carina.demo.zebrunner.web.components.navigation.CarinaNavigationPage;
import com.solvd.qa.carina.demo.zebrunner.web.pages.common.CarinaHomePageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = CarinaHomePageBase.class)
public class CarinaHomePage extends CarinaHomePageBase {

    private static final Logger logger = LogManager.getLogger(CarinaHomePage.class);

    @FindBy(css = "header.md-header")
    private CarinaHeaderPage header;

    @FindBy(xpath = "//div[@class='md-footer-meta md-typeset']")
    private CarinaFooterMenu footerMenu;

    @FindBy(xpath = "//div[@class='md-sidebar md-sidebar--primary']")
    private CarinaNavigationPage navigation;

    @FindBy(css = "h1#overview")
    private ExtendedWebElement overviewHeader;

    public CarinaHomePage(WebDriver driver) {
        super(driver);
    }

    public CarinaHeaderPage getHeader() {
        return header;
    }

    public CarinaFooterMenu getFooterMenu() {
        return footerMenu;
    }

    public CarinaNavigationPage getNavigation() {
        return navigation;
    }

    public boolean isOverviewHeaderVisible() {
        logger.info("Checking if the overview header is visible...");
        navigation.clickGettingStartedLink();
        header.clickZebRunnerLogo();
        return overviewHeader.isVisible();
    }

    public boolean isHeaderSticky() {
        logger.info("Checking if the header is sticky...");
        return header.getHeaderPanel().isElementPresent();
    }
}
