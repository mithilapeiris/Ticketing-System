package org.example;

import java.util.LinkedList;

public class TicketPool {
    private final LinkedList<String> tickets = new LinkedList<>();
    private final int maxCapacity;
    private final int totalTickets; // Linked to totalTickets in Configuration
    private int addedTicketsCount = 0;

    public TicketPool(Configuration config) {
        this.maxCapacity = config.getMaxTicketCapacity();
        this.totalTickets = config.getTotalTickets();
    }
    // Add tickets (used by Vendor)
    public synchronized void addTickets(String ticket) throws InterruptedException {
        while (tickets.size() >= maxCapacity || addedTicketsCount >= maxCapacity) {
            if (addedTicketsCount >= maxCapacity) {
                System.out.println("Ticket limit reached. Vendor stopped adding tickets.");
                return; // Stop adding tickets if limit is reached
            }
            wait(); // Wait if the pool is full
        }
        tickets.add(ticket);
        addedTicketsCount++;
        System.out.println("Ticket added: " + ticket);
        notifyAll();// Notify consumers
    }

    // Remove tickets (used by Customer)
    public synchronized String removeTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait(); // Wait if the pool is empty
        }
        String ticket = tickets.removeFirst();
        System.out.println("Ticket removed: " + ticket);
        notifyAll(); // Notify producers
        return ticket;
    }

    // Method to check current ticket count
    public synchronized int getTicketCount() {
        return tickets.size();
    }
}
