package com.solvd.qa.carina.demo;

import com.solvd.qa.carina.demo.zebrunner.web.components.navigation.CarinaNavigationPage;
import com.solvd.qa.carina.demo.zebrunner.web.components.navigation.NavigationData;
import com.solvd.qa.carina.demo.zebrunner.web.pages.desktop.CarinaHomePage;
import com.solvd.qa.carina.demo.zebrunner.web.pages.desktop.GitHubPage;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZebRunnerCarinaTest implements IAbstractTest {

    @Test
    public void testZebRunnerLogo() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getHeader().isZebRunnerLogoDisplayed(), "Logo validation failed.");
        homePage.getHeader().clickZebRunnerLogo();
        Assert.assertTrue(homePage.isOverviewHeaderVisible(), "Overview header is not visible");
        homePage.assertPageOpened();
    }

    @Test
    public void testCarinaBrand() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertEquals(homePage.getHeader().isCarinaBrandDisplayed(), "Carina");
    }

    @Test
    public void testSearchComponent() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        homePage.getHeader().isSearchComponentDisplayed();
        String searchText = homePage.getHeader().getSearchText();
        Assert.assertEquals(searchText, "Search", "Actual search text does not match the expected value");
    }

    @Test
    public void testGitHubLink() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        ExtendedWebElement gitHubLink = homePage.getHeader().getGitHubLink();
        Assert.assertTrue(gitHubLink.isElementPresent(), "GiHub link is not displayed.");
        gitHubLink.click();
        GitHubPage gitHubPage = new GitHubPage(getDriver());
        Assert.assertEquals(gitHubPage.getCurrentUrl(), "https://github.com/zebrunner/carina/", "The link " +
                "did not redirect to Carina Github page");
    }

    @Test
    public void testHeaderIsSticky() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        homePage.getFooterMenu().scrollIntoFooterMenu();
        Assert.assertTrue(homePage.isHeaderSticky(), "Header is not visible or not attached to the top of the page");
    }

    @Test
    public void testNavigationElementVisibility() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        boolean isFirstElementCarina = homePage.getNavigation().isCarinaHeadingFirstElement();
        Assert.assertTrue(isFirstElementCarina, "Carina heading is not the first element of the navigation menu.");
        Assert.assertTrue(homePage.getNavigation().isNavigationLinkListPresent(), "Navigation link list is not present.");
        Assert.assertTrue(homePage.getNavigation().isHighlighted(), "Current page is not highlighted");
    }

    @Test
    public void testHiddenComponents() {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getNavigation().areElementsHidden(), "Inner elements are not hidden.");
        Assert.assertTrue(homePage.getNavigation().areNestedElementsRevealed(), "Clicking on parent link " +
                "did not reveal link/s of nested pages.");
    }

    @Test(dataProvider = "navigationLinks", dataProviderClass = NavigationData.class)
    public void testNavigationWorksProperly(String link, String[] lists, boolean nested) {
        CarinaHomePage homePage = new CarinaHomePage(getDriver());
        homePage.open();
        homePage.assertPageOpened();
        CarinaNavigationPage navigationPage = homePage.getNavigation();
        navigationPage.isLinkClickRedirectToProperPage(link, lists, nested);
    }
}
