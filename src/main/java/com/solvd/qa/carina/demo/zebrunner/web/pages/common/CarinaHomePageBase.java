package com.solvd.qa.carina.demo.zebrunner.web.pages.common;

import com.solvd.qa.carina.demo.zebrunner.web.components.footer.CarinaFooterMenuBase;
import com.solvd.qa.carina.demo.zebrunner.web.components.header.CarinaHeaderPage;
import com.solvd.qa.carina.demo.zebrunner.web.components.navigation.CarinaNavigationPage;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class CarinaHomePageBase extends AbstractPage {

    public CarinaHomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract CarinaHeaderPage getHeader();

    public abstract CarinaFooterMenuBase getFooterMenu();

    public abstract CarinaNavigationPage getNavigation();

    public abstract boolean isOverviewHeaderVisible();

    public abstract boolean isHeaderSticky();
}
