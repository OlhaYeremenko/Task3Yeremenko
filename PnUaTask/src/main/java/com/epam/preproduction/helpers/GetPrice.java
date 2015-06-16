package com.epam.preproduction.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Olha_Yeremenko on 15-Jun-15.
 */
public class GetPrice {

    public static int getPrice(WebDriver driver, String xPath) {
        return Integer.parseInt(driver
                .findElement(By.xpath(xPath)).getText()
                .replace(" ", "").replace("грн", ""));
    }
}
