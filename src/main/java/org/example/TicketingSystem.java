package org.example;

import java.util.Scanner;

public class TicketingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        // Collect user input for configuration
        System.out.println("Welcome to the Ticketing System Configuration!");

        //config.getConfigDetails();

        System.out.print("Enter total number of tickets: ");
        config.setTotalTickets(scanner.nextInt());

        System.out.print("Enter ticket release rate (ms): ");
        config.setTicketReleaseRate(scanner.nextInt());

        System.out.print("Enter customer retrieval rate (ms): ");
        config.setCustomerRetrievalRate(scanner.nextInt());

        System.out.print("Enter maximum ticket capacity: ");
        config.setMaxTicketCapacity(scanner.nextInt());

        // Validate configuration
        if (!config.isValid()) {
            System.out.println("Invalid configuration. Exiting...");
            return;
        }

        System.out.println("Configuration is valid. Initializing system...");

        // Initialize TicketPool
        TicketPool ticketPool = new TicketPool(config.getMaxTicketCapacity());

        // Create Vendor and Customer threads
        Thread vendor1 = new Thread(new Vendor(ticketPool, "Vendor1", config));
        //Thread vendor2 = new Thread(new Vendor(ticketPool, "Vendor2", config));

        Thread customer1 = new Thread(new Customer(ticketPool, "Customer1", config));
        //Thread customer2 = new Thread(new Customer(ticketPool, "Customer2", config));

        // Start threads
        vendor1.start();
        //vendor2.start();
        customer1.start();
        //customer2.start();

        // Add shutdown hook to stop threads gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            vendor1.interrupt();
            //vendor2.interrupt();
            customer1.interrupt();
            //customer2.interrupt();
            System.out.println("System shutting down...");
        }));

        scanner.close();
    }
}