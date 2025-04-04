package org.example;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Scanner;

public class Configuration implements Serializable {
    private int totalTickets; //total number of tickets available in the system
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity; //max capacity of the ticket pool can hold

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

    public void getConfiguration(){
        totalTickets = validateConfiguration("Enter the total number of tickets: ");
        ticketReleaseRate = validateConfiguration("Enter the ticket release rate: ");
        customerRetrievalRate = validateConfiguration("Enter the customer retrieval rate: ");

        while (true) {
            maxTicketCapacity = validateConfiguration("Enter the maximum ticket capacity: ");
            if (totalTickets <= maxTicketCapacity) {
                break; // Exit the loop if maxTicketCapacity is valid
            } else {
                System.out.println("Total ticket capacity must be less than or equal to the maximum number of tickets.");
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
    public void saveConfiguration(){
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("configuration.json")) {
            gson.toJson(this, writer);
            System.out.println("File saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

