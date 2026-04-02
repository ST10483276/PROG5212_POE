package com.mycompany.st10483276poe_26;

import java.util.Scanner;

public class ST10483276POE_26 {

    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in); 
     Login loginSystem = new Login(); // instantiate for Login.java class
         
     System.out.println("----------WELCOME TO THE CHAT APP----------");
     
     System.out.println("Enter your First Name:"); //Output message for the first name
     String firstName = scanner.nextLine(); 
     
     System.out.println("Enter your Last Name:"); //Output message for the surname 
     String lastName = scanner.nextLine();
     
     System.out.println("Enter in your Cellphone Number (MUST BE A SOUTH AFRICAN NUMBER '+27607740545'):"); //Output message for the cellphone number 
     String cellNumber = scanner.nextLine();
     
     System.out.println("Create a Username(<= 5 CHAR & MANDATORY '_'):"); //Output message for the username
     String Username = scanner.nextLine();
     
     System.out.println("Create a password (MIN 8 CHAR, 1 UPPERCARE 1 LOWERCASE & 1 SPECIAL CHAR):"); //Output message for the password
     String Password = scanner.nextLine();
     
     String registrationMessage = loginSystem.registerUser(Username, Password, firstName, lastName, cellNumber);
     
     System.out.println("\n--- Status ---");
     System.out.println(registrationMessage);
     
     //ONLY WORKS IF LOGIN WAS SUCCESSFUL
     if (registrationMessage.contains("Successful")){
        System.out.println("\n=== Login ---");{
        
        System.out.println("Enter Username: ");
        String loginUser = scanner.nextLine();
        
        System.out.println("Enter your Password: ");
        String loginPass = scanner.nextLine();
        
        boolean isLogged = loginSystem.loginUser(loginUser, loginPass);
        String loginStatusMessage = loginSystem.returnLoginStatus(isLogged); 
        
        System.out.println("\n--- Login Status ---");
        System.out.println(loginStatusMessage); 
    }
        
     scanner.close();
    }
   }
}
