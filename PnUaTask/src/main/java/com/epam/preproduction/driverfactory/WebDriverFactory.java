package com.epam.preproduction.driverfactory;

import com.epam.preproduction.helpers.PropertyFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory extends AbstractDriverFactory {

    public PropertyFactory propertyFactory = new PropertyFactory();

	@Override
	public WebDriver getDriver(String parameter)
    {
		switch (parameter)
        {
		case "firefox":
			return new FirefoxDriver();
		case "ie":
			System.setProperty("webdriver.ie.driver",
                    propertyFactory.getProperty("ie.driver"));
			return new InternetExplorerDriver();
		case "opera":
			System.setProperty("webdriver.opera.driver",
                    propertyFactory.getProperty("opera.driver"));
			return new OperaDriver();
		case "chrome":
                     System.setProperty("webdriver.chrome.driver",
                    propertyFactory.getProperty("chrome.driver"));
			return new ChromeDriver();
		default:
			throw new IllegalArgumentException("This browser is undefined!");
		}
	}

}
