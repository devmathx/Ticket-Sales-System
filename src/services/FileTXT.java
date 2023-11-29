package services;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileTXT {
    /**
     * Diretório do arquivo
     */
    private final String TXT_PATH;

    /**
     * Construtor da classe
     * Cria um novo arquivo .txt
     * @param filename Nome do arquivo
     */ 
    public FileTXT(String filename) { 
        this.TXT_PATH = this.newFile(filename);
    }
    
    /**
     * Cria um novo arquivo .txt
     * @param filename Nome do arquivo
     * @return Caminho relativo ao arquivo
     */
    private String newFile(String filename) {
        File root = new File(System.getProperty("user.dir"));
        File path = new File(root, "database");

        if (!path.exists()) {
            path.mkdir();
        }

        String name = filename + ".txt";
        File txt = new File(path, name);

        if (!txt.exists()) {
            try {
                txt.createNewFile();
            } catch (IOException error) {
                error.printStackTrace();
            }
        }

        return txt.getAbsolutePath();
    }

    /**
     * Escreve no arquivo .txt
     * @param row Linha para ser adicionada
     * @param overwrite Define se é para sobrescrever o conteúdo anterior
     */
    public void write(String row, boolean overwrite) {
        try {
            FileWriter writer = new FileWriter(this.TXT_PATH, !overwrite);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
    
            bufferWriter.write(row);
            bufferWriter.newLine();
    
            bufferWriter.close();
            writer.close();
        
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Escreve no arquivo .txt
     * @param row Linha para ser adicionada
     */
    public void write(String row) {
        this.write(row, false);
    }
    
    /**
     * Lê o arquivo e retorna as linhas
     * @return Um ArrayList com as linhas do .txt
     */
    public ArrayList<String> read() {
        ArrayList<String> rows = new ArrayList<>();

        try {
            FileReader reader = new FileReader(this.TXT_PATH);
            BufferedReader bufferReader = new BufferedReader(reader);

            String row;
            while ((row = bufferReader.readLine()) != null) {
                rows.add(row);
            }
            
            bufferReader.close();
            reader.close();
            
        } catch (IOException error) {
            error.printStackTrace();
        }
        return rows;
    }

    /**
     * Remove uma linha do arquivo
     * @param rowToRemove Linha para ser removida
     */
    public void remove(String rowToRemove) {
        ArrayList<String> data = read();
            
        StringBuilder content = new StringBuilder();

        for (String row : data) {
            if (row != null) {
                if (!row.equals(rowToRemove)) {
                    content.append(row).append("\n");
                } 
            }   
        }

        this.write(content.toString(), true);
    }

    /**
     * Exclui o arquivo
     * @return Status de sucesso
     */
    public boolean delete() {
        File path = new File(this.TXT_PATH);

        final boolean deleted = path.delete();

        return deleted;
    }

    /**
     * Busca por um trecho de texto
     * @param text Trecho de texto para buscar
     * @return A linha em que o texto se encontra
     */
    public String find(String text) {
        ArrayList<String> data = this.read();
        
        for (String row : data) {
            if (row.contains(text)) {
                return row;
            }
        }
        
        return null;
    }

    /**
     * Remove as linhas vazias do arquivo
     */
    public void removeEmptyLines() {
        ArrayList<String> lines = new ArrayList<>();

        lines = this.read();

        lines.removeIf(String::isEmpty);
        
        for (String line : lines) {
            this.write(line + System.lineSeparator());
        }
    }
}
