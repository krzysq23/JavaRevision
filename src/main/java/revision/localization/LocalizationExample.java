package revision.localization;

import org.springframework.cglib.core.Local;

import java.util.Locale;

public class LocalizationExample {

    /*
    Pojęcie	                Skrót	    Co oznacza?
    Internationalization	I18N	    Umożliwienie obsługi różnych języków
    Localization	        L10N	    Dostosowanie aplikacji do konkretnego języka/regionu
     */

    public static void main(String[] args) {

        // Localization
        var locale = Locale.getDefault();
        System.out.println(locale);
        System.out.println(Locale.ENGLISH);
        System.out.println(Locale.UK);
        System.out.println(Locale.US);
        System.out.println(Locale.GERMAN);
        System.out.println(Locale.GERMANY);

        System.out.println(new Locale("pl", "PL"));
        System.out.println(new Locale("en", "PL"));

        Locale.setDefault(new Locale("pl", "PL"));
        System.out.println(Locale.getDefault());


    }

}
