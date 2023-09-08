package org.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SFGovDataExporter {
    public static void main(String[] args) {
        try {
            // Step 1: Fetch data from the URL
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("https://data.sfgov.org/resource/p4e4-a5a7.json");
            HttpResponse response = httpClient.execute(request);

            // Step 2: Parse JSON data
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(response.getEntity().getContent());

            // Step 3: Process and export as CSV
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String timestamp = new SimpleDateFormat("MM-dd-yy-HH-mm-ss").format(new Date());
            String jsonFileName = "sfgov_" + timestamp + ".json";
            String csvFileName = "sfgov_" + timestamp + ".csv";
            String setProperty = System.setProperty("user.dir","");
            String outputFolder = setProperty+"\\HOME\\briq2\\";

            File jsonFile = new File(outputFolder + jsonFileName);
            File csvFile = new File(outputFolder + csvFileName);

            try (FileWriter jsonWriter = new FileWriter(jsonFile);
                 FileWriter csvWriter = new FileWriter(csvFile);
                 CSVPrinter csvPrinter = new CSVPrinter(csvWriter, CSVFormat.DEFAULT
                         .withHeader("field1", "field2", "is_roof"))) {

                for (JsonNode node : jsonData) {
                    JsonNode field1Node = node.get("field1");
                    JsonNode field2Node = node.get("field2");
                    String field1 = (field1Node != null) ? field1Node.asText() : "";
                    String field2 = (field2Node != null) ? field2Node.asText() : "";
                    boolean isRoof = field1.toLowerCase().contains("roof")
                            || field2.toLowerCase().contains("roof");

                    jsonWriter.write(objectMapper.writeValueAsString(node));
                    jsonWriter.write(System.lineSeparator());

                    csvPrinter.printRecord(field1, field2, isRoof);
                }

                System.out.println("Data exported to: " + outputFolder + csvFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

