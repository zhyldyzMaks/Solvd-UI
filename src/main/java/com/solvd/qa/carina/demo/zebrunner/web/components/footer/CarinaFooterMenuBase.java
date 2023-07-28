package com.solvd.qa.carina.demo.zebrunner.web.components.footer;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class CarinaFooterMenuBase extends AbstractUIObject {
    public CarinaFooterMenuBase(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }
}
