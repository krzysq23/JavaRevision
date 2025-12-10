package revision.localization;

import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class FormatLocalization {

    public static void main(String[] args) {

        Locale.setDefault(new Locale("pl", "PL"));

        /*
        NumberFormat
         */
        int number = 1_234_567;
        Locale localePL = new Locale("pl", "PL");
        Locale localeUs = Locale.US;


        System.out.println("NumberFormat.getInstance()");
        System.out.println("PL: " + NumberFormat.getInstance(localePL));
        System.out.println("US: " + NumberFormat.getInstance(localeUs));
        System.out.println("Germany: " + NumberFormat.getInstance(Locale.GERMANY));

        System.out.println("\nNumberFormat.getNumberInstance()");
        System.out.println("PL: " + NumberFormat.getNumberInstance(localePL).format(number));
        System.out.println("US: " + NumberFormat.getNumberInstance(localeUs).format(number));
        System.out.println("Germany: " + NumberFormat.getNumberInstance(Locale.GERMANY).format(number));

        System.out.println("\nNumberFormat.getPercentInstance()");
        System.out.println("PL: " + NumberFormat.getPercentInstance(localePL).format(number));
        System.out.println("US: " + NumberFormat.getPercentInstance(localeUs).format(number));
        System.out.println("Germany: " + NumberFormat.getPercentInstance(Locale.GERMANY).format(number));

        System.out.println("\nNumberFormat.getCurrencyInstance()");
        System.out.println("PL: " + NumberFormat.getCurrencyInstance(localePL).format(number));
        System.out.println("US: " + NumberFormat.getCurrencyInstance(localeUs).format(number));
        System.out.println("Germany: " + NumberFormat.getCurrencyInstance(Locale.GERMANY).format(number));


        /*
        LocalDate
         */
        LocalDate localDate = LocalDate.of(2015, Month.JULY, 10);
        LocalTime localTime = LocalTime.of(12, 15, 10);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(3));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Poland"));

        System.out.println("\nDateTimeFormatter()");
        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(offsetDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        System.out.println(zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));

        DateTimeFormatter formatterFull = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter formatterLong = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter formatterMedium = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        DateTimeFormatter formatterShort = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        System.out.println("localDate FULL: " + localDate.format(formatterFull));
        System.out.println("localDate LONG: " + localDate.format(formatterLong));
        System.out.println("localDate MEDIUM: " + localDate.format(formatterMedium));
        System.out.println("localDate SHORT: " + localDate.format(formatterShort));

        DateTimeFormatter dateTimeFormatterMedium = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println("localTime MEDIUM: " + localDateTime.format(dateTimeFormatterMedium));
        System.out.println("localTime SHORT: " + localDateTime.format(dateTimeFormatterShort));

        System.out.println("offsetDateTime MEDIUM: " + offsetDateTime.format(dateTimeFormatterMedium));
        System.out.println("offsetDateTime SHORT: " + offsetDateTime.format(dateTimeFormatterShort));

        System.out.println("zonedDateTime MEDIUM: " + zonedDateTime.format(dateTimeFormatterMedium));
        System.out.println("zonedDateTime SHORT: " + zonedDateTime.format(dateTimeFormatterShort));

        System.out.println();

        DateTimeFormatter formatterFullGermany = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(Locale.GERMANY);
        DateTimeFormatter formatterLongGermany = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.LONG)
                .withLocale(Locale.GERMANY);
        DateTimeFormatter formatterMediumGermany = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(Locale.GERMANY);
        DateTimeFormatter formatterShortGermany = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.GERMANY);

        System.out.println("localDate GERMAN FULL: " + localDate.format(formatterFullGermany));
        System.out.println("localDate GERMAN LONG: " + localDate.format(formatterLongGermany));
        System.out.println("localDate GERMAN MEDIUM: " + localDate.format(formatterMediumGermany));
        System.out.println("localDate GERMAN SHORT: " + localDate.format(formatterShortGermany));


    }
}
