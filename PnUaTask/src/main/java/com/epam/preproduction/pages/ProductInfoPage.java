package com.epam.preproduction.pages;

import java.util.ArrayList;
import java.util.List;

import com.epam.preproduction.template.AbstractPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class ProductInfoPage extends AbstractPage
{

    public static final String PARAMETERS_LIST = "//*[@class='val']";
    public static final String LINK_TO_COMPARSION = "//*[@class='head-compare-link']";
    public static final String LINK_TO_CATALOG = "//*[@id='page-breadcrumbs']/a[3]";
    public List<String> parametersList;

    @FindBy(xpath = PARAMETERS_LIST)
    private List<WebElement> parametersWebList;

    @FindBy(xpath = LINK_TO_COMPARSION)
    private WebElement comparsionLink;

    @FindBy(xpath = LINK_TO_CATALOG)
    private WebElement catalogLink;

    public ProductInfoPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getProductParameters()
    {
        parametersList = new ArrayList<String>();
        for (WebElement element : parametersWebList)
        {
            parametersList.add(element.getText());
        }
        return parametersList;
    }

    public ProductComparisonPage naivageToComaparsionPage()
    {
        comparsionLink.click();
        return new ProductComparisonPage(driver);
    }

    public ProductCatalog naivageToCatalogPage()
    {
        catalogLink.click();
        return new ProductCatalog(driver);
    }

}
