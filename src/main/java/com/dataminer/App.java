package com.dataminer;


import java.io.File;
import java.util.Scanner;

import com.dataminer.miners.CsvMiner;
import com.dataminer.miners.DocMiner;
import com.dataminer.miners.PdfMiner;
import com.dataminer.template.DataMiner;



public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Data Miner Application");
            int index;
            System.out.println("Selecciona tipo de archivo  1. CSV  2. Doc  3. Pdf  4. Salir");
            index = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            switch (index) {
                case 1 -> {
                    while (true) {
                        System.out.println("Ingresa la ruta del archivo CSV:");
                        String filepath = scanner.nextLine();
                        try {
                            validateFilePath(filepath, ".csv");
                            csvProcess(filepath);
                            break; // Salir del bucle después de procesar el archivo correctamente
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage() + " Intenta de nuevo.");
                        }
                    }
                }
                case 2 -> {
                    while (true) {
                        System.out.println("Ingresa la ruta del archivo Doc:");
                        String filepath = scanner.nextLine();
                        try {
                            validateFilePath(filepath, ".docx");
                            docProcess(filepath);
                            break; // Salir del bucle después de procesar el archivo correctamente
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage() + " Intenta de nuevo.");
                        }
                    }
                }
                case 3 -> {
                    while (true) {
                        System.out.println("Ingresa la ruta del archivo Pdf:");
                        String filepath = scanner.nextLine();
                        try {
                            validateFilePath(filepath, ".pdf");
                            pdfProcess(filepath);
                            break; // Salir del bucle después de procesar el archivo correctamente
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage() + " Intenta de nuevo.");
                        }
                    }
                }
                case 4 -> {
                    try (scanner) {
                        System.out.println("Saliendo de la aplicación.");
                    }
                    return;
                }

                default ->
                    System.out.println("Opción no válida");
            }
        }

    }

    public static void csvProcess(String filepath) {
        DataMiner miner = new CsvMiner(filepath);
        miner.analyzeData();

    }

    public static void docProcess(String filepath) {
        DataMiner miner = new DocMiner(filepath);
        miner.analyzeData();

    }

    public static void pdfProcess(String filepath){
        DataMiner miner = new PdfMiner(filepath);
        miner.analyzeData();
    }

    public static void validateFilePath(String filepath, String expectedExtension) {
        File file = new File(filepath);
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("La ruta no es válida: " + filepath);        
        }
        if (!file.getName().toLowerCase().endsWith(expectedExtension.toLowerCase())) {
            throw new IllegalArgumentException("El archivo no tiene la extensión esperada: " + expectedExtension);
        }
    }
}
