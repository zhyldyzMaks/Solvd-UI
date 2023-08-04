package com.solvd.qa.carina.demo;

import com.solvd.qa.carina.demo.zebrunner.web.components.navigationMenu.CarinaNavigationComponent;
import com.solvd.qa.carina.demo.zebrunner.web.components.navigationMenu.NavigationData;
import com.solvd.qa.carina.demo.zebrunner.web.pages.CarinaHomePage;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ZebRunnerCarinaTest implements IAbstractTest {

    private CarinaHomePage homePage = null;

    @BeforeTest
    public void startDriver() {
        homePage = new CarinaHomePage(getDriver());
    }

    @Test
    public void testZebRunnerLogoAndCarinaBrand() {
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getHeaderObject().isZebRunnerLogoOnTheLeft(),
                "ZebRunner logo is not on the left of the webpage.");
        homePage.getHeaderObject().clickZebRunnerLogo();
        Assert.assertTrue(homePage.isOverviewHeaderVisible(),
                "Overview header is not visible");
        homePage.assertPageOpened();
        Assert.assertEquals(homePage.getHeaderObject().getBrandText(), "Carina",
                "Carina brand is not displayed on a header.");
    }

    @Test
    public void testSearchComponent() {
        homePage.open();
        homePage.assertPageOpened();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.getSearchComponentObject().isSearchComponentDisplayed(),
                "Search component not displayed.");
        softAssert.assertTrue(homePage.getSearchComponentObject().isSearchIconPresent(),
                "Search icon is not present in the search input.");
        softAssert.assertTrue(homePage.getSearchComponentObject().isSearchTextPresent(),
                "Search text is not present in the search input.");
        softAssert.assertAll();
    }

    @Test
    public void testGitHubLink() {
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertEquals(homePage.getHeaderObject().validateGitHubPageUrl(),
                "https://github.com/zebrunner/carina/",
                "Clicking on GitHub link did not redirect to GitHub page.");
    }

    @Test
    public void testHeaderIsSticky() {
        homePage.open();
        homePage.assertPageOpened();
        homePage.getFooterMenuObject().scrollIntoFooterMenu();
        Assert.assertTrue(homePage.isHeaderSticky(),
                "Header is not visible or not attached to the top of the page");
    }

    @Test()
    public void testNavElementVisibilityAndHiddenElementsPresence() {
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getNavigationObject().isCarinaHeadingFirstElement(),
                "Carina heading is not the first element of the navigation menu.");
        Assert.assertTrue(homePage.getNavigationObject().isNavigationLinkListPresent(),
                "Navigation links list is not present.");
        Assert.assertTrue(homePage.getNavigationObject().isCurrentLinkHighlighted(),
                "Current page is not highlighted");
        Assert.assertTrue(homePage.getNavigationObject().areNestedElementsHidden(),
                "Sub-links are not hidden.");
    }

    @Test(dataProvider = "nestedLinksTitles", dataProviderClass = NavigationData.class)
    public void testSubLinksVisibilityOnParentLinkClick(String title, String[] nestedPageTitles) {
        homePage.open();
        homePage.assertPageOpened();
        Assert.assertTrue(homePage.getNavigationObject().areNestedElementsRevealed(title, nestedPageTitles),
                "Clicking on parent link did not reveal link/s of sub-pages.");
    }

    @Test(dataProvider = "navigationLinksTitles", dataProviderClass = NavigationData.class)
    public void testNavigationWorksProperly(String title, String[] nestedPageTitles) {
        homePage.open();
        homePage.assertPageOpened();
        CarinaNavigationComponent navigationPage = homePage.getNavigationObject();
        navigationPage.checkLinkRedirection(title, nestedPageTitles);
    }
}
