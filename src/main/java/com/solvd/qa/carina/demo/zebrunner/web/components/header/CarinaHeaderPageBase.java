package com.solvd.qa.carina.demo.zebrunner.web.components.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class CarinaHeaderPageBase extends AbstractUIObject {
    public CarinaHeaderPageBase(WebDriver driver, SearchContext sc ) {
        super(driver, sc);
    }

    public abstract boolean isZebRunnerLogoDisplayed();

    public abstract String isCarinaBrandDisplayed();

    public abstract void clickZebRunnerLogo();

    public abstract boolean isSearchComponentDisplayed();

    public abstract String getSearchText();

    public abstract ExtendedWebElement getGitHubLink();

    public abstract ExtendedWebElement getHeaderPanel();
}
