package com.mycompany.st10483276poe_26;

public class Login {
    //Used to store the user data in memory once entered
    private String firstName;
    private String lastName;
    private String cellNumber;
    private String Username;
    private String Password;
    
    public boolean checkUserName(String Username) {
        return Username.contains("_") && Username.length()<=5;
    }
    
    public boolean checkPassword(String Password){
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_\\-*~]).{8,}$";
        return Password.matches(regex);
    }
    
    public boolean checkCellPhoneNumber(String cellNumber){
        String regex = "^(\\\\+27 |0)[6-8][0-9]{8}$";
        return cellNumber.matches(regex);
    }
    
    public String registerUser(String Username, String Password, String firstName, String cellNumber, String cellNumber1){
        if (!checkUserName(Username)) {
            return "Incorrect username format! please try again and make sure your username has an underscore '_' and charcaters are not longer than 5.";
        }
        if (!checkPassword(Password)) {
            return "Incorrect password format! please try again and make sure your password has the at least 8 characters, a Captial and small letter, a number and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Incorrect cellphone format! please try again and make sure your have the correct format and your internation code is a South African number starting with +27.";
        }
      
        this.Username = Username;
        this.Password = Password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellNumber = cellNumber;
        
        return "Registration process complete, The Username, Password, and Cell phone have been captured";     
    }
    
    public boolean loginUser(String enteredUsername, String enteredPassword){
        if (Username == null || Password == null) {
            return false;
        }
        return enteredUsername.equals(Username) && enteredPassword.equals(Password);
    }
    
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
