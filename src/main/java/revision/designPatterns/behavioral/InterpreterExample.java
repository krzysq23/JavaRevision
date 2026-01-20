package revision.designPatterns.behavioral;

import lombok.AllArgsConstructor;

public class InterpreterExample {

    /*
        Interpreter to behawioralny wzorzec projektowy, który służy do:
        - definiowania gramatyki prostego języka i interpretowania (wykonywania) wyrażeń tego języka
        Najprościej:
        „Tworzysz mini-język i kod, który go rozumie.”

        Problem, który rozwiązuje Interpreter
        Masz:
        • powtarzalne, proste reguły
        • wyrażenia zapisane jako tekst lub struktura
        • potrzebę ich interpretacji w runtime
        Przykłady:
        • reguły biznesowe
        • filtry
        • proste DSL
        • warunki typu: AND, OR, >, <

        Struktura wzorca:

        Client → Expression
                      ▲
                TerminalExpression
                NonTerminalExpression

        • Expression – interfejs
        • TerminalExpression – elementy końcowe (np. liczby)
        • NonTerminalExpression – reguły (np. AND, ADD)

        Zalety:
        • czytelna struktura
        • łatwa rozbudowa reguł
        • zgodność z OCP
        Wady:
        • dużo klas
        • niska wydajność
        • trudne debugowanie

     */

    public static void main(String[] args) {

        Expression expr =
                new AddExpression(
                        new NumberExpression(2),
                        new AddExpression(
                                new NumberExpression(3),
                                new NumberExpression(4)
                        )
                );

        System.out.println("Expression: " + expr.interpret());
    }

    interface Expression {
        int interpret();
    }

    @AllArgsConstructor
    static class NumberExpression implements Expression {

        private final int number;

        @Override
        public int interpret() {
            return number;
        }
    }

    @AllArgsConstructor
    static class AddExpression implements Expression {

        private final Expression left;
        private final Expression right;

        @Override
        public int interpret() {
            return left.interpret() + right.interpret();
        }
    }

}
