package com.epam.preproduction.pages;

import com.epam.preproduction.template.AbstractPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class ProductComparisonPage extends AbstractPage
{

    private static final String MAIN_PAGE_LINK = " .//*[@id='page-breadcrumbs']/a[1]";

	@FindBy(xpath = MAIN_PAGE_LINK)
	private WebElement mainPageLink;

	public ProductComparisonPage(WebDriver driver)
    {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public MainPage naivageToMainPage()
    {
		mainPageLink.click();
		return new MainPage(driver);
	}

	public ProductInfoPage navigateToProduct(String xPathProduct)
    {
		driver.findElement(By.xpath(xPathProduct)).click();
		return new ProductInfoPage(driver);
	}

}
