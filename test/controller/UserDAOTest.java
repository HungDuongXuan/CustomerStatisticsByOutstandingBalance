/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package controller;

import model.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author phuongdt
 */
public class UserDAOTest {
    
    public UserDAOTest() {
    }

    /**
     * Test of checkLogin method, of class UserDAO.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        UserDAO instance = new UserDAO();
        boolean expResult = true;
        boolean result = instance.checkLogin(user);
        assertEquals(true, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
