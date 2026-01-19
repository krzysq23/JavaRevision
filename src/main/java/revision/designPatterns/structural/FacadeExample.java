package revision.designPatterns.structural;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FacadeExample {

    /*
        Facade to strukturalny wzorzec projektowy, którego celem jest:
        - udostępnić prosty, spójny interfejs do złożonego systemu
        Czyli:
        „ukryć skomplikowane wnętrze systemu za jedną, wygodną fasadą”.

        Przykład z życia:
        Przyjmijmy, że chcemy przykryć za fasadą proces wytworzenia samochodu. Proces taki składa się z
        wielu kroków, ale załóżmy, że chcemy te kroki zgrupować ze sobą i przykryć fasadą.

     */

    public static void main(String[] args) {

        CarFactoryFacade carFactoryFacade = new CarFactoryFacade();
        carFactoryFacade.produceCar();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CarFactoryFacade {

        private FrameAssembler frameAssembler;
        private Painter painter;
        private EngineMaker engineMaker;
        private CockpitAssembler cockpitAssembler;
        private DoorMaker doorMaker;
        private SeatsMaker seatsMaker;
        private WheelsProducer wheelsProducer;

        public void produceCar() {
            frameAssembler.assembleFrame(); // złożenie ramy
            painter.paintSkeleton(); // malowanie szkieletu
            engineMaker.fastenEngine(); // przymocuj silnik
            cockpitAssembler.mountCockpit(); // zamontuj kokpit
            doorMaker.installDoor(); // zamontuj drzwi
            seatsMaker.attachSeats(); // zamontuj siedzenia
            wheelsProducer.attachWheels(); // przykręć koła
        }
    }

    public interface FrameAssembler {
        void assembleFrame();
    }

    public interface Painter {
        void paintSkeleton();
    }

    public interface EngineMaker {
        void fastenEngine();
    }

    public interface CockpitAssembler {
        void mountCockpit();
    }

    public interface DoorMaker {
        void installDoor();
    }

    public interface SeatsMaker {
        void attachSeats();
    }

    public interface WheelsProducer {
        void attachWheels();
    }

}
