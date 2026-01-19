package revision.designPatterns.structural;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public class AdapterExample {

    /*
        Adapter to strukturalny wzorzec projektowy, którego celem jest:
        - połączenie dwóch niekompatybilnych interfejsów bez zmiany ich kodu.
        Czyli: „sprawić, żeby coś, co do siebie nie pasuje, zaczęło współpracować”.
        Możemy je wykorzystać aby:
        - zmusić do pracy 2 interfejsy niezależne od siebie
        - napisać adapter zanim dotaniemy gotowe źródło danych

        Jeżeli napotkamy w praktyce sytuację, gdy będziemy mieli 2 interfejsy, które w żaden sposób nie są
        związane ze sobą i będziemy chcieli te interfejsy zachęcić do wspólnej pracy to możemy
        wykorzystać ten wzorzec. Inaczej mówiąc, wyobraźmy sobie, że mamy bibliotekę, która dostarcza swoje
        klasy (swoje API). Jednocześnie przygotowaliśmy wcześniej kod, w którym napisaliśmy jak chcielibyśmy
        z takiej biblioteki korzystać. Czyli nie znając biblioteki, napisaliśmy swój kod, w którym określiliśmy co
        chcemy mieć zrobione przez bibliotekę. Dokładnie w takich sytuacjach można zastosować wzorzec
        zwany Adapter.

        Zalety tego podejścia:
        • Jeżeli pracujemy nad bardzo starym kodem i chcemy go wymienić, to zamiast modyfikować go od
          razu możemy robić taką modyfikację na 3 kroki.
        ◦ Napisać Adapter ze starego kodu do nowego, który pozwoli nam nadal korzystać ze starej
          funkcjonalności, ale kod korzystający będzie już się opierał o kod "po nowemu",
        ◦ Napisać na nowo implementację, która nas interesuje,
        ◦ Przepiąć kod adaptowany z tego starego na nowy
        • Jeżeli będziemy musieli zintegrować się z API, którego jeszcze nie mamy, to możemy najpierw
          napisać kod Adaptera, który będzie zwracał nam jakieś dane na sztywno. Następnie wykorzystać ten
          kod adaptera do napisania kodu naszej aplikacji. Następnie gdy API, z którego mamy korzystać
          zostanie ukończone, możemy zmodyfikować adapter aby korzystał z prawidłowego źródła.
        • Wcześniej wspomnieliśmy również o dostosowaniu istniejącego juz API do naszej aplikacji jeżeli nie
          spełnia ono naszych wymagań.

     */

    public static void main(String[] args) {

        Dimension dimension = new SonyTV();
        DimensionAdapter dimensionAdapter = new DimensionAdapterImpl(dimension);

        System.out.println("Original: " + dimension.getDimension());
        System.out.println("Adapted: " + dimensionAdapter.getDimension());
    }

    @Data
    @AllArgsConstructor
    public static class DimensionAdapterImpl implements DimensionAdapter {

        private static final double INCh_TO_METER = 0.0254;

        private Dimension dimension;

        @Override
        public BigDecimal getDimension() {
            return convertInchToMeter(dimension.getDimension());
        }

        private BigDecimal convertInchToMeter(BigDecimal dimension) {
            return BigDecimal.valueOf(INCh_TO_METER).multiply(dimension);
        }
    }

    public interface DimensionAdapter {
        BigDecimal getDimension();
    }

    public static class SonyTV implements Dimension {
        @Override
        public BigDecimal getDimension() {
            return BigDecimal.valueOf(49);
        }
    }

    public interface Dimension {
        BigDecimal getDimension();
    }
}
