package revision.ioStream;

import java.io.*;
import java.util.Arrays;

public class InOutStreamExample {

    public static void main(String[] args) throws IOException {

        File inputFile = new File("src/main/java/revision/ioStream/files/someFileInput.txt");
        File outputFile = new File("src/main/java/revision/ioStream/files/someFileOutput.txt");

        System.out.println(inputFile.getAbsoluteFile());
//        justCopyNoBuffer(inputFile, outputFile);
        //copyAndModify(inputFile, outputFile);
        justCopyWithBuffer(inputFile, outputFile);

//        File inputFile2 = new File("src/main/java/revision/ioStream/files/kotlin.png");
//        File outputFile2 = new File("src/main/java/revision/ioStream/files/kotlin2.png");
//        justCopyNoBuffer(inputFile2, outputFile2);
    }

    private static void justCopyWithBuffer(File inputFile, File outputFile) throws IOException {
        try (
                InputStream input = new BufferedInputStream(new FileInputStream(inputFile));
                OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFile))
        ) {
            byte[] buffer = new byte[255];
            int length = input.read(buffer);
            // byte[] buffer2 = input.readAllBytes(); - czyta wszystko
            System.out.printf("Start buffering reading file: [%s]%n", inputFile);
            System.out.printf("Reading buffered values: [%s], chars: [%s], length: [%s]%n",
                    byteArrToString(buffer),
                    toCharString(buffer),
                    length
            );
            while (length > 0) {
                System.out.printf("Writing buffered values: [%s], chars: [%s]%n",
                        byteArrToString(buffer),
                        toCharString(buffer)
                );
                // Zapisze dobrze
                output.write(buffer, 0, length);
                // Poniżej zapisze źle bo pominie ostatnią niepełną tablice
                //output.write(buffer);
                output.flush();

                length = input.read(buffer);
                System.out.printf("Reading values: [%s], chars: [%s], length: [%s]%n",
                        byteArrToString(buffer),
                        toCharString(buffer),
                        length
                );
            }
        }
    }

    static String toCharString(byte[] input) {
        char[] charArray = new char[input.length];
        for (int i = 0; i < input.length; i++) {
            charArray[i] = (char) input[i];
        }
        return replaceNewLines(Arrays.toString(charArray));
    }

    static String byteArrToString(byte[] input) {
        return replaceNewLines(Arrays.toString(input));
    }

    static String replaceNewLines(String toString) {
        return toString.replace("\n", "\\n").replace("\r", "\\r");
    }

    private static void copyAndModify(File inputFile, File outputFile) throws IOException {
        try (
                InputStream input = new FileInputStream(inputFile);
                OutputStream output = new FileOutputStream(outputFile)
        ) {
            int value = input.read();
            System.out.printf("Start reading file: [%s]%n", inputFile);
            System.out.printf("Reading value: [%s], char: [%s]%n", value, (char) value);
            while (value != -1) {
                int modified = value;
                if(modified > 65) {
                    modified++;
                }
                System.out.printf("Writing valueMod: [%s], charMod: [%s]%n", modified, (char) modified);
                output.write(modified);
                value = input.read();
                System.out.printf("Reading valueMod: [%s], charMod: [%s]%n", value, (char)value);
            }
        }
    }

    private static void justCopyNoBuffer(File inputFile, File outputFile) throws IOException {
        try (
                InputStream input = new FileInputStream(inputFile);
                OutputStream output = new FileOutputStream(outputFile)
        ) {
            int value = input.read();
            System.out.printf("Start reading file: [%s]%n", inputFile);
            System.out.printf("Reading value: [%s], char: [%s]%n", value, (char) value);
            // -1 oznacza koniec pliku
            while (value != -1) {
                System.out.printf("Writing value: [%s], char: [%s]%n", value, (char) value);
                output.write(value);
                value = input.read();
                System.out.printf("Reading value: [%s], char: [%s]%n", value, (char)value);
            }

            // signed bytes - zmienne reprezentowane ze znakiem (+, -)
            // unsigned bytes - zmienne reprezentowane bez znaku (0, 255)
            // reader odczytuje singed bytes i zamienia na unsignet bytes
        }
    }
}
