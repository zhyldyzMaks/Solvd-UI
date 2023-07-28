package com.solvd.qa.carina.demo.zebrunner.web.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class GitHubPageBase extends AbstractPage {

    protected GitHubPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getCurrentUrl();
}
