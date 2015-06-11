package com.epam.preproduction.tests;

import com.epam.preproduction.teststeps.ComparisonOfMicrowaveSteps;
import com.epam.preproduction.teststeps.VerifyBreadmakerManufacturersSteps;
import com.epam.preproduction.teststeps.VerifySortingByNameAndPriceSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class VerifyBreadmakersManufacturers extends SetTestSetting
{


    VerifyBreadmakerManufacturersSteps verifyBreadmakerManufacturersSteps;

    @BeforeClass
    public void beforeClass()

    {
        verifyBreadmakerManufacturersSteps = new VerifyBreadmakerManufacturersSteps(driver);
    }

    @Test
    public void comparsionOfProductsTest()
    {
         verifyBreadmakerManufacturersSteps.navigateToBreadmakerCatalog().selectManufacturerFilters().verifyThatSelectedManufacturerPresent();
    }

    @AfterClass
    public void afterClass()
    {
        verifyBreadmakerManufacturersSteps = null;
    }

}