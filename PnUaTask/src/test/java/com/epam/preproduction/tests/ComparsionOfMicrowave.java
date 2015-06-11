package com.epam.preproduction.tests;

import com.epam.preproduction.teststeps.ComparisonOfMicrowaveSteps;
import com.epam.preproduction.teststeps.VerifySortingByNameAndPriceSteps;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class ComparsionOfMicrowave extends SetTestSetting
{

	VerifySortingByNameAndPriceSteps sortSteps;
	ComparisonOfMicrowaveSteps comparaiontSteps;

	@BeforeClass
	public void beforeClass()
    {
		comparaiontSteps = new ComparisonOfMicrowaveSteps(driver);
	}

	@Test
	public void comparsionOfProductsTest()
    {
		comparaiontSteps.navigateToMicrowaveCatalog().addProductToComparison()
				.assertThatAllParametersPresent()
				.verifyDifferentParameterColour();
	}

	@AfterClass
	public void afterClass()
    {
		comparaiontSteps = null;
	}

}