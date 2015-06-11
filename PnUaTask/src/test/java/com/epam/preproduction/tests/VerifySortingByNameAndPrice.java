package com.epam.preproduction.tests;

import com.epam.preproduction.teststeps.VerifySortingByNameAndPriceSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class VerifySortingByNameAndPrice extends SetTestSetting
{

    VerifySortingByNameAndPriceSteps sortSteps;

    @BeforeClass
    public void beforeClass()
    {
        sortSteps = new VerifySortingByNameAndPriceSteps(driver);
    }

    @Test
    public void SortFridgeByPriceAndNameTest()
    {
        sortSteps.navigateToFridgeCatalog().sortFridgeByPrice()
                .sortFridgeByName().backToMain();
    }

    @AfterClass
    public void afterClass()
    {
        sortSteps = null;
    }

}