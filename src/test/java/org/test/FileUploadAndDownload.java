package org.test;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadAndDownload {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/download");
        List<WebElement> allLinks = driver.findElements(By.xpath("//a[not (contains(text(),'.png')) and (contains(text(),'.'))]"));
        WebElement downloadLink = allLinks.get(0);
        
        String linkText = downloadLink.getText();
        String[] split = linkText.split(" ");
        
        downloadLink.click();

        
        Thread.sleep(10000);
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement uploadInput = driver.findElement(By.id("file-upload")); 
        String userHome = System.getProperty("user.home");
        String downloadsFolder = userHome + File.separator + "Downloads";
        String filePath = downloadsFolder+"\\"+split[0]; 
        uploadInput.sendKeys(filePath);

        WebElement uploadButton = driver.findElement(By.id("file-submit")); 
        uploadButton.click();

        Thread.sleep(5000);
        driver.quit();
    }
}
