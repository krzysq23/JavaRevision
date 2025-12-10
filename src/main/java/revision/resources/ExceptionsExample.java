package revision.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExceptionsExample {

    public static void main(String[] args) {

        //example(Paths.get("someFile.txt"), Paths.get("someNewFile.txt"));

        //example2(Paths.get("someFile.txt"), Paths.get("someNewFile.txt"));

        example3(Paths.get("someFile.txt"), Paths.get("someNewFile.txt"));
    }

    private static void example3(Path path1, Path path2) {
        try (Door door = new Door()) {
            System.out.println("Opening the door!");
            throw new RuntimeException("Exception while opening the door!");
        } catch (Exception e) {
            System.err.println("Normal exception: " + e.getMessage());
            /*
            SupressedException - wyjątek dorzucony do głównego wyjątku!
            Ważne żeby je też zbierać!
             */
            for (Throwable throwable: e.getSuppressed()) {
                System.err.println("Suppressed: " + throwable.getMessage());
            }
        } finally {
            System.out.println("Calling finally");
        }
    }

    static class Door implements AutoCloseable {
        public Door() {
            System.out.println("Calling door constructor!");
        }

        /*
        Metoda close() MUSI BYC IDENPOTENTNA!
        czyli za kazdym wywołaniem efekt ma być taki sam
         */
        @Override
        public void close() throws Exception {
            System.out.println("calling close()!");
            throw new RuntimeException("Some exception occurred during close()");
        }
    }

    private static void example2(Path path1, Path path2) {
        /*
            konstrukcja try with resources
            Zabezpiecza ona zamknięcie zasobów podczas zakończenia wywołania
            Stworzone obiekty musz implementować interfejs AutoCloseable
         */
        try (
                BufferedReader in = Files.newBufferedReader(path1);
                BufferedWriter out = Files.newBufferedWriter(path2)
        ) {
            String line = in.readLine() + " changed!";
            out.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example(Path path1, Path path2) {
        BufferedReader in = null;
        BufferedWriter out = null;
        System.out.println(path1.toAbsolutePath());
        try {
            in = Files.newBufferedReader(path1);
            out = Files.newBufferedWriter(path2);
            String line = in.readLine() + " changed!";
            out.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
