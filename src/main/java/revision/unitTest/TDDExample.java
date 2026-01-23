package revision.unitTest;

public class TDDExample {

    /*
        Test Driven Development. Jest podejściem do programowania, w którym definiujemy
        przypadki testowe określające co ma robić i jak ma się zachowywać kod, który piszemy, zanim go
        napiszemy. W praktyce sprowadza się to do tego, że zanim napiszemy kod, piszemy testy.

        Zalety stosowania podejścia TDD
        • z czasem jesteśmy w stanie osiągnąć stan, w którym mamy kod wraz z zestawem testów, które są w
          stanie automatycznie ten kod sprawdzać, oczywiście pisząc w sposób odwrotny (czyli najpierw kod,
          potem testy) w pewnym momencie również osiągniemy ten stan,
        • pomaga zrozumieć kod zanim go napiszemy, gdyż myślimy o tym jak różne fragmenty kodu mają ze
          sobą rozmawiać,
        • pomaga rozbić kod na mniejsze części, gdyż zależy nam na tym, aby metody testowe były jak
          najmniejsze. Wtedy każda metoda faktycznie realizuje swoją funkcję,
        • daje nam pewność siebie w momencie gdy zaczynamy coś w kodzie zmieniać. W praktyce często
          zmiana kodu, modyfikacja czy refactor powoduje, że możemy bardzo łatwo wprowadzić błędy.
          Często jest niemożliwe sprawdzenie wszystkich przypadków, które mogliśmy popsuć. Dlatego lepiej
          jest mieć automat, który sprawdza popełniane przez nas błędy,
        • w przypadku pracy zespołowej, jeżeli najpierw napiszemy wspólnie testy to łatwiej jest podzielić
          pracę, ustalić kto potem ma implementować różne fragmenty systemu,

        Wady stosowania podejścia TDD:
        • stosując podejście TDD zaczniemy mieć problem jeżeli często zmieniane są wymagania dotyczące
          tego co faktycznie kod przez nas pisany ma robić. Wyobraź sobie, napisaliśmy już kilka testów,
          podzieliliśmy to w głowie, zaczynamy pisać kod i przychodzi mail, że zaraz jest spotkanie, na którym
          się dowiesz, że połowa z tego co zostało przez Ciebie wymyślone jest do wyrzucenia. Więc znowu
          poprawiasz testy, chcesz zacząć pisać kod i dowiadujesz się, że zmienione zostały wymagania. W
          takim przypadku znacznie wydłuża to czas developmentu,
        • możemy również wprowadzić błędy w samych testach, wtedy napiszemy błędny kod, który
          powoduje, że błędne testy przechodzą,
        • takie podejście jest często ciężko obronić przez biznesem (mówię z praktyki). Bo z perspektywy
          biznesu, dowozimy funkcjonalność o wiele dłużej, niż byśmy to robili nie pisząc testów wcale.
          Natomiast jest to perspektywa bardzo krótkoterminowa, bo w długiej perspektywie zapewniamy
          sobie możliwość popełnienia mniejszej ilości błędów. Ten punkt w sumie jest niezależny od tego czy
          piszemy najpierw testy, czy kod. Pomaga tutaj jak cały zespół ma spójne podejście i wszyscy mocno
          naciskają na pisanie testów uzasadniając dlaczego ma to sens.

     */
}
