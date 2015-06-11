package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;
import com.epam.preproduction.pages.ProductInfoPage;
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
 * Created by Olha_Yeremenko on 09-Jun-15.
 */
public class AssertEqualsShortAndFullDescriptionSteps {
    ProductCatalog catalog;
    MainPage mainPage;
    ProductInfoPage infoPage;

    public static final String TITLE = "//*[@id='page-subheader']";

    public static final String PRODUCT_VALUE = "//*[contains(@class,'panel')]/div[@class='row'][position()<5]/span[@class='val']";


    public AssertEqualsShortAndFullDescriptionSteps(WebDriver driver)
    {
        catalog = new ProductCatalog(driver);

        mainPage = new MainPage(driver);
    }


    public AssertEqualsShortAndFullDescriptionSteps navigateToConditionerCatalog()
    {

        mainPage.navigateTo("http://pn.com.ua/");

        assertThat(mainPage.getDriver().getTitle(), containsString("Прайс навигатор"));

        catalog = mainPage.conditionerCategoryClick();

        Waiter.waitForElementPresent(mainPage.getDriver(), TITLE);

        assertThat(mainPage.getDriver().getTitle(), containsString("Кондиционер"));

        return this;
    }


    public AssertEqualsShortAndFullDescriptionSteps assertShortAndFullDescription()
    {
        int i = 1;
        ArrayList<String> shortDescriptionList;

        ArrayList<String> fullDescriptionList;

        fullDescriptionList = new ArrayList<String>();

        List<WebElement> webPoductDescriptionList;

        List<WebElement> webPoductValueList;


        while (i < 6)
        {
            infoPage = catalog.productItemClick("//*[@class='item'][" + i + "]//div[@class='name']/a");


            webPoductValueList = infoPage.getDriver().findElements(
                    By.xpath(PRODUCT_VALUE));

            for (WebElement pr : webPoductValueList)
            {
                fullDescriptionList.add(pr.getText());
            }

            catalog = infoPage.naivageToCatalogPage();


            webPoductDescriptionList = catalog.getDriver().findElements(
                    By.xpath(".//*[@class='item'][" + i + "]//div[@class='description']"));

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





