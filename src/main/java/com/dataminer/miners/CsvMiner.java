package com.dataminer.miners;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dataminer.template.DataMiner;

public class CsvMiner extends DataMiner{

    private BufferedReader reader;
    private final List<String> lines = new ArrayList<>();

    public CsvMiner(String filePath) {
        super(filePath);
    }


    // Implementación de extractData para CSV
    @Override
    public void extractData() {
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    // Implementación de parseData para CSV
    @Override
    public void parseData() {
        System.out.println("Parsing data from CSV file...");
        for(int i = 0; i < lines.size(); i++){
            String values[] = lines.get(i).split(",");
            if(i == 0){
                System.out.println("Headers: " + String.join(" | ", values));
            }else{
                System.out.println("Row " + i + ": " + String.join(" | ", values));
            }
        }
    }

    // Implementación de openFile y closeFile para CSV
    @Override
    public void openFile() {
        System.out.println("Opening CSV file...");
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
        
    }

    // Implementación de closeFile para CSV
    @Override
    public void closeFile() {
        System.out.println("Closing CSV file...");
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing file: " + e.getMessage());
        }
    }

    public List<String> getLines() {
        return lines;
    }

}
