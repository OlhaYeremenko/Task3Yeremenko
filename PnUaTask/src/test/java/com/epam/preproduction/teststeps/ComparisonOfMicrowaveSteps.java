package com.epam.preproduction.teststeps;

import java.util.ArrayList;
import java.util.List;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.pages.ProductComparisonPage;
import com.epam.preproduction.pages.ProductInfoPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by olia on 06.06.2015.
 */
public class ComparisonOfMicrowaveSteps {
	ProductCatalog catalog;
	MainPage mainPage;
	ProductComparisonPage comparisonPage;
	ProductInfoPage productInfo;

	public static final String ITEM_LIST = "//div[@class='catalog']/div[@class='item']";
	public static final String LINK_PRODUCT_1_COMPARSION = ".//div[@class='item'][1]//*[@class='compare-links']//span";
	public static final String LINK_PRODUCT_2_COMPARSION = ".//div[@class='item'][2]//*[@class='compare-links']//span";
	public static final String LINK_PRODUCT_1_INFO = "//*[@class='row-2'][1]//a";
	public static final String LINK_PRODUCT_2_INFO = "//*[@class='row-2'][2]//a";
    public static final String PRODUCT_INFO_HEADER="//*[@id='page-subheader']/h1";
	public static final String PRODUCT_1_PARAMETERS = "//tbody//tr[not(@style)]//td[ not(@class) and not(.//div)][2]";
	public static final String PRODUCT_2_PARAMETERS = "//tbody//tr[not(@style)]//td[ not(@class) and not(.//div)][3]";
	public static final String PRODUCT = "//tbody/tr[@class='different' or @class='']";
	public static final String MIN_PRICE_FILTER_12000 = "//div[contains(@class,'group')][1]//*[@class='is_empty_items']//a[contains(text(),'12')]";
	public static final String MAX_PRICE_FILTER_27000 = "//div[contains(@class,'group')][2]//*[@class='is_empty_items']//a[contains(text(),'27')]";
	public static final String LAST_SEARCH_PAGE = "//*[@class='custom_pn_pager']//*[contains(@class,'last')]/a ";
	public static final String FIRST_PRODUCT_PRICE = "//div[@class='item'][1]/div[@class='price']/strong";
	public static final String LAST_PRODUCT_PRICE = "//div[@class='item'][2]/div[@class='price']/strong";

	public ComparisonOfMicrowaveSteps(WebDriver driver)
	{
		catalog = new ProductCatalog(driver);
		mainPage = new MainPage(driver);

	}

	public ComparisonOfMicrowaveSteps navigateToMicrowaveCatalog()
	{

		mainPage.navigateTo("http://pn.com.ua/");

		catalog = mainPage.microwaveCategoryClick();

		assertThat(catalog.getDriver().getTitle(), containsString("Микроволн"));

		return this;
	}

	public ComparisonOfMicrowaveSteps addProductToComparison()
	{

		WebElement product1 = catalog.getDriver().findElement(
				By.xpath(LINK_PRODUCT_1_COMPARSION));

		product1.click();

		WebElement product2 = catalog.getDriver().findElement(
				By.xpath(LINK_PRODUCT_2_COMPARSION));

		product2.click();

		comparisonPage = catalog.naivageToComaparsionPage();

		assertThat(comparisonPage.getDriver().getTitle(),
				containsString("сравнение"));
		return this;
	}


	public ComparisonOfMicrowaveSteps assertThatAllParametersPresent()
	{

		Waiter.waitForElementPresent(comparisonPage.getDriver(),
				LINK_PRODUCT_1_INFO);

		productInfo = comparisonPage.navigateToProduct(LINK_PRODUCT_1_INFO);

		Waiter.waitForElementPresent(productInfo.getDriver(),PRODUCT_INFO_HEADER);

		List<String> paramFromInfo = productInfo.getProductParameters();

		comparisonPage = productInfo.naivageToComaparsionPage();

		/* assertion for product 1 */

		List<WebElement> webParamFromComparison = catalog.getDriver()
				.findElements(By.xpath(PRODUCT_1_PARAMETERS));

		List<String> paramFromComparison = new ArrayList<String>();

		for (WebElement element : webParamFromComparison) {
			paramFromComparison.add(element.getText());
		}

		for (String p : paramFromInfo) {
			assertThat(paramFromComparison, hasItem(p));
		}
		/* assertion for product 2 */

		Waiter.waitForElementPresent(comparisonPage.getDriver(),
				LINK_PRODUCT_2_INFO);

		productInfo = comparisonPage.navigateToProduct(LINK_PRODUCT_2_INFO);

		Waiter.waitForElementPresent(productInfo.getDriver(),
				"//*[@id='page-subheader']/h1");

		paramFromInfo = productInfo.getProductParameters();

		comparisonPage = productInfo.naivageToComaparsionPage();

		webParamFromComparison = comparisonPage.getDriver().findElements(
				By.xpath(PRODUCT_2_PARAMETERS));

		for (WebElement element : webParamFromComparison) {
			paramFromComparison.add(element.getText());
		}

		for (String p : paramFromInfo) {
			assertThat(paramFromComparison, hasItem(p));
		}

		return this;
	}

	public ComparisonOfMicrowaveSteps verifyDifferentParameterColour()
	{

		List<WebElement> webParamComparsProduct1 = comparisonPage.getDriver()
				.findElements(By.xpath(PRODUCT_1_PARAMETERS));

		List<WebElement> classNameList = comparisonPage.getDriver()
				.findElements(By.xpath(PRODUCT_1_PARAMETERS + "/.."));

		List<String> paramComparsProduct1 = new ArrayList<String>();

		for (WebElement element : webParamComparsProduct1)
		{
			paramComparsProduct1.add(element.getText());
		}

		List<WebElement> webParamComparsProduct2 = comparisonPage.getDriver()
				.findElements(By.xpath(PRODUCT_2_PARAMETERS));

		List<String> paramComparsProduct2 = new ArrayList<String>();

		for (WebElement element : webParamComparsProduct2) 
		{
			paramComparsProduct2.add(element.getText());
		}

		for (int i = 0; i < paramComparsProduct2.size() - 1; i++) 
		{

			if (!paramComparsProduct1.get(i).equalsIgnoreCase(
					paramComparsProduct2.get(i))) 
			{

				assertThat(classNameList.get(i).getAttribute("class"),
						containsString("different"));
			}
		}

		return this;
	}

	public ComparisonOfMicrowaveSteps verifyMinMaxFilter()
    {

		mainPage = comparisonPage.naivageToMainPage();

		Waiter.waitForElementPresent(mainPage.getDriver(),
				"//*[@id='edit-name-1']");

		catalog = mainPage.fridgeCategoryClick();

		Waiter.waitForElementPresent(mainPage.getDriver(),
				MIN_PRICE_FILTER_12000);

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
