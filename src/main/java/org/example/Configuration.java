package org.example;

import java.io.Serializable;
import java.util.Scanner;

public class Configuration implements Serializable {
    private static int totalTickets;
    private static int ticketReleaseRate;
    private static int customerRetrievalRate;
    private static int maxTicketCapacity;

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public static void getConfiguration(){
        totalTickets = validateConfiguration("Enter the total number of tickets: ");
        ticketReleaseRate = validateConfiguration("Enter the ticket release rate: ");
        customerRetrievalRate = validateConfiguration("Enter the customer retrieval rate: ");

        while (true) {
            maxTicketCapacity = validateConfiguration("Enter the maximum ticket capacity: ");
            if (maxTicketCapacity <= totalTickets) {
                break; // Exit the loop if maxTicketCapacity is valid
            } else {
                System.out.println("Maximum ticket capacity must be less than or equal to the total number of tickets.");
            }
        }
    }
    public static int validateConfiguration(String prompt){
        Scanner reader = new Scanner(System.in);
        int input ;
        while (true){
            System.out.print(prompt);

            try{
                String userInput = reader.nextLine();
                input = Integer.parseInt(userInput);
                if(input>0){
                    return input;
                }else{
                    System.out.println("Please enter a valid number.");
                }
            }catch (NumberFormatException e){
                System.out.println("Please enter a positive integer.");
            }
        }
    }
}

