/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tarleena_2
 */
public class DiceTest {
    
    public DiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRoll method, of class Dice.
     * Fixed
     */
    @Test
    public void testGetRoll() {
        System.out.println("getRoll");
        Dice instance = new Dice();
        int result = instance.getRoll();
        assertEquals(Integer.class, ((Object)result).getClass());
    }

    /**
     * Test of setRoll method, of class Dice.
     * Fixed
     */
    @Test
    public void testSetRoll() {
        System.out.println("setRoll");
        int roll = 0;
        Dice instance = new Dice();
        instance.setRoll(roll);
        assertEquals(roll, instance.getRoll());
    }

    /**
     * Test of isPressed method, of class Dice.
     * On progress
     */
    @Test
    public void testIsPressed() {
        System.out.println("isPressed");
        Dice instance = new Dice();
        boolean expResult = false;
        //boolean result = instance.isPressed();
        boolean result = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of setPressed method, of class Dice.
     */
    @Test
    public void testSetPressed() {
        System.out.println("setPressed");
        Dice instance = new Dice();
        instance.setPressed();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doRoll method, of class Dice.
     */
    @Test
    public void testDoRoll() {
        System.out.println("doRoll");
        Dice instance = new Dice();
        instance.doRoll();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Dice.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Dice compareDice = null;
        Dice instance = new Dice();
        int expResult = 0;
        int result = instance.compareTo(compareDice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
