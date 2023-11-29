package services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileCSV {
    private final String CSV_PATH;


    /**
     * Construtor da classe
     * @filename nome do arquivo
     */
    public FileCSV(String filename) {
        this.CSV_PATH = this.newFile(filename);
    }


    /**
     * Criando novo aruivo
     * @filename nome do arquivo
     */
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



    /**
     * Escreve no arquivo, removendo o que ja foi escrito
     * @filename nome do arquivo
     */
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
}