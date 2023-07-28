package com.solvd.qa.carina.demo.zebrunner.web.components.header;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarinaHeaderPage extends CarinaHeaderPageBase {

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

    @Override
    public boolean isZebRunnerLogoDisplayed() {
        return zebRunnerLogo.isElementPresent();
    }

    @Override
    public String isCarinaBrandDisplayed() {
        return carinaBrandText.getText();
    }

    @Override
    public void clickZebRunnerLogo() {
        zebRunnerLogo.click();
    }

    @Override
    public boolean isSearchComponentDisplayed() {
        boolean isSearchBoxDisplayed = searchBox.isElementPresent();
        boolean isSearchIconDisplayed = searchIcon.isElementPresent();
        boolean isSearchTextDisplayed = searchText.isElementPresent();

        return isSearchBoxDisplayed && isSearchIconDisplayed && isSearchTextDisplayed;
    }

    @Override
    public String getSearchText() {
        return searchText.getAttribute("placeholder");
    }

    @Override
    public ExtendedWebElement getGitHubLink() {
        return gitHubLink;
    }

    public ExtendedWebElement getHeaderPanel() {
        return headerPanel;
    }
}
