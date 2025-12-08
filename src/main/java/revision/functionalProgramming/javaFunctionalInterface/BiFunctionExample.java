package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.BiFunction;

public class BiFunctionExample {

    public static void main(String[] args) {

        BiFunction<Table, Window, String> function = (tbl, wnd) ->
                tbl.toString().concat("###").concat(wnd.toString());
        System.out.println(function.apply(new Table(), new Window()));

        BiFunction<Table, Window, String> function2 = new Caller()::concat;
        System.out.println(function2.apply(new Table(), new Window()));

        Caller caller = new Caller();
        BiFunction<Table, Window, String> function3 = caller::concat;
        System.out.println(function3.apply(new Table(), new Window()));
    }


    static class Caller {

        String concat(Table tbl, Window wnd) {
            System.out.println("Calling concat:");
            return tbl.toString().concat("###").concat(wnd.toString());
        }

    }


    static class Table {}

    static class Window {}
}
