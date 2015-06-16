package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.GetPrice;
import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.pages.ProductComparisonPage;
import com.epam.preproduction.pages.ProductInfoPage;
import com.epam.preproduction.testdata.Global;
import com.epam.preproduction.testdata.ProductCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class VerifyPriceFilterSteps
{
	private ProductCatalog catalog;
	private MainPage mainPage;


    private static final String MIN_PRICE_FILTER_12000 = "//div[contains(@class,'group')][1]//*[@class='is_empty_items']//a[contains(text(),'12')]";
    private static final String MAX_PRICE_FILTER_27000 = "//div[contains(@class,'group')][2]//*[@class='is_empty_items']//a[contains(text(),'27')]";
    private static final String LAST_SEARCH_PAGE = "//*[@class='custom_pn_pager']//*[contains(@class,'last')]/a ";
    private static final String FIRST_PRODUCT_PRICE = "//div[@class='item'][1]/div[@class='price']/strong";
    private static final String LAST_PRODUCT_PRICE = "//div[@class='item'][2]/div[@class='price']/strong";
    private static final String TITLE = "//*[@id='page-subheader']";


	public VerifyPriceFilterSteps(WebDriver driver)
	{
		catalog = new ProductCatalog(driver);
		mainPage = new MainPage(driver);

	}

    public VerifyPriceFilterSteps navigateToFrigeCatalog()
    {
        Global globalSetting = new Global();
        mainPage.navigateTo(globalSetting.SITE_ADRESS);
        assertThat(mainPage.getDriver().getTitle(), containsString(Global.MAIN_TITLE));
        catalog = mainPage.fridgeCategoryClick();
        Waiter.waitForElementPresent(mainPage.getDriver(),
                MIN_PRICE_FILTER_12000);
        assertThat(mainPage.getDriver().getTitle(), containsString(ProductCategories.Холодильни.toString()));
        return this;
    }


	public VerifyPriceFilterSteps verifyMinMaxFilter()
    {
        catalog.setFilter(MIN_PRICE_FILTER_12000);

		int minPriceBorder =  GetPrice.getPrice(catalog.getDriver(),MIN_PRICE_FILTER_12000);
		Waiter.waitForElementPresent(mainPage.getDriver(),
				MAX_PRICE_FILTER_27000);

		catalog.setFilter(MAX_PRICE_FILTER_27000);
		int maxPriceBorder =GetPrice.getPrice(catalog.getDriver(),MAX_PRICE_FILTER_27000);

		int actualMinPrice = GetPrice.getPrice(catalog.getDriver(),FIRST_PRODUCT_PRICE);
		WebElement lastPage = catalog.getDriver().findElement(
				By.xpath(LAST_SEARCH_PAGE));

		lastPage.click();
		int actualMaxPrice =  GetPrice.getPrice(catalog.getDriver(),LAST_PRODUCT_PRICE);

		assertThat(minPriceBorder, (lessThanOrEqualTo(actualMinPrice)));
		assertThat(actualMaxPrice, lessThanOrEqualTo(maxPriceBorder));

		return this;

	}

}
