package com.solvd.qa.carina.demo.zebrunner.web.components.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaHeaderPage extends CarinaHeaderPageBase {

    private static final Logger logger = LogManager.getLogger(CarinaHeaderPage.class);

    @FindBy(xpath = "//a[@aria-label='Carina']//img")
    private ExtendedWebElement zebRunnerLogo;

    @FindBy(xpath = "//span[contains(text(),'Carina')]")
    private ExtendedWebElement carinaBrandText;

    @FindBy(css = "div.md-search")
    private ExtendedWebElement searchBox;

    @FindBy(xpath = "//label[@class='md-search__icon md-icon']")
    private ExtendedWebElement searchIcon;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private ExtendedWebElement searchText;

    @FindBy(xpath = "//div[@class='md-header-nav__source']//a[@title='Go to repository']")
    private ExtendedWebElement gitHubLink;

    @FindBy(css = "div.md-header-nav__ellipsis")
    private ExtendedWebElement headerPanel;

    public CarinaHeaderPage(WebDriver driver, SearchContext sc) {
        super(driver, sc);
    }

    public boolean isZebRunnerLogoDisplayed() {
        logger.info("Checking if ZebRunner logo is displayed...");
        boolean isLogoPresent = zebRunnerLogo.isElementPresent();

        if (isLogoPresent) {
            int logoX = zebRunnerLogo.getElement().getLocation().getX();
            int headerX = headerPanel.getElement().getLocation().getX();
            isLogoPresent = logoX < headerX;
        }
        return isLogoPresent;
    }

    @Override
    public String isCarinaBrandDisplayed() {
        logger.info("Checking if Carina brand is displayed...");
        String brandText = carinaBrandText.getText();
        logger.info("Carina brand displayed: {}", brandText);
        return brandText;
    }

    @Override
    public void clickZebRunnerLogo() {
        zebRunnerLogo.click();
    }

    @Override
    public boolean isSearchComponentDisplayed() {
        logger.info("Checking if search component is displayed...");
        boolean isSearchBoxDisplayed = searchBox.isElementPresent();
        boolean isSearchIconDisplayed = searchIcon.isElementPresent();
        boolean isSearchTextDisplayed = searchText.isElementPresent();
        boolean isDisplayed = isSearchBoxDisplayed && isSearchIconDisplayed && isSearchTextDisplayed;
        logger.info("Search component displayed: {}", isDisplayed);
        return isDisplayed;
    }

    @Override
    public String getSearchText() {
        logger.info("Getting search text...");
        String searchPlaceholder = searchText.getAttribute("placeholder");
        logger.info("Search text: {}", searchPlaceholder);
        return searchPlaceholder;
    }

    @Override
    public ExtendedWebElement getGitHubLink() {
        return gitHubLink;
    }

    public ExtendedWebElement getHeaderPanel() {
        return headerPanel;
    }
}
