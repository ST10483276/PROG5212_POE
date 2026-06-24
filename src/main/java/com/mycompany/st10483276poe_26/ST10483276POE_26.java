package com.mycompany.st10483276poe_26;

import java.util.Scanner; //Scanner input to store the input

public class ST10483276POE_26 {

    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in); //Instantiate, used to read input from the keyboard
     Login loginSystem = new Login(); //Instantiate for Login.java class to access the methods
         
     System.out.println("=================== WELCOME TO THE CHAT APP ===================");

     //------------------ Phase 1: Capturing the user's details ------------------
     
     System.out.println("Enter your First Name:"); //Output message for the first name
     String firstName = scanner.nextLine(); //Captures the user's input (First name)
     loginSystem.setFirstName(firstName); //Passed the captured name to the Login class
     
     System.out.println("Enter your Last Name:"); //Output message for the surname 
     String lastName = scanner.nextLine(); //Captures the user's input (Surname)
     loginSystem.setLastName(lastName); //Passed the captured surname to the Login class
     
    /* Source: GeeksforGeeks, 2026. While Loop in Programming. [online]
     Available at: <https://www.geeksforgeeks.org/dsa/while-loop-in-programming/>
     Search Query: While loop in Java programming
     Date Accessed: 12 April 2026
     Purpose: Used to reinforce understanding of while loop syntax and behavior.
     Modification: Adapted loop examples into POE-specific iterative test cases.
    */
    
     //Requests the user to enter the cellphone until the format is correct
     String cellNumber = "";
     boolean validCell = false;
      while (!validCell) {
        System.out.println("Enter in your Cellphone Number (MUST BE A SOUTH AFRICAN NUMBER STARTING WITH '+27'):"); //Output message for the cellphone number 
        cellNumber = scanner.nextLine(); //Captures the user's input (Username)
            
        // if the check for the Cellphone is true, the loop will end
        if (loginSystem.checkCellPhoneNumber(cellNumber)) {
          System.out.println("Cellphone Successfully saved"); 
          validCell = true; 
          }else {
          System.out.println("Incorrect cellphone format! please try again and make sure your have the correct format and your internation code is a South African number starting with +27.");
          }
        }
       
     //Requests the user to enter the Username until the format is correct
     String Username = "";
     boolean validUser = false;
      while (!validUser) {
        System.out.print("Create a Username(<= 5 CHAR & MANDATORY '_'):"); //Output message for the username
        Username = scanner.nextLine(); //Captures the user's input (Username)
            
        // if the check for the username is true, the loop will end
        if (loginSystem.checkUserName(Username)) {
          System.out.println("Username Successfully saved"); 
          validUser = true;
          }else {
          System.out.println("Incorrect username format! please try again and make sure your username has an underscore '_' and charcaters are not longer than 5.");
          }
        }
      
     //Requests the user to enter the Password until the format is correct
     String Password = "";
     boolean validPass = false;
      while (!validPass) {
        System.out.println("Create a password (MIN 8 CHAR, 1 UPPERCARE 1 LOWERCASE & 1 SPECIAL CHAR):"); //Output message for the password
        Password = scanner.nextLine(); //Captures the user's input (password)
            
        // if the check for the password is true, the loop will end
        if (loginSystem.checkPassword(Password)) {
          System.out.println("Password Successfully saved"); 
          validPass = true;
          }else {
          System.out.println("Incorrect password format! please try again and make sure your password has the at least 8 characters, a Captial and small letter, a number and a special character.");
          }
        }     
          
     //------------------ Phase 2: Registration & Validation ------------------
     
     String registrationMessage = loginSystem.registerUser(Username, Password, cellNumber); //Passes credentials to registerUser method to check if all conditions are satified 
     
     System.out.println("\n---------- Status ----------"); //Output status for the registration 
     System.out.println(registrationMessage);

     //------------------ Phase 3: Login process ------------------
     
        System.out.println("\n---------- Login ----------");
        
        System.out.println("Enter Username: "); //Output message for the username
        String loginUser = scanner.nextLine(); //Captures the user's login attempt for the username
        
        System.out.println("Enter your Password: "); //Output message for the password
        String loginPass = scanner.nextLine(); //Captures the user's login attempt for the password
        
        boolean isLogged = loginSystem.loginUser(loginUser, loginPass); //Calls the loginUser method to confirm if the entered details match the stored details
        String loginStatusMessage = loginSystem.returnLoginStatus(isLogged); //Obtains the final welcome or rejection message based in the boolean result
        
        System.out.println("\n---------- Login Status ----------");
        System.out.println(loginStatusMessage); 

     //------------------ Phase 4: QuickChat Menu (Part 2) ------------------
     
        if (isLogged) {    
            Message manager = new Message();
            manager.loadStoredMessagesFromJson();
            System.out.println("\n =================== Welcome to QuickChat =================== ");
            boolean running = true;
            
            while (running) {
                System.out.println("\nPlease Choose an option:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                //------------------ Part 3: 4th option used to access the menu display 
                System.out.println("4) Stored Message Report"); 
                
                /*
                   Source: O’Reilly, 2025. Class Integer (Java Platform SE 8). [online] 
                   Available at: < https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html>
                   Search Query: Java Integer class 
                   Date Accessed: 19 April 2026
                   Purpose: understand constants, functionality and method for converting and parsing integer values.
                   Modification: Used the example to impliment integer parsing and value conversion
                */   
                
                /*
                   Source: GeeksforGeeks, 2026. Java try-catch block. [online] 
                   Available at: < https://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html>
                   Search Query: Java try and catch  
                   Date Accessed: 24 June 2026
                   Purpose: Used to understand the syntax and usage of try-catch blocks in java for handling.
                   Modification: Used examples to manage runtime errors in code to ensure stability when an invalid input/unexpected condition occurs. 
                */   
                int menuChoice = 0;
                    try {
                        menuChoice = Integer.parseInt(scanner.nextLine());   
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please type a number");
                        continue; // Loops back to the start of the menu
                    }

                switch (menuChoice) {
                    case 1:
                        System.out.println("How many messages do you wish to enter?");
                        int numMessages = Integer.parseInt(scanner.nextLine());
                        
                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\n-- Message " + (i + 1) + " of " + numMessages + " ---");
                            Message currentMessage = new Message();
                            
                            // Delcares the variables outside the loop
                            String recCell = "";
                            String msgText = "";
                                                        
                            // Cellphone Loop for Message Recipient
                            boolean msgCellValid = false;
                            while (!msgCellValid) {
                                System.out.println("Enter Recipient Cellphone Number:");
                                recCell = scanner.nextLine();
                                String cellResult = currentMessage.checkrecipientCell(recCell);
                                System.out.println(cellResult);
                                
                                if (cellResult.equals("Cellphone number successfully captured.")) {
                                    msgCellValid = true;
                                }
                            }
                            
                            // Text Length Loop
                            boolean msgTextValid = false;
                            while (!msgTextValid) {
                                System.out.println("Enter in your message (MAX 250 CHARACTERS):");
                                msgText = scanner.nextLine();                                
                                String textResult = currentMessage.checkMessageLength(msgText);
                                System.out.println(textResult);                                
                                if (textResult.equals("Message ready to send.")) {
                                    msgTextValid = true;
                                }
                            }
                            
                            // Action Menu
                            System.out.println("\nSelect an action for this message:");
                            System.out.println("1) Send Message");
                            System.out.println("2) Discard Message");
                            System.out.println("3) Store Message to send later");
                            
                            int actionChoice = Integer.parseInt(scanner.nextLine());
                            System.out.println(currentMessage.SentMessage(actionChoice));
                            
                            // ------------------ Save to arrays ------------------
                            String status = (actionChoice == 1) ? "Sent" : (actionChoice == 2) ? "Disregarded" : "Stored";
                            manager.addMockData(currentMessage.getMessageID(), currentMessage.getmessageHash(), recCell, msgText, status);
                            
                            // Output details
                            System.out.println("\nMessage Details:");
                            System.out.println(currentMessage.printMessages());
                        }
                        break;
                        
                    case 2:
                        System.out.println("\n--- Recently Sent Messages ---"); //The updated feature 
                        String sentList = manager.getAllSentMessages(); // Calls the method that was built for JUnit testing 
                        
                        // a quick check if the array is empty
                        if (sentList.isEmpty()) {
                            System.out.println("No messages have been sent during this session");
                        } else {
                            System.out.println(sentList);
                        }
                        break;
                        
                    case 3: 
                        // Instantiate a blank message to call the total counter
                        Message temp = new Message();
                        System.out.println("\n Total messages sent: " + temp.returnTotalMessages());
                        System.out.println("Goodbye");
                        running = false;
                        break;
                        
                    case 4:
                        // The report Menu
                        System.out.println("\n--- Stored Messages Options ---");
                        System.out.println("a. Display sender and recipient of all stored messages");
                        System.out.println("b. Display the longest message");
                        System.out.println("c. Search for message by ID");
                        System.out.println("d. Search messages by Recipient");
                        System.out.println("e. Delete a message");
                        System.out.println("f. Display full report");
                        System.out.println("Please select an option (a-f):");
                        
                        String subChoice = scanner.nextLine().toLowerCase();
                        
                        switch (subChoice) {
                        /*
                           Source: Bierman, G. & Lahoda, J., 2019. JEP 361: Switch Expressions - OpenJDK. [online] 
                           Available at: < https://openjdk.org/jeps/361>
                           Search Query: Java switch expression class 
                           Date Accessed: 22 June 2026
                           Purpose: understand switch constructors in Java.
                           Modification: Improved branching logic in assignment code by using values from switch expressions and replacing complex arrow labels with clearer ones.
                        */                              
                            
                            case "a":
                                manager.displaySenderAndRecipient();
                                break;
                            case "b":
                                System.out.println("Longest Message:"+ manager.getLongestMessage());
                                break;
                            case "c":
                                System.out.println("Enter Message ID to search:");
                                String searchID = scanner.nextLine();
                                System.out.println("Result:" + manager.searchMessageByID(searchID));
                                break;
                            case "d":
                                System.out.println("Enter Recipient Cell to start the search:");
                                String searchRec = scanner.nextLine();
                                System.out.println("Results: \n" + manager.searchByRecipient(searchRec));
                                break; 
                            case "e":
                                System.out.println("Enter Messaage Hash to delete ");
                                String searchHash = scanner.nextLine();
                                System.out.println(manager.deleteMessageByHash(searchHash));                                                                
                                break;
                            case "f":                                
                                manager.displayReport();
                                break;  
                            default:
                                System.out.println("Invalid selection");                                                            
                            }  
                            break; 
                            
                    default:
                        // Error handling message
                        System.out.println("Invalid selection, please select 1, 2,3, or 4.");
                }
            }
        } else {
            System.out.println("Login failed. Closing application.");
        }

     //Closes the scanner   
     scanner.close();
    }
}
/*
   Source: QuickBlox, 2023. Beginner’s Guide to Chat App Architecture. [online] 
   Available at: <https://quickblox.com/blog/beginners-guide-to-chat-app-architecture/>
   Search Query: Chat app architecture basics
   Date Accessed: 11 March 2026
   Purpose: Used to understand chat application design principles.
   Modification: Applied concepts to POE networking rubric.
*/

/* ================================================================================
REFERENCE LIST (Harvard - Anglia Style)
================================================================================
GeeksforGeeks, 2026. Access Modifiers in Java. [Online] 
Available at: https://www.geeksforgeeks.org/java/access-modifiers-java/

GeeksforGeeks, n.d. String Manipulation in Java. [Online]
Available at: https://www.geeksforgeeks.org/string-manipulation-in-java/

GeeksforGeeks, n.d. While Loop in Programming. [Online] 
Available at: https://www.geeksforgeeks.org/dsa/while-loop-in-programming/ 

Goyvaerts, J. & Levithan, C., 2012. Regular Expressions Cookbook. 2nd ed. O'Reilly Media. [Online]
Available at: https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html

Programiz, 2026. Java Access Modifiers (With Examples). [Online]
Available at: https://www.programiz.com/java-programming/access-modifiers

Programmingw/ProfessorA, 2023. Automated Testing using GitHub Actions. [Video] 
Available at: https://youtu.be/ozOQd5H4Onk

Programmingw/ProfessorA, 2023. Java Unit Testing with JUnit in Netbeans. [Video] 
Available at: https://www.youtube.com/watch?v=MOhjM2SXZl0

QuickBlox, n.d. Beginner's Guide to Chat App Architecture. [Online]
Available at: https://quickblox.com/blog/beginners-guide-to-chat-app-architecture/

TutorialsPoint, n.d. Java - Random class. [Online]
Available at: https://www.tutorialspoint.com/java/java_random_class.htm

TutorialsPoint, n.d. Java Regular Expressions. [Online]
Available at: https://www.tutorialspoint.com/java/java_regular_expressions.htm

W3Schools, n.d. Java Files - Create and Write. [Online]
Available at: https://www.w3schools.com/java/java_files_create.asp

W3Schools, 2026. Java Modifiers. [Online]
Available at: https://www.w3schools.com/java/java_modifiers.asp

W3Schools, n.d. Java Regular Expressions. [Online]
Available at: https://www.w3schools.com/java/java_regex.asp

Oracle, 2025. Arrays (Java™ Tutorials: Learning the Java Language).[Online]
Available at: https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html

================================================================================
*/