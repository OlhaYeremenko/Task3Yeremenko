package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.testdata.Global;
import com.epam.preproduction.testdata.ProductCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class VerifyBreadmakerManufacturersSteps
{
    private  ProductCatalog catalog;
    private  MainPage mainPage;
    private   HashSet<String> productManufacturerList;
    private   HashSet<String> productNameList ;



    private static final String MANUFACTURER_FILTER_BINATONE = "//div[@class='group'][3]/div[@class='is_empty_items']/a[2]";
    private static final String MANUFACTURER_FILTER_LG = "//div[@class='group'][3]/div[@class='is_empty_items']/a[9]";
    private static final String MANUFACTURER_FILTER_SCARLER = "//div[@class='group'][3]/div[@class='is_empty_items']/a[16]";
    private static final String PRODUCT_NAME_LIST = "//div[@class='item']/*[@class='name']/a";
    private static final String TITLE = "//div[@id='page-subheader']";


    public VerifyBreadmakerManufacturersSteps(WebDriver driver)
    {
        catalog = new ProductCatalog(driver);
        mainPage = new MainPage(driver);
    }


    public VerifyBreadmakerManufacturersSteps navigateToBreadmakerCatalog()
    {
        Global globalSetting = new Global();
        mainPage.navigateTo(globalSetting.SITE_ADRESS);
        assertThat(mainPage.getDriver().getTitle(), containsString(Global.MAIN_TITLE));
        catalog = mainPage.breadmakerCategoryClick();
        Waiter.waitForElementPresent(mainPage.getDriver(), TITLE);
        assertThat(mainPage.getDriver().getTitle(), containsString(ProductCategories.Хлебопечи.toString()));
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
