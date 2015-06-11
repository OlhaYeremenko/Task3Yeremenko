package com.epam.preproduction.pages;

import com.epam.preproduction.template.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Olha_Yeremenko on 05-Jun-15.
 */
public class MainPage extends AbstractPage
{

    public static final String FRIDGE_LINK = "//*[@id='page-content-wrap']//a[27]";
    public static final String BREADMAKER_LINK = ".//*[@id='page-content-wrap']//a[33]";
    public static final String CONDITIONER_LINK = ".//*[@id='page-content-wrap']//a[25]";
    public static final String MICROWAVE_LINK = ".//*[@id='page-content-wrap']//a[30]";

    @FindBy(xpath = FRIDGE_LINK)
    private WebElement fridgeLink;

    @FindBy(xpath = MICROWAVE_LINK)
    private WebElement microwaveLink;


    @FindBy(xpath = BREADMAKER_LINK)
    private WebElement breadmakerLink;

    @FindBy(xpath = CONDITIONER_LINK)
    private WebElement conditionerLink;


    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductCatalog fridgeCategoryClick()
    {
        fridgeLink.click();
        return new ProductCatalog(driver);
    }

    public ProductCatalog microwaveCategoryClick()
    {
        microwaveLink.click();
        return new ProductCatalog(driver);
    }

    public ProductCatalog breadmakerCategoryClick()
    {

        breadmakerLink.click();
        return new ProductCatalog(driver);
    }

    public ProductCatalog conditionerCategoryClick()
    {
        conditionerLink.click();
        return new ProductCatalog(driver);
    }


}
