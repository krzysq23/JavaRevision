package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.BinaryOperator;

public class BinaryOperatorExample {

    public static void main(String[] args) {

        BinaryOperator<String> binaryOperator = (str1, str2) -> str1.concat(str2);
        System.out.println(binaryOperator.apply("a", "b"));

        BinaryOperator<String> binaryOperator2 = String::concat;
        System.out.println(binaryOperator2.apply("123", "456"));


        BinaryOperator<String> binaryOperator3 = BinaryOperatorExample::concat;
        System.out.println(binaryOperator3.apply("abc", "def"));
    }

    public static String concat (String str1, String str2) {
        return str1.concat("").concat(str2);
    }
}
