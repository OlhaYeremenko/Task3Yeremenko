package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.pages.ProductInfoPage;
import com.epam.preproduction.testdata.Global;
import com.epam.preproduction.testdata.ProductCategories;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class AssertEqualsShortAndFullDescriptionSteps {
    private ProductCatalog catalog;
    private MainPage mainPage;
    private  ProductInfoPage infoPage;

    private static final String TITLE = "//*[@id='page-subheader']";
    private static final String PRODUCT_VALUE = "//*[contains(@class,'panel')]/div[@class='row'][position()<5]/span[@class='val']";


    public AssertEqualsShortAndFullDescriptionSteps(WebDriver driver)
    {
        catalog = new ProductCatalog(driver);
        mainPage = new MainPage(driver);
    }


    public AssertEqualsShortAndFullDescriptionSteps navigateToConditionerCatalog()
    {
        Global globalSetting = new Global();
        mainPage.navigateTo(globalSetting.SITE_ADRESS);
        assertThat(mainPage.getDriver().getTitle(), containsString(Global.MAIN_TITLE));
        catalog = mainPage.conditionerCategoryClick();
        Waiter.waitForElementPresent(mainPage.getDriver(), TITLE);
        assertThat(mainPage.getDriver().getTitle(), containsString(ProductCategories.Кондиционер.toString()));
        return this;
    }


    public AssertEqualsShortAndFullDescriptionSteps assertShortAndFullDescription()
    {
        int i = 1;
        Global globalSetting = new Global();
        ArrayList<String> shortDescriptionList;

        ArrayList<String> fullDescriptionList;

        fullDescriptionList = new ArrayList<String>();

        List<WebElement> webPoductDescriptionList;

        List<WebElement> webPoductValueList;


        while (i <=globalSetting.PRODUCT_COUNT)
        {
            infoPage = catalog.productItemClick("//*[@class='item'][" + i + "]//div[@class='name']/a");//нужно переделывать логику теста

            webPoductValueList = infoPage.getDriver().findElements(
                    By.xpath(PRODUCT_VALUE));

            for (WebElement pr : webPoductValueList)
            {
                fullDescriptionList.add(pr.getText());
            }
            catalog = infoPage.naivageToCatalogPage();

            webPoductDescriptionList = catalog.getDriver().findElements(
                    By.xpath("//div[@class='item'][" + i + "]//div[@class='description']"));

            shortDescriptionList = new ArrayList<String>();

            for (WebElement webCharacteristic : webPoductDescriptionList)
            {
                shortDescriptionList.addAll(Arrays.asList(webCharacteristic.getText().split(";")));
            }

            for (int j = 0; j < fullDescriptionList.size(); j++)
            {
                assertThat(shortDescriptionList.get(j), containsString(fullDescriptionList.get(j)));
            }
            i++;
            shortDescriptionList.clear();
            fullDescriptionList.clear();
        }

        return this;
    }


}





