package com.mycompany.st10483276poe_26;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Message {
    
    //------------------ Private Variables ------------------ 
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    
    private static int totalMessages = 0;
    
    private int currentMessageNumber;
    
    // ------------------ Part 3: Parallel Arrays ------------------
    /* Source: GeeksforGeeks, 2026. Arrays in Java. [online]
     Available at: <https://www.geeksforgeeks.org/java/arrays-in-java/>
     Search Query: Java arrays
     Date Accessed: 12 June 2026
     Purpose: Used to clarify on arrays and common operations in Java.
     Modification: Adapted examples to implement array based storage and iteration, like managing multiple values.
    */       
    private String[] messageIDs = new String[100];
    private String[] messageHashes = new String[100];
    private String[] recipients = new String[100];
    private String[] messagesText = new String[100];
    private String[] statuses = new String[100]; // "Sent", "Disregarded", "Stored"
    
    private int messageCount = 0;    
    
    // ------------------ Constructor ------------------
    public Message() {
        this.messageID = generateMessageID();
        this.currentMessageNumber = totalMessages; 
    }
    
    // Auto-Generates a random 10-digit message
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for(int i = 0; i < 10; i++){
            id.append(rand.nextInt(10));
        }
        return id.toString();
    }

    /*
     * Boolean: checkmessageID
     * Makes sure the messageID is 10 characters.
    */
    public boolean checkmessageID(String id){
        return id != null && id.length() == 10;
    }
        
    /*
     * String: checkrecipientCell
     * Checks if the entered number is a valid South African number
    */
    public String checkrecipientCell(String cellNumber){
        //Regex conditions for the number
        /*
         * ^(\\+27) = forces the user to start with '+27'
         * 6-8 = ensures that the next digit is a valid number 
         * [0-9]{8}$ = ensure 8 exact digits follow
        */
        String regex = "^(\\+27)[6-8][0-9]{8}$";   
        /*
           Source: O’Reilly, 2012. Regular Expressions Cookbook. [online] 
           Available at: <https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html>
           Search Query: Regex cookbook examples
           Date Accessed: 07 April 2026
           Purpose: Advanced regex patterns for string matching.
           Modification: Simplified complex patterns for POE requirements
        */        
        if (cellNumber.matches(regex)){
            this.recipientCell = cellNumber;
            return "Cellphone number successfully captured.";  
        } else {
            return "Incorrect cellphone format! please try again and make sure your have the correct format and your internation code is a South African number starting with +27.";
        }
    }
    
    /*
     * String: checkMessageLength
     * Make sures the message is not more than 250 characters.
    */   
    public String checkMessageLength(String message) {
        if (message.length() <= 250) {
            this.messageText = message;
            
            // Generates a hash once a valid message is stored
            this.messageHash = createMessageHash(); 
            return "Message ready to send.";
        } else {
            int overBy = message.length() - 250;
            return "Message exceeding 250 characters by " + overBy + "; please reduce the size.";
        }
    }
    
    /*
     * String: createMessageHash
    */   
    public String createMessageHash() {
        /*
           Source: Oracle, 2019. String (Java SE 11 & JDK 11 API Documentation). [online] 
           Available at: <https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html>
           Search Query: Java String class API
           Date Accessed: 15 May 2026
           Purpose: To understand methods and properties of String Class for message handling.
           Modification: Applied the document insights to create a custom message hash function using substring and split operations.
        */
        
        // Extract first 2 digits of the ID
        String firstTwoDigits = this.messageID.substring(0,2);
        
        // Extract first and last words
        String[] words = this.messageText.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        
        // Handle 1 word messages to avoid duplication
        String wordCombine = (words.length == 1) ? firstWord : firstWord + lastWord;
        
        return firstTwoDigits + ":" + this.currentMessageNumber + ":" + wordCombine;
    }

    /*
     * String: SentMessage(int choice)
     * Allows the user to chose if they want to send, cancel or store the message.
    */       
    public String SentMessage(int choice) {
        /*
           Source: Oracle, 2025. Operators (Java™ Tutorials: Learning the Java Language).[online] 
           Available at: <https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op1.html>
           Search Query: Java operators tutorial
           Date Accessed: 16 May 2026
           Purpose: Learn the use of arithmetic and relational operators in Java for using expressions
           Modification: Applied the concepts to customize calculations 
        */ 
        
        /*
           Source: Oracle, 2025. The switch Statement (Java™ Tutorials: Learning the Java Language). [online] 
           Available at: <https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html>
           Search Query: Java switch statement tutorial
           Date Accessed: 16 May 2026
           Purpose: Understanding syntax and usage of switch statement for message handling logic.
           Modification: Used an example structure to increase counters and return custom messages in the SentMessage method.
        */         
        
        switch (choice) {
            case 1:                
                totalMessages++;
                return "Message successfully sent."; 
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";                        
            default:   
                return "Invalid selection, try again";
        }
    }
    
    /*
     * String: printMessages()
     * Returns a formatted string with the full details of the message.
    */       
    public String printMessages() {
        return "Message ID: " + this.messageID + "\n" +
               "Message Hash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipientCell + "\n" +   
               "Message:" + this.messageText + "\n";        
    }
 
    /*
     * Int: returnTotalMessages()
     * Returns the total numbers of messages sent.
    */       
    public int returnTotalMessages() {
        return totalMessages;
    }
    
    /*
     * storeMessage() method
     * 
    */ 
    public void storeMessage() {
       String jsonFormat = "{\n" +
        "  \"MessageID\": \"" + this.messageID + "\",\n" +
        "  \"MessageHash\": \"" + this.messageHash + "\",\n" +
        "  \"Recipient\": \"" + this.recipientCell + "\",\n" +
        "  \"Message\": \"" + this.messageText + "\"\n" + "}\n";  
          
        /*
           Source: Oracle, 2021, Class FileWriter (Java Platform SE 8). [online] 
           Available at: <https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html>
           Search Query: Java FileWriter class documentation
           Date Accessed: 18 May 2026
           Purpose: Used to understand how to create and write text files using FileWriter class in Java.
           Modification: Used the examples to implement file output for storing message log.
       */ 

       /*
           Source: Oracle, 2023, Catching and Handling Exceptions (Java™ Tutorials: Essential Classes). [online] 
           Available at: <https://docs.oracle.com/javase/tutorial/essential/exceptions/handling.html>
           Search Query: Java exception handling tutorial 
           Date Accessed: 18 May 2026
           Purpose: Used to understand try - catch and practice it for handling runtime errors in Java.
           Modification: Implemented the tutorial example to manage invalid user input.
       */     
    
    // Used to save data
    try{
        FileWriter writer = new FileWriter("StoredMessages.json" , true);
        writer.write(jsonFormat);
        writer.close();
    } catch (IOException e) {
        System.out.println("An Error occurred, please try again");
     }   
    }
    
    //------------------ GETTERS  ------------------ 
    //Uses for JunitTest
    public String getmessageHash() {
        return messageHash;
    } 
    
    public String getMessageID() {
        return messageID;
    }  
    
    /*
     * -- Part 3: Method to add a message to the parallel arrays --
     * Method to add a message to the parallel arrays
    */ 
    public void addMockData(String id, String hash, String recipient, String text, String status) {
        if(messageCount < 100) {
            messageIDs[messageCount] = id;
            messageHashes[messageCount] = hash;
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
                    // once on  message text, the object is complete
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
         Modification: Adapted examples to implement logic like looping through arrays and control repeated execution of statements.
        */                 
        for (int i = 0; i < messageCount; i++) {
            if (statuses[i] != null && statuses[i].equals("Stored")){
                System.out.println("System Developer ->" + recipients[i]);
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
            if (messageIDs[i] != null && messageIDs[i].equals(searchID)){
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
            if (messageHashes[i] != null && messageHashes[i].equals(searchHash)) {
                String text = messagesText[i];
                messageIDs[i] = null;
                messageHashes[i] = null;
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
            if (messageIDs[i] != null) {
                System.out.println("Status:" + statuses[i]);
                System.out.println("Hash:" + messageHashes[i]);
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

/* 
================================================================================
REFERENCE LIST
================================================================================
W3Schools, 2023. Java Files Create. [online] 
Available at:https://www.w3schools.com/java/java_files_create.asp

GeeksforGeeks, 2024. Java String Manipulation: Best Practices for Clean Code. [online] 
Available at: https://www.geeksforgeeks.org/java/java-string-manipulation-best-practices-for-clean-code/

TutorialsPoint, 2021. Java Regular Expressions. [Online]
Available at: https://www.tutorialspoint.com/java/java_regular_expressions.htm

Oracle, 2023, Catching and Handling Exceptions (Java™ Tutorials: Essential Classes). [online]
Available at: https://docs.oracle.com/javase/tutorial/essential/exceptions/handling.html

Oracle, 2021, Class FileWriter (Java Platform SE 8). [online] 
Available at: https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html

Oracle, 2025. The switch Statement (Java™ Tutorials: Learning the Java Language). [online] 
Available at: <https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html>

Oracle, 2025. Operators (Java™ Tutorials: Learning the Java Language).[online] 
Available at: <https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op1.html

Oracle, 2019. String (Java SE 11 & JDK 11 API Documentation). [online] 
Available at: <https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html>

O’Reilly, 2012. Regular Expressions Cookbook. [online] 
Available at: <https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html>
================================================================================
*/