package org.example;

import java.util.Scanner;

public class TicketingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        // Collect user input for configuration
        System.out.println("Welcome to the Ticketing System Configuration!");

        config.getConfiguration();

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