package com.solvd.qa.carina.demo.zebrunner.web.components.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaHeaderPage extends AbstractUIObject {

    private static final Logger logger = LogManager.getLogger(CarinaHeaderPage.class);

    @FindBy(xpath = "//a[@aria-label='Carina']//img")
    private ExtendedWebElement zebRunnerLogo;

    @FindBy(xpath = "//span[contains(text(),'Carina')]")
    private ExtendedWebElement carinaBrandText;

    @FindBy(xpath = "//div[@class='md-header-nav__source']//a[@title='Go to repository']")
    private ExtendedWebElement gitHubLink;

    @FindBy(css = ".md-header-nav__ellipsis")
    private ExtendedWebElement headerPanel;

    public CarinaHeaderPage(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }

    public boolean isZebRunnerLogoOnTheLeft() {
        logger.info("Checking if ZebRunner logo is on the left of the header...");
        int logoX = zebRunnerLogo.getElement().getLocation().getX();
        int headerX = headerPanel.getElement().getLocation().getX();
        return logoX < headerX;
    }

    public boolean isCarinaBrandDisplayed() {
        logger.info("Checking if Carina brand is displayed...");
        String brandText = carinaBrandText.getText();
        return brandText.equals("Carina");
    }

    public void clickZebRunnerLogo() {
        zebRunnerLogo.click();
    }

    public ExtendedWebElement getGitHubLink() {
        return gitHubLink;
    }

    public ExtendedWebElement getHeaderPanel() {
        return headerPanel;
    }
}
