package com.epam.preproduction.teststeps;

import com.epam.preproduction.helpers.Waiter;
import com.epam.preproduction.pages.MainPage;
import com.epam.preproduction.pages.ProductCatalog;

import com.epam.preproduction.testdata.Global;
import com.epam.preproduction.testdata.ProductCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.assertTrue;

/**
 * @author Olha_Yeremenko
 * @since 05-Jun-15
 */
public class VerifySortingByNameAndPriceSteps {
    private  MainPage mainPage;
    private  ProductCatalog catalog;


    private static final String ITEMS_PRICE_LIST = "//div[@class='catalog']/div[@class='item']/div[@class='price']/strong";
    private static final String ITEMS_NAME_LIST = "//div[@class='catalog']/div[@class='item']/div[@class='name']/strong";
    private static final String TITLE = "//*[@id='page-subheader']";
    private static final String MAIN_SEARCH_FILED = "//*[@id='edit-name-1'] ";


    public VerifySortingByNameAndPriceSteps(WebDriver driver)
    {
        mainPage = new MainPage(driver);
    }

    public VerifySortingByNameAndPriceSteps navigateToFridgeCatalog()
    {
        Global globalSetting = new Global();
        mainPage.navigateTo(globalSetting.SITE_ADRESS);
        assertThat(mainPage.getDriver().getTitle(), containsString(Global.MAIN_TITLE));
        catalog = mainPage.fridgeCategoryClick();
        Waiter.waitForElementPresent(mainPage.getDriver(),TITLE);
        assertThat(mainPage.getDriver().getTitle(), containsString(ProductCategories.Холодильни.toString()));
        
        return this;
    }

    public VerifySortingByNameAndPriceSteps sortFridgeByPrice()
    {
        catalog.sortPriceClick();
        List<Integer> fridgePriceList = new ArrayList<>();
        List<Integer> unsortedList = new ArrayList<>();
        List<WebElement> webElementList = catalog.getDriver()
                .findElements(By.xpath(ITEMS_PRICE_LIST));
           for (WebElement fridgePrice : webElementList)
           {
            fridgePriceList.add(Integer.parseInt(fridgePrice.getText().replace(" ", "").replace("грн", "")));
           }
        unsortedList.addAll(fridgePriceList);
        Collections.sort(fridgePriceList);
       assertTrue(unsortedList.equals(fridgePriceList));

        return this;
    }

    public VerifySortingByNameAndPriceSteps sortFridgeByName()
    {

        catalog.sortProductNameClick();
        List<String> fridgeNameList = new ArrayList<>();
        List<Object> unsortedList = new ArrayList<>();
        List<WebElement> webElementList = catalog.getDriver()
                .findElements(By.xpath(ITEMS_NAME_LIST));

        for (WebElement fridgeName : webElementList)
        {
            fridgeNameList.add(fridgeName.getText());
        }
        unsortedList.addAll(fridgeNameList);
        Collections.sort(fridgeNameList);
        assertTrue(unsortedList.equals(fridgeNameList));

        return this;
    }
    public VerifySortingByNameAndPriceSteps backToMain()
    {
        catalog.naivageToMainPage();
        assertThat(mainPage.getDriver().getTitle(), containsString(Global.MAIN_TITLE));
        Waiter.waitForElementPresent(mainPage.getDriver(), MAIN_SEARCH_FILED);
        return this;
    }
}
