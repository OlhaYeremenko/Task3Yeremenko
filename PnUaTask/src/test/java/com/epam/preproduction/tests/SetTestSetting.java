package com.epam.preproduction.tests;

import com.epam.preproduction.driverfactory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public  class SetTestSetting
{
    public WebDriver driver;
    ComparsionOfMicrowave comparsionOfMicrowave ;
    VerifyPriceFilter verifyPriceFilter ;
    VerifySortingByNameAndPrice verifySortingByNameAndPrice ;


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
