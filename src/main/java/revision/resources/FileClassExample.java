package revision.resources;

import java.io.File;

public class FileClassExample {

    public static void main(String[] args) {

        /*
        Klasa File służy do odczytu pliku - nie edycji
         */
        File file = new File("someFile.txt");
        if(!file.exists()) {
            System.out.println("Sile not exist!");
        }

        System.out.println("getAbsolutePath:" + file.getAbsolutePath());
        System.out.println("getParent:" + file.getParent());

        System.out.println("isFile:" + file.isFile());
        System.out.println("length:" + file.length());

        File directory = new File("src/main");
        System.out.println("Dir path: " + directory.getAbsolutePath());
        if(directory.isDirectory()) {
            System.out.println("File is directory" );
            for (File listFile: directory.listFiles()) {
                System.out.println("Subfile: " + listFile);
            }
        }



    }
}
