package com.mycompany.st10483276poe_26;

import java.io.File;
import java.util.Scanner;

public class MessageManager { 
    // ------------------ Parallel Arrays ------------------
    /* Source: GeeksforGeeks, 2026. Arrays in Java. [online]
     Available at: <https://www.geeksforgeeks.org/java/arrays-in-java/>
     Search Query: Java arrays
     Date Accessed: 12 June 2026
     Purpose: Used to clarify on arrays and common operations in Java.
     Modification: Adapted examples to impliment array based storage and iteration, like managing multiple values.
    */       
    private String[] messageID = new String[100];
    private String[] MessageHash = new String[100];
    private String[] recipients = new String[100];
    private String[] messagesText = new String[100];
    private String[] statuses = new String[100]; // "Sent" , "Disregarded" OR , "Stored"  
    
    private int messageCount = 0;
    
    // Method to add a message to the parallel arrays 
    public void addMockData(String id, String hash, String recipient, String text, String status) {
        if(messageCount < 100) {
            messageID[messageCount] = id;
            MessageHash[messageCount] = hash;
            recipients[messageCount] = recipient;
            messagesText[messageCount] = text;
            statuses[messageCount] = status;
            messageCount++;                                        
        }
    }
    
    /* 
     * Reads the storedMessages.json file line-by-line and extract data
     * Uses basic String manipulation to populate arrays
     */
    public void loadStoredMessagesFromJson(){
        try {
            File file = new File("StoredMessages.json");
            if (!file.exists()) {
                return;
            }
            Scanner fileReader = new Scanner(file);
            String id = "", hash = "", rec = "", text = "";
            
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                
                if(line.contains("\"MessageID\":")) {
                    id = extractJsonValue(line);
                } else if (line.contains("\"MessageHash\":")) {
                    hash = extractJsonValue(line);                    
                } else if (line.contains("\"Recipient\":")) {
                    rec = extractJsonValue(line); 
                } else if (line.contains("\"Message\":")) {
                    text = extractJsonValue(line);   
                    //once on  message text, the object is complete
                    addMockData(id, hash, rec, text, "Stored");
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error reading JSON file.");            
        }        
    }
    
    // Helper method to slice out data between the quotes
    private String extractJsonValue(String line) {
        int firstQuote = line.indexOf("\"", line.indexOf(":") + 1);
        int lastQuote= line.lastIndexOf("\"");
        if (firstQuote != -1 && lastQuote != -1 && firstQuote < lastQuote) {
            return line.substring(firstQuote + 1, lastQuote);
        }
        return "";
    }
    
    //------------------ Functions ------------------
    public void displaySenderAndRecipient() {
        System.out.println("\n--- Senders & Recipients of Stored Messages ---");
        /* Source: GeeksforGeeks, 2026. Java for loop with examples. [online]
         Available at: <https://www.geeksforgeeks.org/java/java-for-loop-with-examples/>
         Search Query: Java for loops
         Date Accessed: 22 June 2026
         Purpose: Used to clarify on for loops in Java, including initialization, condition and increment operations.
         Modification: Adapted examples to impliment logic like looping through arrays and control repeated execution of statements.
        */                 
        for (int i = 0; i < messageCount; i++) {
            if (statuses[i] != null && statuses[i].equals("Stored")){
                System.out.println("Systen Developer ->" + recipients[i]);
            }
        }        
    }
    
    public String getLongestMessage() {
        String longest = "";
        for (int i = 0; i < messageCount; i++) {
            if (messagesText[i] != null && messagesText[i].length() > longest.length()){
                longest = messagesText[i];
            }
        } 
        return longest;
    }

    public String searchMessageByID(String searchID) {
        for (int i = 0; i < messageCount; i++) {
            if (messageID[i] != null && messageID[i].equals(searchID)){
                return messagesText[i];
            }
        } 
        return "Message not found, please try again";
    }  

    public String searchByRecipient(String searchRec) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < messageCount; i++) {
            if (recipients[i] != null && recipients[i].equals(searchRec)){
                results.append(messagesText[i]).append("\n") ;
            }
        } 
        return results.toString().isEmpty() ? "No messages found." : results.toString().trim();
    }   
    
    public String deleteMessageByHash(String searchHash) {
        for (int i = 0; i < messageCount; i++) {
            if (MessageHash[i] != null && MessageHash[i].equals(searchHash)) {
                String text = messagesText[i];
                messageID[i] = null;
                MessageHash[i] = null;
                recipients[i] = null;   
                messagesText[i] = null;
                statuses[i] = null;
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }
    
    public void displayReport() {
        System.out.println("\n=================== FINAL MESSAGE REPORT ===================");
        for (int i = 0; i < messageCount; i++) {
            if (messageID[i] != null) {
                System.out.println("Status:" + statuses[i]);
                System.out.println("Hash:" + MessageHash[i]);
                System.out.println("Recipient:" + recipients[i]);
                System.out.println("Message:" + messagesText[i]);
                System.out.println("-------------------------------------------------------");
            }
        }
    }
    
    // Used for the JUnit testing
    public String getAllSentMessages() {
        StringBuilder results = new StringBuilder();
        for(int i = 0; i < messageCount; i++){
            if (statuses[i] != null && statuses[i].equals("Sent")) {
                results.append(messagesText[i]).append("\n");
            }
        }
        return results.toString();
    }          
}
