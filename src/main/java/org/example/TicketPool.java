package org.example;

import java.util.LinkedList;

public class TicketPool {
    private final LinkedList<String> tickets = new LinkedList<>();
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Add tickets (used by Vendor)
    public synchronized void addTickets(String ticket) throws InterruptedException {
        while (tickets.size() >= maxCapacity) {
            wait(); // Wait if the pool is full
        }
        tickets.add(ticket);
        System.out.println("Ticket added: " + ticket);
        notifyAll(); // Notify consumers
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
