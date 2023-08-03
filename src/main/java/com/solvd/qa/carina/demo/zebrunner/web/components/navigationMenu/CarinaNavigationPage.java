package com.solvd.qa.carina.demo.zebrunner.web.components.navigationMenu;

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

    @FindBy(xpath = "//nav[@aria-label='Navigation']//label[@for='__drawer']")
    private ExtendedWebElement carinaHeading;

    @FindBy(xpath = "//li[contains(@class, '%s')]")
    private ExtendedWebElement curPageDynamicXpath;

    @FindBy(xpath = "//li[contains(@class,'md-nav__item')]//*[contains(text(),'%s')]")
    private ExtendedWebElement title;

    @FindBy(xpath = "(//li[@class='md-nav__item']//*[contains(text(),'Mobile')])[2]")
    private ExtendedWebElement mobileLinkInAdvanced;

    @FindBy(xpath = "//nav[@aria-label='Navigation']/*")
    private List<ExtendedWebElement> navigationBarNestedTags;

    @FindBy(xpath = "//li[contains(@class, 'md-nav__item')]")
    private List<ExtendedWebElement> allNavLinks;

    @FindBy(xpath = "//li[contains(@class,'md-nav__item md-nav__item--nested')]")
    private List<ExtendedWebElement> parentLinks;

    @FindBy(xpath = "//li[contains(@class,'nested')]//li[@class='md-nav__item']/a")
    private List<ExtendedWebElement> nestedLinks;

    @FindBy(xpath = "//nav[@aria-label='Navigation']/ul/li")
    private List<ExtendedWebElement> allOuterLinks;

    public CarinaNavigationPage(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public boolean isCarinaHeadingFirstElement() {
        logger.info("Checking if Carina heading is the first element...");
        if (navigationBarNestedTags.size() > 0) {
            return navigationBarNestedTags.get(0).getText().equals(carinaHeading.getText());
        }
        return false;
    }

    public boolean isNavigationLinkListPresent() {
        logger.info("Checking if Navigation links list present...");
        boolean allLinksPresent = true;

        for (ExtendedWebElement navLink : allOuterLinks) {
            if (!navLink.isElementPresent()) {
                allLinksPresent = false;
                break;
            }
        }
        return allLinksPresent;
    }

    public boolean isCurrentLinkHighlighted() {
        logger.info("Checking if current page is highlighted...");
        String expectedColor = "rgba(6, 194, 126, 1)";
        for (int i = 0; i < allNavLinks.size(); i++) {
            ExtendedWebElement currentLink = curPageDynamicXpath.format("md-nav__item md-nav__item--active");
            ExtendedWebElement currentNestedLink = curPageDynamicXpath.format("md-nav__item md-nav__item--active md-nav__item--nested");
            if (allNavLinks.get(i).equals(currentLink) || allNavLinks.get(i).equals(currentNestedLink)) {
                String actualColor = allNavLinks.get(i).getElement().getCssValue("color");
                if (!actualColor.equals(expectedColor)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean areNestedElementsHidden() {
        logger.info("Checking if nested elements are hidden...");
        boolean areHidden = false;
        for (ExtendedWebElement element : nestedLinks) {
            if (!element.isElementPresent()) {
                areHidden = true;
                break;
            }
        }
        return areHidden;
    }

    public boolean areNestedElementsRevealed(String parentPageTitle, String[] nestedPageTitles) {
        logger.info("Checking if nested elements are revealed...");
        for (ExtendedWebElement parentLink : parentLinks) {
            if (parentLink.getText().equals(parentPageTitle)) {
                Assert.assertTrue(parentLink.isElementPresent());
                parentLink.click();
                List<ExtendedWebElement> nestedLinks = parentLink.findExtendedWebElements(By.xpath(
                        "//li[contains(@class,'nested')]//li[@class='md-nav__item']/a"));
                for (String expectedNestedPageTitle : nestedPageTitles) {
                    boolean nestedPageFound = false;
                    for (ExtendedWebElement nestedLink : nestedLinks) {
                        if (nestedLink.getText().equals(expectedNestedPageTitle)) {
                            nestedPageFound = true;
                            break;
                        }
                    }
                    Assert.assertTrue(nestedPageFound, "Nested-page with title: " + expectedNestedPageTitle + " is not found.");
                }
                return true;
            }
        }
        return false;
    }

    public void checkLinkRedirection(String expectedLinkTitle, String[] nestedPageTitles) {
        logger.info("Checking if clicking on link redirects to the proper page...");
        ExtendedWebElement currentLink;
        if (nestedPageTitles.length > 0) {
            List<String> elements = Arrays.asList(nestedPageTitles);
            for (int i = 0; i < parentLinks.size(); i++) {
                if (parentLinks.get(i).getText().equals(expectedLinkTitle)) {
                    parentLinks.get(i).click();
                    for (int k = 0; k < elements.size(); k++) {
                        currentLink = title.format(elements.get(k));
                        Assert.assertEquals(currentLink.getText().trim(), elements.get(k));
                        Assert.assertTrue(currentLink.isElementPresent());
                        Assert.assertTrue(currentLink.isClickable());
                        currentLink.click();
                        Assert.assertEquals(currentLink.getElement().getCssValue("color"), "rgba(6, 194, 126, 1)");
                        Assert.assertEquals(driver.getTitle().trim(), elements.get(k) + " - Carina");
                        if (expectedLinkTitle.equals("Advanced") && elements.get(k).equals("Mobile")) {
                            currentLink = mobileLinkInAdvanced;
                            Assert.assertEquals(currentLink.getText().trim(), elements.get(k));
                            Assert.assertTrue(currentLink.isElementPresent());
                            Assert.assertTrue(currentLink.isClickable());
                            currentLink.click();
                            Assert.assertEquals(currentLink.getElement().getCssValue("color"), "rgba(6, 194, 126, 1)");
                            Assert.assertEquals(driver.getTitle().trim(), "Mobile - Carina");
                        }
                    }
                }
            }
        } else {
            currentLink = title.format(expectedLinkTitle);
            Assert.assertEquals(currentLink.getText().trim(), expectedLinkTitle);
            Assert.assertTrue(currentLink.isElementPresent());
            Assert.assertTrue(currentLink.isClickable());
            currentLink.click();
            Assert.assertEquals(currentLink.getElement().getCssValue("color"), "rgba(6, 194, 126, 1)");
            if (currentLink.getText().trim().equals("Overview")) {
                Assert.assertEquals(driver.getTitle().trim(), "Carina");
            } else {
                Assert.assertEquals(driver.getTitle().trim(), expectedLinkTitle + " - Carina");
            }
        }
    }
}
