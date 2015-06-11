package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


/**
 * Created by Olha_Yeremenko on 09-Jun-15.
 */
public class VerifyWeightRegulationFilterSteps
{
    ProductCatalog catalog;
    MainPage mainPage;
    List<String>  productDescriptionList ;
    String FilterValue;


    public static final String WEIGHT_REGULATION_FILTER= "//div[@class='group'][8]/div[@class='is_empty_items']/a[2]";

    public static final String PRODUCT_DESCRIPTION_LIST = ".//*[@class='item']//div[@class='description']";
    public static final String TITLE = "//*[@id='page-subheader']";


    public VerifyWeightRegulationFilterSteps(WebDriver driver)
    {
        catalog = new ProductCatalog(driver);
        mainPage = new MainPage(driver);
    }


    public VerifyWeightRegulationFilterSteps navigateToBreadmakerCatalog()
    {

        mainPage.navigateTo("http://pn.com.ua/");

        assertThat(mainPage.getDriver().getTitle(), containsString("Прайс навигатор"));

        catalog = mainPage.breadmakerCategoryClick();

        Waiter.waitForElementPresent(mainPage.getDriver(), TITLE);

        assertThat(mainPage.getDriver().getTitle(), containsString("Хлебопечи"));

        return this;
    }


    public VerifyWeightRegulationFilterSteps selectWeightRegulationFilter()
    {

        WebElement WeightRegulationfilter = catalog.getDriver().findElement(
                By.xpath(WEIGHT_REGULATION_FILTER));

        FilterValue=WeightRegulationfilter.getText();

        WeightRegulationfilter.click();

        return this;
    }

    public VerifyWeightRegulationFilterSteps VerifyWeightRegulationFilter()
    {

        List<WebElement> webPoductDescriptionList = catalog.getDriver().findElements(
                By.xpath(PRODUCT_DESCRIPTION_LIST));

        productDescriptionList = new ArrayList<>();

        for (WebElement description : webPoductDescriptionList)
        {
               assertThat(description.getText(),containsString(FilterValue));
        }

        return this;
    }


}
