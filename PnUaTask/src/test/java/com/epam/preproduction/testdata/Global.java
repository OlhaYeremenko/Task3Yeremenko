package com.epam.preproduction.testdata;

import com.epam.preproduction.helpers.PropertyFactory;

/**
 * Created by Olha_Yeremenko on 15-Jun-15.
 */
public class Global {
    protected PropertyFactory propertyFactory = new PropertyFactory();

    public static final String COMPARE_TITLE = " сравнение";
    public static final String MICROWAVE_TITLE = "Микроволновки";
    public static final String MAIN_TITLE = "Прайс навигатор";
    public  String SITE_ADRESS = propertyFactory.getProperty("test.environment");
    public int PRODUCT_COUNT = Integer.parseInt(propertyFactory.getProperty("productCount"));
}