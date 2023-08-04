package com.solvd.qa.carina.demo.zebrunner.web.components.headerMenu;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaHeaderComponent extends AbstractUIObject {

    private static final Logger logger = LogManager.getLogger(CarinaHeaderComponent.class);

    @FindBy(xpath = ".//a[@class='md-header-nav__button md-logo']")
    private ExtendedWebElement zebRunnerLogo;

    @FindBy(xpath = ".//span[contains(text(),'Carina')]")
    private ExtendedWebElement carinaBrandText;

    @FindBy(xpath = ".//div[@class='md-header-nav__source']//a[@title='Go to repository']")
    private ExtendedWebElement gitHubLink;

    @FindBy(css = ".md-header-nav__ellipsis")
    private ExtendedWebElement headerPanel;

    public CarinaHeaderComponent(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }

    public boolean isZebRunnerLogoOnTheLeft() {
        logger.info("Checking if ZebRunner logo is on the left of the header...");
        int logoX = zebRunnerLogo.getElement().getLocation().getX();
        int headerX = headerPanel.getElement().getLocation().getX();
        return logoX < headerX;
    }

    public String getBrandText() {
        logger.info("Checking if Carina brand is displayed...");
        return carinaBrandText.getText();
    }

    public String validateGitHubPageUrl() {
        gitHubLink.click();
        return driver.getCurrentUrl().trim();
    }

    public void clickZebRunnerLogo() {
        zebRunnerLogo.click();
    }

    public ExtendedWebElement getHeaderPanel() {
        return headerPanel;
    }
}
