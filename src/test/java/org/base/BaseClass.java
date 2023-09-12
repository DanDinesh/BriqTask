package org.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	
	public static void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	public static void urlLaunch(String url) {
		driver.get(url);
	}
	
	public static void impliCitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	public static void browserMax() {
		driver.manage().window().maximize();

	}
	
	
}
