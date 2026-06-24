package com.mycompany.st10483276poe_26;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class LoginTest {
    
 Login loginSystem = new Login(); //Instantiate the Login class to access the methods needed for the testing
 
    @Before
    public void setUp() {
       loginSystem.setFirstName("Kyle");
       loginSystem.setLastName("Doe");
    }
 //=====================================================
 // Table 1: assertEquals, Testing exact string messages
 //=====================================================  
    /*
    * Test to verify if a correct username format is used 
    * a successful message alert will appear
    */
    @Test
    public void testUsernameCorrectlyFormatted_Message() {
        String expected = "Username registered successfully.";
        String actual = loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }
    
    /*
    * Test to verify if an incorrect username format is used (more than 5 chatacters or no underscore was used '_') 
    * an error message alert will appear regarding the username format being incorrect
    */
    @Test
    public void testUsernameIncorrectlyFormatted_Message() {
        String expected = "Incorrect username format! please try again and make sure your username has an underscore '_' and charcaters are not longer than 5.";
        String actual = loginSystem.registerUser("kyle!!!!", "Ch&&sec@ke99!", "+27838968976");
    }

    /*
    * Test to verify if a password meets all the conditions required 
    * (+8 characters, 1 uppercase, 1 lowercase, 1 digit and 1 special character) allows successful registration
    */
    @Test
    public void testPasswordMeetsComplexity_Message() {
        String expected = "Username registered successfully.";
        String actual = loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    /*
    * Test to verify if a password fails to meet all the required conditions
    * an error message alert will appear regarding the password format being incorrect
    */
    @Test
    public void testPasswordDoesNotMeetComplexity_Message() {
        String expected = "Incorrect password format! please try again and make sure your password has the at least 8 characters, a Captial and small letter, a number and a special character.";
        String actual = loginSystem.registerUser("Kyl_1", "password", "+27838968976");
    }
    
    /*
    * Test to verify if a cellphone number meet the required conditions (South African formatat +27)est 
    * a successful message alert will appear
    */
    @Test
    public void testCellPhoneCorrectlyFormatted_Message() {
        String expected = "Username registered successfully.";
        String actual = loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "08966553");
    }
    
    /*
    * Test to verify that the systems returns a correct and personalized
      welcome message upon a successfull login 
    */
    @Test
    public void testLoginSuccessful_Message() {
        loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean isLoginValid = loginSystem.loginUser("kyl_1", "Ch&&sec@ke99!");
        
        String expected = "Welcome Kyle , Doe it is great to see you again";
        String actual = loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
    }
    
    /*
    * Test to verify that an incorrect password attempt was made resulting in a failed login attempt
    * an error message alert will appear regarding a failed login attempt with an icorrect password input
    */
    @Test
    public void testLoginFailed_Message() {
        loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean isLoginValid = loginSystem.loginUser("kyl_1", "wrongpassword");
        
        String expected = "Username or password is incorrect, please try again.";
        String actual = loginSystem.returnLoginStatus(isLoginValid);
    }

 //=============================================================
 // Table 2: assertTrue & assertFalse, Testing the boolean logic
 //=============================================================
 
 //------------------ Username Test ------------------
 
    /*
    * Tests if a correct username format returns true
    * Condition: An underscore '_' & no more than 5 characters 
    */
    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(loginSystem.checkUserName("kyl_1"));
    }
    
    /*
    * Tests if an incorrect username format returns false
    * Condition: More than 5 characters or does not contain an underscore '_'
    */
    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(loginSystem.checkUserName("kyle!!!!"));
    }

 //------------------ Password Test ------------------  
    
    /*
    * Tests if a password meets all the conditions returns true
    * Condition: Minimum 8 characters, 1 Capital letter, 1 number & 1 special character
    */   
    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(loginSystem.checkPassword("Ch&&sec@ke99"));
    }

    /*
    * Tests if a password does not satisfy all the conditions returns false
    */   
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(loginSystem.checkPassword("password")); 
    }

 //------------------Cellphone Test ------------------
    
    /*
    * Tests if a correctly formatted South African phone number returns true
    * Condition: Must be +27 and is the exact valid lenght 
    */   
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(loginSystem.checkCellPhoneNumber("+27838968976"));
    }

    /*
    * Tests if an incorrect phone number format returns false
    */ 
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(loginSystem.checkCellPhoneNumber("08966553"));
    }

 //------------------ Login Authentication Test ------------------   
    
    /*
    * Tests if the correct login credentials returns true 
    */   
    @Test
    public void testLoginSuccessful() {
        //Register a user so the system has data in the memory
        loginSystem.setFirstName("Kyle");
        loginSystem.setLastName("Doe");
        loginSystem.registerUser("Kyl_1", "Ch&&sec@ke99!", "+27838968976");
        
        //Attempt to log in with the exact credentials
        assertTrue(loginSystem.loginUser("Kyl_1", "Ch&&sec@ke99!"));
    }
}

/*
   Source: Programmingw/ProfessorA, 2023. Java Unit Testing with JUnit in Netbeans. [Video]
   Available at: <https://www.youtube.com/watch?v=MOhjM2SXZl0>
   Search Query: Java Unit Testing with JUnit in Netbeans
   Date Accessed: 10 April 2026
   Purpose: Demonstrated practical JUnit testing setup in NetBeans.
   Modification: Adapted video examples into 14 POE-specific unit tests.
*/