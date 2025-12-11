package revision.ioStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReaderWriterExample {

    public static void main(String[] args) throws IOException {

        File inputFile = new File("src/main/java/revision/ioStream/files/someFileInput.txt");
        File outputFile = new File("src/main/java/revision/ioStream/files/someFileOutput.txt");

        List<String> fileRead = readFile(inputFile);
        fileRead.forEach(System.out::println);


        writeFile(outputFile, fileRead);
    }

    private static void writeFile(File outputFile, List<String> fileRead) throws IOException {
        // append dodaje zawartosc do istniejącego pliku
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8, true))) {
            for (String line : fileRead) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static List<String> readFile(final File inputFile) throws IOException {
        List<String> result = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))
        ) {
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        }
        return result;
    }
}
