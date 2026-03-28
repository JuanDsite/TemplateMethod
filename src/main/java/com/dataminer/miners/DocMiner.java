package com.dataminer.miners;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.dataminer.template.DataMiner;

public class DocMiner extends DataMiner {

    private FileInputStream fileInputStream;
    private XWPFDocument document;
    private String[] paragraphs;

    public DocMiner(String filePath) {
        super(filePath);
    }
    @Override
    public void extractData() {
        System.out.println("Extracting DOC data...");
        try (XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            String text = extractor.getText();
            paragraphs = text.split("\\r?\\n");
            System.out.println("DOC data extracted successfully.");
        } catch (IOException e) {
            System.out.println("Error extracting DOC data: " + e.getMessage());
        }
    }

    @Override
    public void parseData() {
        System.out.println("Parsing DOC data...");
        for (int i = 0; i < paragraphs.length; i++) {
            System.out.println("Paragraph " + (i + 1) + ": " + paragraphs[i]);
        }

    }

    @Override
    public void openFile() {
        try {
            fileInputStream = new FileInputStream(filePath);
            document = new XWPFDocument(fileInputStream);
            System.out.println("DOC file opened successfully.");
        } catch (IOException e) {
            System.out.println("Error opening DOC file: " + e.getMessage());
        }
    }

    @Override
    public void closeFile() {
        System.out.println("Closing DOC file...");
        try {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (document != null) {
                document.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing DOC file: " + e.getMessage());
        }
    }

}
