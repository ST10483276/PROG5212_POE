package com.mycompany.st10483276poe_26;

public class Login {
    //Used to store the user data in memory once entered
    //Made use of private string, ensuring enclosure, this prevents changes to these variables from outside the class
    /*
       Source: GeeksforGeeks, 2026. Java Access Modifiers. [online] 
       Available at: <https://www.geeksforgeeks.org/java/access-modifiers-java/>
       Search Query: Access modifiers in Java
       Date Accessed: 29 March 2026
       Purpose: learn more about modifiers.
       Modification: integrated into POE rubric compliance.
    */
    private String firstName;
    private String lastName;
    private String cellNumber;
    private String Username;
    private String Password;
    
    //------------------ Validation Method ------------------
    public boolean checkUserName(String Username) { //Checks if the username entered meets the required format
        /*
        *.contains("_") checks for the madatory underscore '_'
        *.length()<=5 checks if the string is less than or equal to 5
        */
        return Username.contains("_") && Username.length()<=5; 
    }
    
    public boolean checkPassword(String Password){ //Checks if the password entered meets the required format
        //Regex conditions for the number
        /*
        * (?=.*[A-Z]) = At least one uppercase letter
        * (?=.*\\d) = At least one digit 
        * (?=.*[@#$%^&+=!_\\-*~]) = At least one character 
        * {8,} = Minimum of 8 characters
        */
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_\\-*~]).{8,}$"; 
        return Password.matches(regex); 
        /*
           Source: Tutorialspoint, 2012. Java Regular Expressions. [online]
           Available at: <https://www.tutorialspoint.com/java/java_regular_expressions.htm>
           Search Query: Java regex tutorial
           Date Accessed: 07 April 2026
           Purpose: Learned about regex syntax for validation methods.
           Modification:  Adapted examples for assignment-specific test cases.
        */
    }
    
    public boolean checkCellPhoneNumber(String cellNumber){ // Checks if the entered number is a valid South African number
        //Regex conditions for the number
        /*
        * ^(\\+27) = forces the user to start with '+27'
        * 6-8 = ensures that the next digit is a valid number 
        * [0-9]{8}$ = ensure 8 exact digits follow
        */
        String regex = "^(\\+27)[6-8][0-9]{8}$";       
        return cellNumber.matches(regex);
        /*
           Source: O’Reilly, 2012. Regular Expressions Cookbook. [online] 
           Available at: <https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch04s03.html>
           Search Query: Regex cookbook examples
           Date Accessed: 07 April 2026
           Purpose: Advanced regex patterns for string matching.
           Modification: Simplified complex patterns for POE requirements
        */
    }
    
    //------------------ Registration Method ------------------
    /*
     * Attempt to register the user by passing the inputs through the validation method 
     * Returns a specific error message of a check fails, or passes all the conditions and give a success message  
     */
    public String registerUser(String Username, String Password, String cellNumber){
        if (!checkUserName(Username)) {
            //Error handling message, if username format is false, it stop and returns an error message 
            return "Incorrect username format! please try again and make sure your username has an underscore '_' and charcaters are not longer than 5."; //Error handling message
        }
        if (!checkPassword(Password)) {
            //Error handling message, if Password format is false, it stop and returns an error message 
            return "Incorrect password format! please try again and make sure your password has the at least 8 characters, a Captial and small letter, a number and a special character."; //Error handling message
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            //Error handling message, if cellphone format is false, it stop and returns an error message 
            return "Incorrect cellphone format! please try again and make sure your have the correct format and your internation code is a South African number starting with +27."; //Error handling message
        }
      
        //If all 'if' statements pass the met requirements, the data is considered valid
        this.Username = Username;
        this.Password = Password;
        this.cellNumber = cellNumber;
        
        return "Registration process complete, The Username, Password, and Cell phone have been successfully captured";     
    }
    
    //------------------ Login and Authentication methods  ------------------
    public boolean loginUser(String enteredUsername, String enteredPassword){
        return enteredUsername.equals(Username) && enteredPassword.equals(Password); //uses .equals() to compare exact content of the Strings
    }
    
    //Set's the user first name in memory
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    //Set's the user last name in memory
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //------------------ Status output ------------------
    //return a final message based on the loginUser boolean and checks if it passes or fail 
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again";
        } else {
            return "Username or password is incorrect, please try again.";
        }
    }
}
