package com.solvd.qa.carina.demo.zebrunner.web.components.navigation;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class CarinaNavigationPage extends AbstractUIObject {

    private static final Logger logger = LogManager.getLogger(CarinaNavigationPage.class);

    @FindBy(xpath = "//li[contains(@class, 'md-nav__item')]//a")
    private List<ExtendedWebElement> navigationElements;

    @FindBy(xpath = "//nav[@aria-label='Navigation']/*")
    private List<ExtendedWebElement> navigationBarNestedTags;

    @FindBy(xpath = "//nav[@aria-label='Navigation']//label[@for='__drawer']")
    private ExtendedWebElement carinaHeading;

    @FindBy(xpath = "//li[contains(@class, 'md-nav__item')]")
    private List<ExtendedWebElement> allNavLinks;

    @FindBy(xpath = "//li[contains(@class, '%s')]")
    private ExtendedWebElement dynamicXpathElement;

    @FindBy(xpath = "//li[contains(@class,'md-nav__item md-nav__item--nested')]")
    private List<ExtendedWebElement> parentLinksWithNested;

    @FindBy(xpath = "//li[@class='md-nav__item md-nav__item--nested']//li[@class='md-nav__item']/a")
    private List<ExtendedWebElement> nestedLinks;

    public CarinaNavigationPage(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickGettingStartedLink() {
        logger.info("Clicking on 'Getting started' link...");
        for (ExtendedWebElement element : navigationElements) {
            if (element.getText().equalsIgnoreCase("Getting started")) {
                element.click();
                break;
            }
        }
    }

    public boolean isCarinaHeadingFirstElement() {
        logger.info("Checking if Carina heading is the first element...");
        if (navigationBarNestedTags.size() > 0) {
            for (ExtendedWebElement element : navigationBarNestedTags) {
                if (element.getText().equals(carinaHeading.getText())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public boolean isNavigationLinkListPresent() {
        logger.info("Checking if navigation link list is present...");
        for (ExtendedWebElement element : navigationElements) {
            if (element.isElementPresent()) {
                return true;
            }
        }
        return false;
    }

    public boolean isHighlighted() {
        logger.info("Checking if current page is highlighted...");
        String expectedColor = "rgba(6, 194, 126, 1)";
        for (int i = 0; i < allNavLinks.size(); i++) {
            if (allNavLinks.get(i).equals(dynamicXpathElement.format("md-nav__item md-nav__item--active")) || allNavLinks.get(i).equals(dynamicXpathElement.format("md-nav__item md-nav__item--active md-nav__item--nested"))) {
                String actualColor = allNavLinks.get(i).getElement().getCssValue("color");
                if (!actualColor.equals(expectedColor)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areElementsHidden() {
        logger.info("Checking if elements are hidden...");
        boolean areHidden = false;
        for (ExtendedWebElement element : allNavLinks) {
            if (!element.isElementPresent()) {
                areHidden = true;
                break;
            }
        }
        return areHidden;
    }

    public boolean areNestedElementsRevealed() {
        logger.info("Checking if nested elements are revealed...");
        for (ExtendedWebElement parentLink : parentLinksWithNested) {
            parentLink.click();
        }
        for (ExtendedWebElement nestedLink : nestedLinks) {
            if (!nestedLink.isElementPresent()) {
                return false;
            }
        }
        return true;
    }

    public void isLinkClickRedirectToProperPage(String expectedLink, String[] lists, boolean nested) {
        logger.info("Validating link click redirection to proper page...");
        ExtendedWebElement actualLink;
        if (nested) {
            List<String> elements = Arrays.asList(lists);
            for (int i = 0; i < parentLinksWithNested.size(); i++) {
                if (parentLinksWithNested.get(i).getText().equals(expectedLink)) {
                    parentLinksWithNested.get(i).click();
                    for (int k = 0; k < elements.size(); k++) {
                        actualLink = findExtendedWebElement(By.xpath("//a[@title='" + elements.get(k) + "']"));
                        Assert.assertEquals(actualLink.getText().trim(), elements.get(k));
                        Assert.assertTrue(actualLink.isElementPresent());
                        Assert.assertTrue(actualLink.isClickable());
                        actualLink.click();
                        actualLink = findExtendedWebElement(By.xpath("//a[@title='" + elements.get(k) + "']"));
                        Assert.assertEquals(actualLink.getElement().getCssValue("color"), "rgba(6, 194, 126, 1)");
                        Assert.assertEquals(driver.getTitle(), elements.get(k) + " - Carina");
                    }
                }
            }
        } else {
            actualLink = findExtendedWebElement(By.xpath("//a[@title='" + expectedLink + "']"));
            Assert.assertEquals(actualLink.getText().trim(), expectedLink);
            Assert.assertTrue(actualLink.isElementPresent());
            Assert.assertTrue(actualLink.isClickable());
            actualLink.click();
            actualLink = findExtendedWebElement(By.xpath("//a[@title='" + expectedLink + "']"));
            Assert.assertEquals(actualLink.getElement().getCssValue("color"), "rgba(6, 194, 126, 1)");
            if (actualLink.getText().trim().equals("Overview")) {
                Assert.assertEquals(driver.getTitle(), "Carina");
            } else {
                Assert.assertEquals(driver.getTitle(), actualLink.getText().trim() + " - Carina");
            }
        }
    }
}
