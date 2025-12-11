package revision.ioStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class IOStreamExample {

    public static void main(String[] args) {

        /*
            Stream IO - struktura danych rozumiana jako przepływ informacji
            1. Stream - senkwencyjnie operuje na danych
            2. BufferedStream - przetwarza wiele elmentów na raz (wymiana danych)

            Opercaje:
            1. Reader / Writer -operacje na znakach
            2. InputStream / OutputStream - operacje na byte'ch
         */

        // 1. Kodowanie znaków
        charsetCoding();

        // 2. Tablica UNICODE - JAVA z niej korzysta gdyż jest dłuższa niż ASCI
        unicode();

        // 3. Stream
        streamSample();

        // Reader - klasa abstrakcyjna do dziedziczenia przez inne klasy
        // FileReader - klasa któóa czyta pojedyńcze znaki z pliku | Niski poziom
        // InputStreamReader - klasa czytjąca znaki z przekazanego InputStream | Wysoki poziom
        // BufferedReader - klasa czytająca, grupująca | Wysoki poziom
        // Writer - klasa abstrakcyjna do dzieczyenia przez inne klasy
        // FileWriter - stream do zapisywania danych w formie znakowej | Niski poziom
        // OutputStreamWriter - klasa zapisująca znaki do podanego OutputStream | Wysoki poziom
        // BufferedWriter - stream zapisujacy, grupujacy | Wysoki poziom
        // PrintWriter - stram ułatwiający formatowanie treści | Wysoki poziom
        // InputStream - klasa abstrakcyjna z której dziewcziczą inne InputStream'y
        // FileInputStream - klasa odczytująca informacje z pliku z postaci bajtów | Niski poziom
        // BufferedInputStream - klasa buforująca odczyt bajtów | Wysoki poziom
        // ObjectInputStream - klsa któ®a może odczytać obiekt java | Wysoki poziom
        // OutputStream - klasa abstrakcyjna, z  której dziecziczą inne output stream
        // FileOutputStream - klasa zapisująca pojedyńcze bajty | Niski poziom
        // BufferedOutputStream - klasa grupująca output stream, poprawia wydajność
        // ObjectOutputStream - klasa pozwalająca na zapis do outpu stream objektów java | Wysoki poziom
        // PrintStream - stream pozwalający na łatwiejsze formatowanie treści | Wysoki poziom


        // Metoda .flush() - służy do sprawdzenia czy dane do pliku sie zapisały
        // Metoda .skip() - pomija dane ze streama


    }

    private static void streamSample() {
        try {
            try(ObjectInputStream objectInputStream = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream("someFile.txt")
                    )
            )) {

            }
        } catch (IOException e) {

        }
    }

    private static void unicode() {

        char someSign = 'B';
        System.out.println((int)someSign);

        int someValue = 66;
        System.out.println((char)someValue);
    }

    private static void charsetCoding() {

        System.out.println("Charset default: " + Charset.defaultCharset());

        String someSentence = "Some sentence ęłąłóź";
        byte[] bytes = someSentence.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes, StandardCharsets.US_ASCII));
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

        //Charset.availableCharsets().keySet().forEach(System.out::println);
    }
}
