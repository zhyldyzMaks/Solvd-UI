package com.solvd.qa.carina.demo.zebrunner.web.components.navigation;

import org.testng.annotations.DataProvider;

public class NavigationData {

    @DataProvider(name = "navigationLinks")
    public Object[][] getData() {
        return new Object[][]{
                {"Overview", new String[]{}, false},
                {"Getting started", new String[]{}, false},
                {"Project structure", new String[]{}, false},
                {"Configuration", new String[]{}, false},
                {"Execution", new String[]{}, false},
                {"Automation", new String[]{"Web", "Mobile", "API", "Windows"}, true},
                {"Advanced", new String[]{"Database", "DataProvider", "Driver", "Localization", "Program flow", "Proxy", "Screenshot", "Security"}, true},
                {"Integration", new String[]{"Zebrunner"}, true},
                {"Cucumber", new String[]{}, false},
                {"Contribution", new String[]{}, false},
                {"Migration Guide", new String[]{}, false},
        };
    }
}
