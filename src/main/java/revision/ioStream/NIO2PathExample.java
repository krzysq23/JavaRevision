package revision.ioStream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NIO2PathExample {

    /*
    NIO.2 - Non Blocking IO 2 (wprowadzone w java 7) - rozwiazania nie blokujące operacji na plikach
     */

    public static void main(String[] args) {

        /*
        Path - interfejs reprezentujący ścieżkę w systemie plików. Jest następcą starej klasy java.io.File

        URI - Uniform Resource Identifier - sposób na identryfikacje zasobu
        scheme://authority/path?query#fragment
        - scheme – protokół / rodzaj zasobu
        np. http, https, file, ftp
        - authority – „władza” nad zasobem:
        user@host:port
        np. example.com, user@host:8080
        - path – ścieżka do zasobu
        np. /api/users, /images/logo.png
        - query – część po ?, parametry
        np. ?page=2&sort=asc
        - fragment – część po #, kotwica
        np. #section1
         */
        try {
            URI uri = Paths.get("src/main/java/revision/ioStream/files/example.txt").toUri();
            Path path = Paths.get(uri);
            Path path1 = Paths.get("src/main/java/revision/ioStream/files/example2.txt");
            Path pathFolder = Paths.get("src/main/java/revision/ioStream/files");

            System.out.println(Files.exists(path));

            FileSystem fileSystem = FileSystems.getDefault();
            System.out.println(fileSystem.getClass());
            Path pathFS = fileSystem.getPath("src/main/java/revision/ioStream/files/example.txt");
            System.out.println(pathFS);

            File someFile = new File("someFile.txt");
            Path somePath = someFile.toPath();

            System.out.println();
            // toString()
            System.out.println("toString: " + path.toString());
            // getNameCount()
            System.out.println("getNameCount: " + path.getNameCount());
            // getName()
            System.out.println("getNameCount: " + path.getName(path.getNameCount() - 1));
            // getFileName()
            System.out.println("getNameCount: " + path.getFileName());
            // toAbsolutePath()
            System.out.println("toAbsolutePath: " + path.toAbsolutePath());
            // isAbsolute()
            System.out.println("isAbsolute: " + path.isAbsolute());
            // getParent()
            System.out.println("getParent: " + path.getParent());
            // getRoot()
            System.out.println("getRoot: " + path.getRoot());
            // subpath()
            System.out.println("subpath: " + path.subpath(1, 4));
            // relativize() - przejście z jednej ścieżki do drugiej
            Path path3 = Paths.get("src/main/resources/Store.properties").toAbsolutePath();
            System.out.println("relativize: " + path.relativize(path3));
            // resolve() - sklejanie ścieżek
            System.out.println("resolve: " + path.resolve(path3));
            // normalize()
            Path path4 = Paths.get("src/../main/resources/../Store.properties");
            System.out.println("normalize: " + path4.normalize());
            // toRealPath
            System.out.println("toRealPath: " + path.toRealPath());

            // Working directory
            System.out.println("toRealPath: " + Paths.get("."));


            // Files.exists
            System.out.println("Files.exists: " + Files.exists(path));
            // Files.createDirectory lub createDirectories (tworzy wiecej ścieżek jeżeli nie istnieja w ścieżce)
            if(!Files.exists(path)) {
                System.out.println("Files.createDirectory: " + Files.createDirectory(path));
            }
            // Files.copy
            if(!Files.exists(path)) {
                System.out.println("Files.copy: " +
                        Files.copy(path, path1)
                );
            }
            // Files.move - moze tez zmienić nazwe
            if(!Files.exists(path)) {
                System.out.println("Files.move: " +
                        Files.move(path, pathFolder)
                );
            }
            // Files.copy
            if(!Files.exists(path)) {
                System.out.println("Files.copy: " +
                        Files.copy(path, path1)
                );
            }
            // Files.deleteIfExists
//            System.out.println("Files.move: " + Files.deleteIfExists(path1));
            // Files.readAllLines
            List<String> readAllLines = Files.readAllLines(path);
            System.out.println("Files.readAllLines: " + readAllLines);
            // Files.isSameFile
            System.out.println("Files.isSameFile: " + Files.isSameFile(path, path1));


            // newBufferedReader
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Files.newBufferedReader: " + line);
                }
            }

            // newBufferedWriter
            try (BufferedWriter writer = Files.newBufferedWriter(path1)) {
                writer.write("Test1");
                writer.newLine();
                writer.write("File content");
            }

            // Atrybuty plików
            // size
            System.out.println("Files.size: " + Files.size(path));
            // getLastModifiedTime
            System.out.println("Files.getLastModifiedTime: " + Files.getLastModifiedTime(path));
            // setLastModifiedTime
            Files.setLastModifiedTime(path, FileTime.from(190123L, TimeUnit.HOURS));
            System.out.println("Files.setLastModifiedTime: " + Files.getLastModifiedTime(path));
            // isHidden
            System.out.println("Files.size: " + Files.isHidden(path));
            // isReadable
            System.out.println("Files.isReadable: " + Files.isReadable(path));
            // isExecutable
            System.out.println("Files.isExecutable: " + Files.isExecutable(path));

            Stream<Path> list = Files.list(pathFolder);
            List<Path> absolutes = list
                    .filter(Files::isRegularFile)
                    .map(Path::toAbsolutePath)
                    .toList();
            absolutes.forEach(System.out::println);

            Stream<String> lines = Files.lines(path);
            List<String> lineList = lines
                    .filter(l -> l.contains("s"))
                    .toList();
            lineList.forEach(System.out::println);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
