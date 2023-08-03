package com.solvd.qa.carina.demo.zebrunner.web.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class GitHubPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(GitHubPage.class);

    public GitHubPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = getDriver().getCurrentUrl();
        logger.info("Current URL: {}", currentUrl);
        return currentUrl;
    }
}
