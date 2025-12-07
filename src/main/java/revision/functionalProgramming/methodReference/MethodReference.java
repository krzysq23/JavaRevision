package revision.functionalProgramming.methodReference;

import revision.functionalProgramming.functionalInterface.SomeFunctionalInterface;

public class MethodReference {

    public static void main(String[] args) {

        // method reference:
        // NazwaKlasy::NazwaMetody

        MilkProducer milkProducer = () -> "some milk";
        MilkConsumer milkConsumer = arg1 -> "some milk consumed: " + arg1;

//        System.out.println(milkProducer.produce());
//        System.out.println(milkConsumer.consumer("somethingToConsume"));

        // bez lambda
        MilkProducer milkProducer2 = MethodReference::someMilkReference;
        MilkConsumer milkConsumer2 = MethodReference::someMilkReference;
        System.out.println(milkProducer2.produce());
        System.out.println(milkConsumer2.consumer("somethingToConsume"));
    }


    public static String someMilkReference() {
        return "some milk from method";
    }

    public static String someMilkReference(String arg1) {
        return "consume milk from method: " + arg1;
    }

    @FunctionalInterface
    interface MilkProducer {
        String produce();
    }

    @FunctionalInterface
    interface MilkConsumer {
        String consumer(String toConsume);
    }

}
