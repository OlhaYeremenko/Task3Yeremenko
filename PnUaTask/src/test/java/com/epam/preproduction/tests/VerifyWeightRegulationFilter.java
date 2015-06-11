package com.epam.preproduction.tests;


import com.epam.preproduction.teststeps.VerifyWeightRegulationFilterSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class VerifyWeightRegulationFilter extends SetTestSetting
{


    VerifyWeightRegulationFilterSteps verifyWeightRegulationFilterSteps;

    @BeforeClass
    public void beforeClass()
    {
        verifyWeightRegulationFilterSteps = new VerifyWeightRegulationFilterSteps(driver);
    }

    @Test
    public void comparsionOfProductsTest()
    {
        verifyWeightRegulationFilterSteps.navigateToBreadmakerCatalog().selectWeightRegulationFilter().VerifyWeightRegulationFilter();
    }

    @AfterClass
    public void afterClass()
    {
        verifyWeightRegulationFilterSteps = null;
    }

}