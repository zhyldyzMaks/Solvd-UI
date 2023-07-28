package com.solvd.qa.carina.demo.zebrunner.web.components.navigation;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CarinaNavigationPage extends AbstractUIObject {

    @FindBy(xpath = "//ul[@class='md-nav__list']//li")
    List<ExtendedWebElement> navigationElements;

    @FindBy(xpath = "//nav[@aria-label='Navigation']//label[@for='__drawer']")
    private ExtendedWebElement carinaHeading;

    @FindBy(xpath = "//nav[@aria-label='Navigation']")
    private ExtendedWebElement navigationParentTag;

    @FindBy(xpath = "//nav[@aria-label='Navigation']/*")
    List<ExtendedWebElement> navigationChildTags;

    public CarinaNavigationPage(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void clickGettingStartedLink() {
        for (ExtendedWebElement element : navigationElements) {
            if (element.getText().equalsIgnoreCase("Getting started")) {
                element.click();
                break;
            }
        }
    }

    public boolean isCarinaHeadingFirstElement() {
        if (navigationChildTags.size() > 0) {
            for (ExtendedWebElement element : navigationChildTags) {
                if (element.equals(carinaHeading)) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public boolean isNavigationLinkListPresent() {
        for (ExtendedWebElement element : navigationElements) {
            if (element.isElementPresent()) {
                return true;
            }
        }return false;
    }

    public String isCurrentPageLinkHighlighted(ExtendedWebElement element, String propertyName) {
        WebDriver driver = element.getDriver();
        WebElement webElement = element.getElement();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "return window.getComputedStyle(arguments[0]).getPropertyValue(arguments[1]);";
        return (String) js.executeScript(script, webElement, propertyName);
    }

    public ExtendedWebElement getCurrentPageLink() {
        for (ExtendedWebElement element : navigationElements) {
            element.click();
            String classes = element.getAttribute("class");
            if (classes != null && classes.contains("active")) {
                return element;
            }
        }
        return null;
    }
}
