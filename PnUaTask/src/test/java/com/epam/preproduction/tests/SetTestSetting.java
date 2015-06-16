package com.epam.preproduction.tests;

import com.epam.preproduction.driverfactory.WebDriverFactory;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.testdata.Global;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public  class SetTestSetting
{
    public WebDriver driver;
    private MainPage mainPage;



    @BeforeTest
    public void beforeSuite()
    {
        String browser = "chrome";
        WebDriverFactory driverFactory = new WebDriverFactory();
        driver = driverFactory.getDriver(browser);
    }

    @AfterTest
    public void afterSuite()
    {
        driver.quit();

    }
}
