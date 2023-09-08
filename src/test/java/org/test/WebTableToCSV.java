package org.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebTableToCSV {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        WebElement table = driver.findElement(By.xpath("//h3[text()='Challenging DOM']/following::table"));

        List<List<String>> tableData = new ArrayList<>();

        WebElement headerRow = table.findElement(By.tagName("thead")).findElement(By.tagName("tr"));
        List<WebElement> headerColumns = headerRow.findElements(By.tagName("th"));
        List<String> headerData = new ArrayList<>();
        for (WebElement headerColumn : headerColumns) {
            headerData.add(headerColumn.getText());
        }
        tableData.add(headerData);

        List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
        for (WebElement row : rows) {
            List<String> rowData = new ArrayList<>();
            List<WebElement> columns = row.findElements(By.tagName("td"));
            for (WebElement column : columns) {
                rowData.add(column.getText());
            }
            tableData.add(rowData);
        }

        String timeStamp = new SimpleDateFormat("MM-dd-yy-HH-mm-ss").format(new Date());
        String fileName = "webtable_" + timeStamp + ".csv";
        String setProperty = System.setProperty("user.dir","");
        String outputFolder = setProperty+"\\HOME\\briq1\\";
        String filePath = outputFolder + fileName;

        try (FileWriter csvWriter = new FileWriter(filePath)) {
            for (List<String> rowData : tableData) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV file generated successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while generating CSV file.");
        }

        driver.quit();
    }
}
