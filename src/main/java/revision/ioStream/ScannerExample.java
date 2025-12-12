package revision.ioStream;

import java.util.Scanner;

public class ScannerExample {

    /*
     Scanner - klasa służy do czytania danych z pliku lub terminala
     oraz parsuje ona dane
     */

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter some data");

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println("Entered: " + line);

            if("done".equals(line)) {
                break;
            }
        }

    }
}
