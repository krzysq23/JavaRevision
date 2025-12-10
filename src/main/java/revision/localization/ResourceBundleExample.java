package revision.localization;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ResourceBundleExample {

    /*
        ResourceBundle w Javie automatycznie wybiera właściwe tłumaczenia
        na podstawie ustawionego języka, bez modyfikacji kodu.
     */

    public static void main(String[] args) {

        System.out.println(Locale.getDefault());

        ResourceBundle store = ResourceBundle.getBundle("Store", new Locale("pl"));
        String mainPage = store.getString("mainPage");
        String footer = store.getString("footer");
        String defaultProperty = store.getString("defaultProperty");

        System.out.println(mainPage);
        System.out.println(footer);
        System.out.println(defaultProperty);

        Map<String, String> collect = store.keySet().stream()
                .collect(Collectors.toMap(k -> k, store::getString));
        System.out.println(collect);
        System.out.println(collect.get("defaultProperty"));

        // Przekazanie args
        System.out.println("args[0]: " + args[0] + ", args[1]: " + args[1]);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Store", new Locale(args[0], args[1]));
        System.out.println(resourceBundle.getString("mainPage") + " : " + resourceBundle.getString("footer"));
    }
}
