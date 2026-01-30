package revision.concurrency.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class InvoiceNameGeneratorLock {

    private static final String INVOICE_NAME_PATTERN = "FV_";

    private final Lock lock = new ReentrantLock();
    private int lastInvoiceNumber = 0;

    public String generateNewInvoiceName() {
        lock.lock();
        try {
            lastInvoiceNumber++;
            return INVOICE_NAME_PATTERN + lastInvoiceNumber;
        } finally {
            lock.unlock();
        }
    }

}
