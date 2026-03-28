package com.dataminer.template;

public abstract class DataMiner {

    protected String filePath;


    public DataMiner(String filePath) {
        this.filePath = filePath;
    }

    public abstract void extractData();
    public abstract void parseData();
    public abstract void openFile();
    public abstract void closeFile();
    public final void analyzeData() {
        openFile();
        extractData();
        parseData();
        closeFile();
        sendReport();
    }

    public void sendReport() {
        System.out.println("Report sent to the client.");
    System.out.println(" \n---------------------------------------- \n");
    }
    


}
