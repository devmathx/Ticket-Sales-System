package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileCSV {
    private final String CSV_PATH;

    public FileCSV(String filename) {
        this.CSV_PATH = this.newFile(filename);
    }

    private String newFile(String filename) {
        if (filename == null) {
            return null;
        }

        File root = new File(System.getProperty("user.dir"));
        File path = new File(root, "reports");

        if (!path.exists()) {
            path.mkdir();
        }

        String name = filename + ".csv";
        File csv = new File(path, name);

        if (!csv.exists()) {
            try {
                csv.createNewFile();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }

        return csv.getAbsolutePath();
    }

    public void write(String data, boolean overwrite) {
        try {
            FileWriter writer = new FileWriter(this.CSV_PATH, false);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            
            bufferWriter.write(data);
            bufferWriter.newLine();
            
            bufferWriter.close();
            writer.close();
            
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public List<String[]> read() {
        List<String[]> rows = new ArrayList<>();

        try {
            FileReader reader = new FileReader(this.CSV_PATH);
            BufferedReader bufferReader = new BufferedReader(reader);

            String row;
            while ((row = bufferReader.readLine()) != null) {
                rows.add(row.split(","));
            }

            bufferReader.close();
            reader.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
        return rows;
    }

   

    private boolean areArraysEqual(String[] arr1, String[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }

        return true;
    }
}