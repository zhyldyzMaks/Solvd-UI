package com.solvd.qa.carina.demo;

import com.solvd.qa.carina.demo.zebrunner.web.components.navigation.CarinaNavigationPage;
import com.solvd.qa.carina.demo.zebrunner.web.pages.CarinaHomePage;
import com.solvd.qa.carina.demo.zebrunner.web.pages.GitHubPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZebRunnerCarinaTest implements IAbstractTest {

    private static final Logger logger = LogManager.getLogger(ZebRunnerCarinaTest.class);

    @Test
    public void testLogoPresenceAndOverviewPageRedirect() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        logger.info("Validating the ZebRunner logo...");
        Assert.assertTrue(homePage.getHeader().isZebRunnerLogoDisplayed(),"Logo validation failed.");
        homePage.getHeader().clickZebRunnerLogo();
        Assert.assertTrue(homePage.isOverviewHeaderVisible(), "Overview header is not visible");
        logger.info("Asserting that the Overview page is opened after the logo click.");
        homePage.assertPageOpened();
    }

    @Test
    public void testCarinaBrand() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        Assert.assertEquals(homePage.getHeader().isCarinaBrandDisplayed(),"Carina");
    }

    @Test
    public void testSearchComponent() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        homePage.getHeader().isSearchComponentDisplayed();
        String searchText = homePage.getHeader().getSearchText();
        logger.info("Search Box has search text in input field");
        Assert.assertEquals(searchText, "Search", "Actual search text does not match the expected value");
    }

    @Test
    public void tesGitHubLinkPresenceAndGitHubLinkRedirect() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        ExtendedWebElement gitHubLink = homePage.getHeader().getGitHubLink();
        Assert.assertTrue(gitHubLink.isElementPresent(), "GiHub link is not displayed.");
        gitHubLink.click();
        GitHubPage gitHubPage = new GitHubPage(getDriver());
        Assert.assertEquals(gitHubPage.getCurrentUrl(),"https://github.com/zebrunner/carina/", "The link did nor redirect to Carina Github page");
    }

    @Test
    public void testHeaderIsSticky() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.getFooterMenu().scrollIntoFooterMenu();
        Assert.assertTrue(homePage.isHeaderSticky(),"Header is not visible or not attached to the top of the page");
    }

    @Test
    public void testNavigationValidate() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.getNavigation().isNavigationLinkListPresent(), "Navigation link list is not present.");
//        boolean isFirstElementCarina = homePage.getNavigation().isCarinaHeadingFirstElement();
//        Assert.assertTrue(isFirstElementCarina, "Carina heading is not the first element of the navigation menu.");
    }
    @Test
    public void testColorValidate() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        CarinaNavigationPage navigationPage = homePage.getNavigation();
        ExtendedWebElement currentPageLink = navigationPage.getCurrentPageLink();
        String colorValue = navigationPage.isCurrentPageLinkHighlighted(currentPageLink, "color");
        String expectedHighlightedColor = "rgba(6, 194, 126, 1)";
        Assert.assertEquals(colorValue, expectedHighlightedColor, "The current page link is not highlighted.");
    }
}
