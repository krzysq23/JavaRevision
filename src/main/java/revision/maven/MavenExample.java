package revision.maven;

import org.jsoup.Jsoup;

import java.io.IOException;

public class MavenExample {

    public static void main(String[] args) throws IOException {

        System.out.println("XsWare.pl title: " + Jsoup.connect("https://xsware.pl").get().title());

    }
}
