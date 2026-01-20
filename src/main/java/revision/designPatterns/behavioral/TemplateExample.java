package revision.designPatterns.behavioral;

public class TemplateExample {

    /*
        Wzorzec Template method jest używany gdy chcemy określić jedno miejsce w kodzie, które w
        abstrakcyjny sposób określi kroki naszego algorytmu, natomiast konkretna implementacja tych kroków
        zostanie oddelegowana do konkretnych klas implementujących. Jednocześnie możemy zapewnić
        domyślną implementację takich kroków, która może być nadpisana w klasach implementujących
        konkretne kroki.

     */

    public static void main(String[] args) {

        HouseTemplate house1 = new BrickHouse();
        house1.build();

        System.out.println("\n===========\n");

        HouseTemplate house2 = new WoodenHouse();
        house2.build();
    }


    public abstract static class HouseTemplate {

        protected final void build() {
            digHole();
            buildFoundation();
            buildWalls();
            makeRoof();
            insertWindows();
        }

        protected void digHole() {
            System.out.println("digging hole");
        }

        protected abstract void buildFoundation();
        protected abstract void buildWalls();
        protected abstract void makeRoof();
        protected abstract void insertWindows();
    }


    public static class WoodenHouse extends HouseTemplate {

        @Override
        protected void buildFoundation() {
            System.out.println("WoodenHouse buildFoundation");
        }

        @Override
        protected void buildWalls() {
            System.out.println("WoodenHouse buildWalls");
        }

        @Override
        protected void makeRoof() {
            System.out.println("WoodenHouse makeRoof");
        }

        @Override
        protected void insertWindows() {
            System.out.println("WoodenHouse insertWindows");
        }
    }

    public static class BrickHouse extends HouseTemplate {

        @Override
        protected void buildFoundation() {
            System.out.println("BrickHouse buildFoundation");
        }

        @Override
        protected void buildWalls() {
            System.out.println("BrickHouse buildWalls");
        }

        @Override
        protected void makeRoof() {
            System.out.println("BrickHouse makeRoof");
        }

        @Override
        protected void insertWindows() {
            System.out.println("BrickHouse insertWindows");
        }
    }

}
