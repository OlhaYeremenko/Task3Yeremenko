package com.epam.preproduction.tests;

import com.epam.preproduction.teststeps.ComparisonOfMicrowaveSteps;
import com.epam.preproduction.teststeps.VerifyPriceFilterSteps;
import com.epam.preproduction.teststeps.VerifySortingByNameAndPriceSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class VerifyPriceFilter extends SetTestSetting
{

	VerifySortingByNameAndPriceSteps sortSteps;
	VerifyPriceFilterSteps verifyPriceFilterSteps;

	@BeforeClass
	public void beforeClass()
    {
        verifyPriceFilterSteps = new VerifyPriceFilterSteps(driver);
	}

	@Test
	public void ComparsionOfProductsTest()
    {
        verifyPriceFilterSteps.navigateToFrigeCatalog().verifyMinMaxFilter();
	}

	@AfterClass
	public void afterClass()
    {
        verifyPriceFilterSteps = null;
	}

}