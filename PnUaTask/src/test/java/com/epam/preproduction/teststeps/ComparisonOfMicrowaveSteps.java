package com.epam.preproduction.teststeps;

import java.util.ArrayList;
import java.util.List;

import com.epam.preproduction.helpers.GetPrice;
import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.pages.ProductComparisonPage;
import com.epam.preproduction.pages.ProductInfoPage;

import com.epam.preproduction.testdata.Global;
import com.epam.preproduction.testdata.ProductCategories;
import com.sun.javafx.tk.quantum.GlassAppletWindow;
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
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class ComparisonOfMicrowaveSteps {
	private ProductCatalog catalog;
	private MainPage mainPage;
	private ProductComparisonPage comparisonPage;
	private ProductInfoPage productInfo;


    private static final String LINK_PRODUCT_1_COMPARSION = "//div[@class='item'][1]//*[@class='compare-links']//span";
    private static final String LINK_PRODUCT_2_COMPARSION = "//div[@class='item'][2]//*[@class='compare-links']//span";
    private static final String LINK_PRODUCT_1_INFO = "//div[@class='row-2'][1]//a";
    private static final String LINK_PRODUCT_2_INFO = "//div[@class='row-2'][2]//a";
    private static final String PRODUCT_INFO_HEADER="//*[@id='page-subheader']/h1";
    private static final String PRODUCT_1_PARAMETERS = "//tbody//tr[not(@style)]//td[ not(@class) and not(.//div)][2]";
    private static final String PRODUCT_2_PARAMETERS = "//tbody//tr[not(@style)]//td[ not(@class) and not(.//div)][3]";
    private static final String MIN_PRICE_FILTER_12000 = "//div[contains(@class,'group')][1]//*[@class='is_empty_items']//a[contains(text(),'12')]";
    private static final String MAX_PRICE_FILTER_27000 = "//div[contains(@class,'group')][2]//*[@class='is_empty_items']//a[contains(text(),'27')]";
    private static final String LAST_SEARCH_PAGE = "//div[@class='custom_pn_pager']//*[contains(@class,'last')]/a ";
    private static final String FIRST_PRODUCT_PRICE = "//div[@class='item'][1]/div[@class='price']/strong";
    private static final String LAST_PRODUCT_PRICE = "//div[@class='item'][2]/div[@class='price']/strong";
    private static final String EDIT_NAME = "//div[@id='edit-name-1']";

	public ComparisonOfMicrowaveSteps(WebDriver driver)
	{
		catalog = new ProductCatalog(driver);
		mainPage = new MainPage(driver);

	}

	public ComparisonOfMicrowaveSteps navigateToMicrowaveCatalog()
	{
        Global globalSetting = new Global();
        mainPage.navigateTo(globalSetting.SITE_ADRESS);
        assertThat(mainPage.getDriver().getTitle(), containsString(Global.MAIN_TITLE));
		catalog = mainPage.microwaveCategoryClick();

		assertThat(catalog.getDriver().getTitle(), containsString(ProductCategories.Микроволнов.toString()));

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

        Waiter.waitForElementPresent(catalog.getDriver(),
                LINK_PRODUCT_2_COMPARSION);

		comparisonPage = catalog.naivageToComaparsionPage();

		assertThat(comparisonPage.getDriver().getTitle(),
				containsString(Global.MICROWAVE_TITLE));
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
                PRODUCT_INFO_HEADER);

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
                EDIT_NAME);
		catalog = mainPage.fridgeCategoryClick();

		Waiter.waitForElementPresent(mainPage.getDriver(),
				MIN_PRICE_FILTER_12000);

		catalog.setFilter(MIN_PRICE_FILTER_12000);

		int minPriceBorder =  GetPrice.getPrice(catalog.getDriver(),MIN_PRICE_FILTER_12000);

		Waiter.waitForElementPresent(mainPage.getDriver(),
				MAX_PRICE_FILTER_27000);

		catalog.setFilter(MAX_PRICE_FILTER_27000);

		int maxPriceBorder = GetPrice.getPrice(catalog.getDriver(),MAX_PRICE_FILTER_27000);

		int actualMinPrice = GetPrice.getPrice(catalog.getDriver(),FIRST_PRODUCT_PRICE);

		WebElement lastPage = catalog.getDriver().findElement(
				By.xpath(LAST_SEARCH_PAGE));

		lastPage.click();
		int actualMaxPrice = GetPrice.getPrice(catalog.getDriver(),LAST_PRODUCT_PRICE);

		assertThat(minPriceBorder, (lessThanOrEqualTo(actualMinPrice)));
		assertThat(actualMaxPrice, lessThanOrEqualTo(maxPriceBorder));

		return this;

	}

}
