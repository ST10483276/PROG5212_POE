package com.mycompany.st10483276poe_26;
// Imports
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MessageManagerTest {

    private MessageManager manager;
    
    @Before
    public void setUp() {
        manager = new MessageManager();
        
        // Mock data: ID, Hash, Recipient, Message, Flag
        manager.addMockData("ID001", "Hash1", "+27834557896", "Did you get the cake?", "Sent"); //Test data 1
        manager.addMockData("ID002", "Hash2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"); //Test data 2
        manager.addMockData("ID003", "Hash3", "+27834484567", "Yohoooo, I am at your gate.", "Disregard"); //Test data 3
        manager.addMockData("0838884567", "Hash4", "0838884567", "It is dinner time !", "Sent"); //Test data 4
        manager.addMockData("ID005", "Hash5", "+27838884567", "Ok, I am leaving without you.", "Stored"); //Test data 5
    }
    
    /*
    * Test 1: Sent messages array correctely populated
    * Expected: "Did you get the cake?" , "It is dinner time!" 
    */
    @Test
    public void testSentMessagesArrayCorrectlyPopulated() { // public method
        String sentMessages = manager.getAllSentMessages();
        assertTrue(sentMessages.contains("Did you get the cake?"));
        assertTrue(sentMessages.contains("It is dinner time !"));
    }
    
    /*
    * Test 2: Display the longest message 
    * Expected: "Where are you? You are late! I have asked you to be on time."
    */
    @Test
    public void testDisplayLongestMessage() { // public method
        String longestMessage = manager.getLongestMessage();
        assertEquals("Where are you? You are late! I have asked you to be on time.", longestMessage);
    }
    
    /*
    * Test 3: Search for messageID
    * Expected: "It is dinner time !" (Using Developer ID 0838884567)
    */
    @Test
    public void testSearchForMessageID() { // public method
        String foundMessage = manager.searchMessageByID("0838884567");
        assertEquals("It is dinner time !", foundMessage);
    }

    /*
    * Test 4: Search all messages regarding a particular recipient 
    * Expected: Returns both messages for +27838884567
    */    
    @Test
    public void testSearchByRecipient() { // public method
        String recipientMessages = manager.searchByRecipient("+27838884567");
        assertTrue(recipientMessages.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(recipientMessages.contains("Ok, I am leaving without you."));        
    }

    /*
    * Test 5: Delete a message using a message hash
    * Expected: Message: "[Message Text]" successfully deleted.
    */    
    @Test
    public void testDeleteMessageUsingHash() { // public method
        // The 2nd message is deleted using the mock hash "Hash2"
        String deleteResult = manager.deleteMessageByHash("Hash2");
        String expectedOutput = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        assertEquals(expectedOutput, deleteResult);
    }
    
}
