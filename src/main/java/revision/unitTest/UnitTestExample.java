package revision.unitTest;

public class UnitTestExample {

    /*
    Rodzaje testów:
    • Testy jednostkowe (unit tests) - pisane przez programistę, są szybkie i najtańsze w utrzymaniu,
      dotyczą małych, odizolowanych funkcjonalności. Pisząc te testy szukamy błędów
      implementacyjnych. Zapewniają one bezpieczeństwo w przypadku wprowadzania kolejnych zmian w aplikacji,
    • Testy integracyjne (integration tests) - testują zachowanie komponentów, na styku których następuje
      integracja. Komponenty są testowane w grupie odpowiedzialnej za jakąś funkcjonalność. Takie testy
      trwają odpowiednio dłużej. Sprawdzają poprawne działanie komunikacji pomiędzy komponentami.
      Są też nazywane testami komponentów lub testami podsystemów,
    ◦ Przykładem testu integracyjnego może być kod komunikujący się z bazą danych. W takim teście
      nie potrzeba w pełni działającej bazy, a wystarczy jedynie jej atrapa. Zamiast angażować jakiś
      ciężki i zaawansowany mechanizm można skorzystać z czegoś lekkiego, co będzie taką bazę
      imitować. Takie podejście ma swoje zalety i wady. Będziemy o tym rozmawiać później.
    ◦ Ten link najlepiej wyjaśnia różnicę pomiędzy testami jednostkowymi a integracyjnymi !,
    • Testy systemowe (end to end, e2e) - testy przeprowadzane na działającej aplikacji. Ich celem jest
      zasymulowanie prawdziwego zachowania użytkownika w systemie. Są trudniejsze, bardziej
      czasochłonne do zautomatyzowania i bardzo kosztowne w utrzymaniu ich prawidłowego działania
      ze względu na ciągły rozwój systemów. Z tego powodu często są przeprowadzanie manualnie, czyli,
      tester klika w systemie, sprawdzając poszczególne procesy biznesowe.

    Zasady pisania dobrych testów jednostkowych:
    - Nie używaj infrastruktury (używać mock)
    - Testy jednostkowe powinny być wyizolowane (jeżeli nasza klasa odwołuje się
      do innych klas, to te inne klasy powinny zostać zaślepione)
    - Testy jednostkowe powinny być małe
    - Testujmy nie tylko przypadki pozytywne
    - Testy powinny być niezależne od siebie
    - Testy powinny być deterministyczne (musimy zawsze się upewnić, czy testy zachowują się w
      sposób deterministyczny, czyli zawsze tak samo)

     */
}
