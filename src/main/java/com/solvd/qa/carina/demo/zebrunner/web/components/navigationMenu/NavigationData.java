package com.solvd.qa.carina.demo.zebrunner.web.components.navigationMenu;

import org.testng.annotations.DataProvider;

public class NavigationData {

    @DataProvider(name = "navigationLinksTitles")
    public Object[][] getData() {
        return new Object[][]{
                {"Overview", new String[]{}},
                {"Getting started", new String[]{}},
                {"Project structure", new String[]{}},
                {"Configuration", new String[]{}},
                {"Execution", new String[]{}},
                {"Automation", new String[]{"Web", "Mobile", "API", "Windows"}},
                {"Advanced", new String[]{"Database", "DataProvider", "Driver", "Localization", "Program flow", "Proxy", "Screenshot", "Security"}},
                {"Integration", new String[]{"Zebrunner"}},
                {"Cucumber", new String[]{}},
                {"Contribution", new String[]{}},
                {"Migration Guide", new String[]{}},
        };
    }

    @DataProvider(name = "nestedLinksTitles")
    public Object[][] getTitle() {
        return new Object[][]{
                {"Automation", new String[]{"Web", "Mobile", "API", "Windows"}},
                {"Advanced", new String[]{"Database", "DataProvider", "Driver", "Mobile", "Localization", "Program flow", "Proxy", "Screenshot", "Security"}},
                {"Integration", new String[]{"Zebrunner"}}
        };
    }
}
