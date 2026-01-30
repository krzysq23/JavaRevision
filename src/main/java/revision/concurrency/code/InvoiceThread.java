package revision.concurrency.code;

import java.util.stream.IntStream;

public class InvoiceThread extends Thread {

    InvoiceNameGenerator invoiceNameGenerator;

    public InvoiceThread(InvoiceNameGenerator invoiceNameGenerator) {
        this.invoiceNameGenerator = invoiceNameGenerator;
    }

    @Override
    public void run() {
        IntStream.range(0, 3)
                .forEach(iteration -> System.out.println(invoiceNameGenerator.generateNewInvoiceName()));

    }

}