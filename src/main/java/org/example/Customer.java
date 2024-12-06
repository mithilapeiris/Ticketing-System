package org.example;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final String customerName;
    private final int retrievalInterval;

    public Customer(TicketPool ticketPool, String customerName, Configuration config) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.retrievalInterval = config.getCustomerRetrievalRate(); // Retrieval interval in ms
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ticket = ticketPool.removeTicket();
                System.out.println(customerName + " purchased ticket: " + ticket);
                Thread.sleep(retrievalInterval); // Simulate time between purchases
            }
        } catch (InterruptedException e) {
            System.out.println(customerName + " interrupted.");
        }
    }
}
