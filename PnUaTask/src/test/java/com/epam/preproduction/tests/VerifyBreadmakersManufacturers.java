package com.epam.preproduction.tests;

import com.epam.preproduction.teststeps.ComparisonOfMicrowaveSteps;
import com.epam.preproduction.teststeps.VerifyBreadmakerManufacturersSteps;
import com.epam.preproduction.teststeps.VerifySortingByNameAndPriceSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class VerifyBreadmakersManufacturers extends SetTestSetting
{


    private  VerifyBreadmakerManufacturersSteps verifyBreadmakerManufacturersSteps;

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