package org.example;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final String vendorName;
    private final int ticketsPerRelease;
    private final int releaseInterval;

    public Vendor(TicketPool ticketPool, String vendorName, Configuration config) {
        this.ticketPool = ticketPool;
        this.vendorName = vendorName;
        this.ticketsPerRelease = 1; // Tickets per release
        this.releaseInterval = config.getTicketReleaseRate();  // Release interval in ms
    }

    @Override
    public void run() {
        try {
            int ticketCounter = 1;
            while (true) {
                for (int i = 0; i < ticketsPerRelease; i++) {
                    String ticket = vendorName + "-T" + ticketCounter++;
                    ticketPool.addTickets(ticket);
                }
                Thread.sleep(releaseInterval); // Simulate time between releases
            }
        } catch (InterruptedException e) {
            System.out.println(vendorName + " interrupted.");
        }
    }
}