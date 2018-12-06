package application;

import static org.junit.Assert.*;

import org.junit.Test;

/***********************************************************************
 * This method is the testing suite for the whole application of the 
 * Student Database.
 * 
 * @author William Shreeve, Hai Duong, and Trung-Vuong Pham
 **********************************************************************/
public class TestStudent {

    /*******************************************************************
     * Tests that setName() and getName() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetNameandGetName() throws Exception {
        Student s = new Student();
        s.setName("TrungVuong Pham");
        assertTrue(s.getName().equals("TrungVuong Pham"));
    }
    
    /*******************************************************************
     * Tests that setName() and getName() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetNameandGetName2() throws Exception {
        Student s = new Student();
        s.setName("Hai Duong");
        assertFalse(s.getName().equals("Jeff Bridges"));
    }
    
    /*******************************************************************
     * Tests that setName() and getName() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetNameandGetName3() throws Exception {
        Student s = new Student();
        s.setName("Jagadeesh Nandigam");
        assertFalse(s.getName().equals(""));
    }
    
    
    /*******************************************************************
     * Tests that setMajor() and getMajor() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetMajorandGetMajor() throws Exception {
        Student s = new Student();
        s.setMajor("Computer Science");
        assertTrue(s.getMajor().equals("Computer Science"));
    }
    
    /*******************************************************************
     * Tests that setMajor() and getMajor() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetMajorandGetMajor2() throws Exception {
        Student s = new Student();
        s.setMajor("Computer Science");
        assertFalse(s.getMajor().equals("Mechanical Engineering"));
    }
    
    /*******************************************************************
     * Tests that setMajor() and getMajor() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetMajorandGetMajor3() throws Exception {
        Student s = new Student();
        s.setMajor("Writing");
        assertFalse(s.getMajor().equals(""));
    }
    
    /*******************************************************************
     * Tests that setStanding() and getStanding() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetStandingandGetStanding() throws Exception {
        Student s = new Student();
        s.setStanding("Freshman");
        assertTrue(s.getStanding().equals("Freshman"));
    }
    
    /*******************************************************************
     * Tests that setStanding() and getStanding() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetStandingandGetStanding2() throws Exception {
        Student s = new Student();
        s.setStanding("Freshman");
        assertFalse(s.getStanding().equals(""));
    }
    
    /*******************************************************************
     * Tests that setStanding() and getStanding() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetStandingandGetStanding3() throws Exception {
        Student s = new Student();
        s.setStanding("Senior");
        assertFalse(s.getStanding().equals(""));
    }
    
    /*******************************************************************
     * Tests that setGPA() and getGPA() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetGPAandGetGPA() throws Exception {
        Student s = new Student();
        s.setGPA("3.5");
        assertTrue(Float.parseFloat(s.getGPA()) == 3.5);
    }
    
    /*******************************************************************
     * Tests that setGPA() and getGPA() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetGPAandGetGPA2() throws Exception {
        Student s = new Student();
        s.setGPA("0");
        assertTrue(Float.parseFloat(s.getGPA()) == 0);
    }
    
    /*******************************************************************
     * Tests that setGPA() and getGPA() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetGPAandGetGPA3() throws Exception {
        Student s = new Student();
        s.setGPA("4.0");
        assertFalse(Float.parseFloat(s.getGPA()) == 0);
    }
    
    /*******************************************************************
     * Tests that setGNum() and getGNum() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetGNumandGetGNum() throws Exception {
        Student s = new Student();
        s.setGNum("G01272550");
        assertTrue(s.getGNum().equals("G01272550"));
    }
    
    /*******************************************************************
     * Tests that setGNum() and getGNum() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetGNumandGetGNum2() throws Exception {
        Student s = new Student();
        s.setGNum("G01272345");
        assertTrue(s.getGNum().equals("G01272345"));
    }
    
    /*******************************************************************
     * Tests that setGNum() and getGNum() methods work. 
     * 
     * @throws Exception Any errors
     ******************************************************************/
    @Test 
    public void testSetGNumandGetGNum3() throws Exception {
        Student s = new Student();
        s.setGNum("G12888906");
        assertFalse(s.getGNum().equals(""));
    }

}
