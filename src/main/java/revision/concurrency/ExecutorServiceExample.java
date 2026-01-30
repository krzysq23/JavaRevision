package revision.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {

    /*
        ExecutorService Jest to zbiór wątków, które czekają na wykorzystanie w celu wykonania przeznaczonych im zadań.
        Zamiast samemu tworzyć nowy wątek przy pomocy konstruktora, można posłużyć się wątkiem z thread poola, a
        po wykonaniu zadania zwrócić go tam z przeznaczeniem do wykonania kolejnych pojawiających się zadań.

     */

    public static void main(String[] args) {

        runnableMethod();
        callableMethod();
    }

    private static void callableMethod() {

        final String TEST_STRING = "Mój kod jest bezbłędny";
        final String NEW_STRING = "czasami ";

        ExecutorService executorService = null;

        try {
            StringBuilder stringBuilder = new StringBuilder(TEST_STRING);

            executorService = Executors.newSingleThreadExecutor();
            System.out.println("Tutaj rozpoczynam");

            Future<StringBuilder> expectedResult = executorService
                    .submit(() -> stringBuilder.replace(13, 16, NEW_STRING));

            printValueWhenReady(expectedResult);
        } catch (ExecutionException | InterruptedException exception) {
            // obsługa wyjątku
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private static void printValueWhenReady(
            Future<StringBuilder> stringBuilderFuture
    ) throws ExecutionException, InterruptedException {
        while (!stringBuilderFuture.isDone()) {
            System.out.println("Czekam na wykonanie zadania!");
        }
        System.out.println(stringBuilderFuture.get().toString());
    }

    private static void runnableMethod() {

        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            executorService
                    .execute(() -> System.out.println("Pierwsze zadanie z użyciem ExecutorService"));
            executorService
                    .execute(() -> System.out.println("... i drugie"));
        } finally {
            if(executorService != null) {
                executorService.shutdown();
            }
        }

    }
}
