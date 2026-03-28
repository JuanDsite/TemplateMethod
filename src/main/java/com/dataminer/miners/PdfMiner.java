package com.dataminer.miners;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.dataminer.template.DataMiner;

public class PdfMiner extends DataMiner {

    private PDDocument document;
    private PDFTextStripper pdfStripper;
    private String[] lines;

    public PdfMiner(String filePath) {
        super(filePath);
    }

    @Override
    public void extractData() {
        try {
            String text = pdfStripper.getText(document);
            lines = text.split("\\r?\\n");
            System.out.println("Extracted " + lines.length + " lines from PDF.");
        } catch (IOException e) {
            System.err.println("Error extracting data from PDF: " + e.getMessage());
        }
    }

    @Override
    public void parseData() {
        if (lines != null) {
            System.out.println("Parsing extracted lines from PDF...");
            for (String line : lines) {
                System.out.println("Parsed line: " + line);
            }
        } else {
            System.out.println("No lines to parse from PDF.");
        }
    }

    @Override
    public void openFile() {
        try {
            document = PDDocument.load(new java.io.File(filePath));
            pdfStripper = new PDFTextStripper();
            System.out.println("PDF file opened successfully.");
        } catch (IOException e) {
            System.err.println("Error opening PDF file: " + e.getMessage());
        }
    }

    @Override
    public void closeFile() {
        try {
            if (document != null) {
                document.close();
            }
            System.out.println("PDF file closed successfully.");
        } catch (IOException e) {
            System.err.println("Error closing PDF file: " + e.getMessage());
        }
    }

}

