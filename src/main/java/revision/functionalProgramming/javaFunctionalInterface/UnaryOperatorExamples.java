package revision.functionalProgramming.javaFunctionalInterface;

import org.apache.logging.log4j.util.Strings;

import java.util.function.UnaryOperator;

public class UnaryOperatorExamples {

    public static void main(String[] args) {

        UnaryOperator<String> unaryOperator = str -> str + str;
        System.out.println(unaryOperator.apply("test"));

        UnaryOperator<String> unaryOperator2 = UnaryOperatorExamples::doubleDouble;
        System.out.println(unaryOperator.apply("double"));
    }

    public static String doubleDouble(String str) {
        System.out.println("Calling doubleDouble");
        return str + str;
    }
}
