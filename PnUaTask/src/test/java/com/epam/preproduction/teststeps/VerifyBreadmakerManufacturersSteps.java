package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Olha_Yeremenko on 09-Jun-15.
 */
public class VerifyBreadmakerManufacturersSteps
{
    ProductCatalog catalog;
    MainPage mainPage;
    HashSet<String> productManufacturerList;
    HashSet<String> productNameList ;


    public static final String MANUFACTURER_FILTER_BINATONE = "//div[@class='group'][3]/div[@class='is_empty_items']/a[2]";
    public static final String MANUFACTURER_FILTER_LG = "//div[@class='group'][3]/div[@class='is_empty_items']/a[9]";
    public static final String MANUFACTURER_FILTER_SCARLER = "//div[@class='group'][3]/div[@class='is_empty_items']/a[16]";
    public static final String PRODUCT_NAME_LIST = ".//*[@class='item']/*[@class='name']/a";
    public static final String TITLE = "//*[@id='page-subheader']";


    public VerifyBreadmakerManufacturersSteps(WebDriver driver)
    {
        catalog = new ProductCatalog(driver);
        mainPage = new MainPage(driver);
    }


    public VerifyBreadmakerManufacturersSteps navigateToBreadmakerCatalog()
    {

        mainPage.navigateTo("http://pn.com.ua/");

        assertThat(mainPage.getDriver().getTitle(), containsString("Прайс навигатор"));

        catalog = mainPage.breadmakerCategoryClick();

        Waiter.waitForElementPresent(mainPage.getDriver(), TITLE);

        assertThat(mainPage.getDriver().getTitle(), containsString("Хлебопечи"));

        return this;
    }


    public VerifyBreadmakerManufacturersSteps selectManufacturerFilters()
    {
        productManufacturerList = new HashSet<>();

        WebElement filterBimatone = catalog.getDriver().findElement(
                By.xpath(MANUFACTURER_FILTER_BINATONE));

        productManufacturerList.add(filterBimatone.getText());

        filterBimatone.click();


        WebElement filterLg = catalog.getDriver().findElement(
                By.xpath(MANUFACTURER_FILTER_LG));

        productManufacturerList.add(filterLg.getText());

        filterLg.click();

        WebElement filterScarler = catalog.getDriver().findElement(
                By.xpath(MANUFACTURER_FILTER_SCARLER));

        productManufacturerList.add(filterScarler.getText());

        filterScarler.click();

        return this;
    }

    public VerifyBreadmakerManufacturersSteps verifyThatSelectedManufacturerPresent()
    {

        List<WebElement> webPoductNameList = catalog.getDriver().findElements(
                By.xpath(PRODUCT_NAME_LIST));

        productNameList = new HashSet<>();

        for (WebElement element : webPoductNameList)
        {
           productNameList.add((element.getText().split("\\s+"))[0]);
        }

       assertThat(productManufacturerList, is(productNameList));

        return this;
    }


}
