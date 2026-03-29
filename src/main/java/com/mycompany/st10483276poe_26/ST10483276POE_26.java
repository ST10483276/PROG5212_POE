package com.mycompany.st10483276poe_26;

import java.util.Scanner;

public class ST10483276POE_26 {

    public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in); 
         
     System.out.println("----------WELCOME TO THE CHAT APP----------");
     
     System.out.println("Enter your First Name:"); //Output message for the first name
     String FirstName = scanner.nextLine(); 
     
     System.out.println("Enter your Last Name:"); //Output message for the surname 
     String LastName = scanner.nextLine();
     
     System.out.println("Enter in your Cellphone Number (MUST BE A SOUTH AFRICAN NUMBER '+27607740545'):"); //Output message for the cellphone number 
     String CellNumber = scanner.nextLine();
     
     System.out.println("Create a Username(<= 5 CHAR & MANDATORY '_'):"); //Output message for the username
     String Username = scanner.nextLine();
     
     System.out.println("Create a password (MIN 8 CHAR, 1 UPPERCARE 1 LOWERCASE & 1 SPECIAL CHAR):"); //Output message for the password
     String Password = scanner.nextLine();
    }
}
