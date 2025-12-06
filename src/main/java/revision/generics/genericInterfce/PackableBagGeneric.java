package revision.generics.genericInterfce;

public class PackableBagGeneric<T> implements Packable<T> {

    /*
    W typach generycznych nie mozna:
    1. Utworzyc konstruktora typu generycznego
            T newElement ] new T();
    2. nie mozna stworzyc tablicy typu generycznego
        private T[] arr;
    3. Nie mozna zawołać instanceOf
        if( element instanceOf T)
    4. Nie można używać prymitywów jako generyków
    5. Nie można używać generóków przy statykach
        private static T variable;
     */

    @Override
    public void pack() {

    }

    @Override
    public T empty() {
        return null;
    }
}
