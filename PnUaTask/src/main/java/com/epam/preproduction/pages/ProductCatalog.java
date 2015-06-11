package com.epam.preproduction.pages;

import com.epam.preproduction.template.AbstractPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by olia on 06.06.2015.
 */
public class ProductCatalog extends AbstractPage {

	public static final String PRCIE_SORT_LINK = "//*[contains(@class,'column width-75')] //*[@class='links-bar']//a[1]";
	public static final String PRODUCT_NAME_SORT_LINK = "//*[contains(@class,'column width-75')] //*[@class='links-bar']//a[1]";
	public static final String MAIN_PAGE_LINK = " //*[@id='page-breadcrumbs']/a";
	public static final String LINK_TO_COMPARSION = "//*[@class='item'][1]//*[@class='compare_redirect_link']//a";

	@FindBy(xpath = PRCIE_SORT_LINK)
	private WebElement priceSortLink;

	@FindBy(xpath = MAIN_PAGE_LINK)
	private WebElement mainPageLink;

	@FindBy(xpath = PRODUCT_NAME_SORT_LINK)
	private WebElement productNameSortLink;

	@FindBy(xpath = LINK_TO_COMPARSION)
	private WebElement comparsionLink;

	public ProductCatalog(WebDriver driver)
    {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public ProductInfoPage productItemClick(String xpath)
    {
        driver.findElement(By.xpath(xpath)).click();

		return new ProductInfoPage(driver);
	}

	public MainPage naivageToMainPage()
    {
		mainPageLink.click();
		return new MainPage(driver);
	}

	public ProductCatalog sortPriceClick()
    {
		priceSortLink.click();
		return this;
	}

	public ProductCatalog sortProductNameClick()
    {
		productNameSortLink.click();
		return this;
	}

	public ProductComparisonPage naivageToComaparsionPage()
    {
		comparsionLink.click();
		return new ProductComparisonPage(driver);
	}


	public ProductCatalog setFilter(String FilterXPath)
    {
		driver.findElement(By.xpath(FilterXPath)).click();
		return this;
	}


}