package revision.concurrency.code;

public class InvoiceNameGenerator {

    private static final String INVOICE_NAME_PATTERN = "FV_";

    private int lastInvoiceNumber = 0;

    public String generateNewInvoiceName() {
        synchronized(this){
            lastInvoiceNumber = lastInvoiceNumber + 1;
            return INVOICE_NAME_PATTERN + lastInvoiceNumber;
        }
    }

}
