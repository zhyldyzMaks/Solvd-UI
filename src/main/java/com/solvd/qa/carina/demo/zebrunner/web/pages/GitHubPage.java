package com.solvd.qa.carina.demo.zebrunner.web.pages;

import com.solvd.qa.carina.demo.zebrunner.web.pages.common.GitHubPageBase;
import org.openqa.selenium.WebDriver;

public class GitHubPage extends GitHubPageBase {

    public GitHubPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
