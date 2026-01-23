package revision.unitTest;

import revision.unitTest.code.Calculator;

public class JunitExample {

    /*
    JUnit
    • JUnit Platform - jest platformą służącą do uruchamiania różnych frameworków testowych na
      maszynie wirtualnej Javy. Oznacza to, że przy wykorzystaniu platformy JUnit Platform możemy
      również uruchamiać inne biblioteki testujące takie jak Spock, Cucumber lub FitNesse, ale o nich nie
      będziemy rozmawiać. JUnit Platform zapewnia również interface pomiędzy JUnit a np. narzędziami do budowania.
    • JUnit Jupiter - moduł zawierający nowy model (np. nowe adnotacje), który został wprowadzony w JUnit w wersji 5.
    • JUnit Vintage - moduł umożliwiający uruchomienie testów napisanych w JUnit 3 lub 4.

    3 logiczne fragmenty:
    • given - w którym definiujemy co jest parametrem wejściowym naszego testu,
    • when - w którym określamy jaka logika jest faktycznie testowana,
    • then - w który definiujemy co jest spodziewanym wynikiem

    Metoda              Przykład
    assertEquals        assertEquals(2, 12, "Opcjonalna wiadomość przy niepowodzeniu");
    assertTrue          assertTrue(5 != 7, () → "Opcjonalna wiadomość przy niepowodzeniu");
    assertFalse         assertFalse(2 == 4, "Opcjonalna wiadomość przy niepowodzeniu");
    assertNotNull       assertNotNull(jakaśReferencja, "Opcjonalna wiadomość przy niepowodzeniu");
    assertNull          assertNull(jakaśReferencja, "Opcjonalna wiadomość przy niepowodzeniu");

     Maven pomojanie testow:
     mvn verify -DskipTests

     Gradle pomijanie testów:
     gradle build -x test

     Plugin jacoco - umożliwia nam określenie minimalnego progu pokrycia kodu testami.

     */

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.add(1, 3);
    }
}
