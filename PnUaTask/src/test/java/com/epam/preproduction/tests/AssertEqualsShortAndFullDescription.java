package com.epam.preproduction.tests;

import com.epam.preproduction.teststeps.AssertEqualsShortAndFullDescriptionSteps;
import com.epam.preproduction.teststeps.ComparisonOfMicrowaveSteps;
import com.epam.preproduction.teststeps.VerifySortingByNameAndPriceSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class AssertEqualsShortAndFullDescription extends SetTestSetting
{

    AssertEqualsShortAndFullDescriptionSteps assertDescriptionSteps;


    @BeforeClass
    public void beforeClass() {
        assertDescriptionSteps = new AssertEqualsShortAndFullDescriptionSteps(driver);
    }

    @Test
    public void comparsionOfProductsTest() {
        assertDescriptionSteps.navigateToConditionerCatalog().assertShortAndFullDescription();
    }

    @AfterClass
    public void afterClass() {
        assertDescriptionSteps = null;
    }
}