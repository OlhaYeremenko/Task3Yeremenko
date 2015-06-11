package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.pages.ProductComparisonPage;
import com.epam.preproduction.pages.ProductInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by olia on 06.06.2015.
 */
public class VerifyPriceFilterSteps
{
	ProductCatalog catalog;
	MainPage mainPage;

	public static final String MIN_PRICE_FILTER_12000 = "//div[contains(@class,'group')][1]//*[@class='is_empty_items']//a[contains(text(),'12')]";
	public static final String MAX_PRICE_FILTER_27000 = "//div[contains(@class,'group')][2]//*[@class='is_empty_items']//a[contains(text(),'27')]";
	public static final String LAST_SEARCH_PAGE = "//*[@class='custom_pn_pager']//*[contains(@class,'last')]/a ";
	public static final String FIRST_PRODUCT_PRICE = "//div[@class='item'][1]/div[@class='price']/strong";
	public static final String LAST_PRODUCT_PRICE = "//div[@class='item'][2]/div[@class='price']/strong";
    public static final String TITLE = "//*[@id='page-subheader']";

	public VerifyPriceFilterSteps(WebDriver driver)
	{
		catalog = new ProductCatalog(driver);
		mainPage = new MainPage(driver);

	}

    public VerifyPriceFilterSteps navigateToFrigeCatalog()
    {

        mainPage.navigateTo("http://pn.com.ua/");

        assertThat(mainPage.getDriver().getTitle(), containsString("Прайс навигатор"));

        catalog = mainPage.fridgeCategoryClick();

        Waiter.waitForElementPresent(mainPage.getDriver(),
                MIN_PRICE_FILTER_12000);

        assertThat(mainPage.getDriver().getTitle(), containsString("Холодильни"));

        return this;
    }


	public VerifyPriceFilterSteps verifyMinMaxFilter()
    {

        catalog.setFilter(MIN_PRICE_FILTER_12000);

		int minPriceBorder = Integer.parseInt(catalog.getDriver()
				.findElement(By.xpath(MIN_PRICE_FILTER_12000)).getText());

		Waiter.waitForElementPresent(mainPage.getDriver(),
				MAX_PRICE_FILTER_27000);

		catalog.setFilter(MAX_PRICE_FILTER_27000);

		int maxPriceBorder = Integer.parseInt(catalog.getDriver()
				.findElement(By.xpath(MAX_PRICE_FILTER_27000)).getText());

		int actualMinPrice = Integer.parseInt(catalog.getDriver()
				.findElement(By.xpath(FIRST_PRODUCT_PRICE)).getText()
				.replace(" ", "").replace("грн", ""));

		WebElement lastPage = catalog.getDriver().findElement(
				By.xpath(LAST_SEARCH_PAGE));

		lastPage.click();

		int actualMaxPrice = Integer.parseInt(catalog.getDriver()
				.findElement(By.xpath(LAST_PRODUCT_PRICE)).getText()
				.replace(" ", "").replace("грн", ""));

		assertThat(minPriceBorder, (lessThanOrEqualTo(actualMinPrice)));

		assertThat(actualMaxPrice, lessThanOrEqualTo(maxPriceBorder));

		return this;

	}

}
