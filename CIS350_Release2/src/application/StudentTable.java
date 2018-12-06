package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Comparator;

import javax.swing.JOptionPane;


/*************************************************************************
 * The StudentTable class creates the structure that JTable will follow
 * to implement methods and functions on the student database. This
 * currently includes addition, deletion, editing, saving and loading, 
 * and simple sorting.
 * 
 * @author William Shreeve, Hai Duong, Trung-Vuong Pham
 ************************************************************************/
public class StudentTable {

    /** ArrayList of students. */
	private ArrayList<Student> students;
	
	/*******************************************************************
	 * The default constructor that initializes the list of students
	 * and sets the column names.
	 ******************************************************************/
	public StudentTable() {
		students = new ArrayList<Student>();
	}
	
	/*******************************************************************
	 * Returns the array of students.
	 * 
	 * @return returns ArrayList of students
	 ******************************************************************/
	public ArrayList<Student> returnList() {
		return students;
	}
	
	/*******************************************************************
	 * Gets the row count.
	 * 
	 * @return Returns the number of students
	 ******************************************************************/
	public int getRowCount() {
		return students.size();
	}
	
	
	/*******************************************************************
	 * Replaces student with the parameter student.
	 * 
	 * @param s	Student to replace student with
	 * @param i Index to replace
	 ******************************************************************/
	public void replaceAt(Student s, int i) {
		students.set(i, s);
	}	

	/*******************************************************************
	 * Gets the value at a specific row and column.
	 * 
	 * @param rowIndex the specified row
	 * @param columnIndex the specified column
	 * @return returns the object at the specified row and column
	 ******************************************************************/
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return students.get(rowIndex).getName();
		case 1:
			return students.get(rowIndex).getMajor();
		case 2:
			return students.get(rowIndex).getStanding();
		case 3:
			return students.get(rowIndex).getGNum();
		default:
		    break;
		}
	return null;
	}
	
	/*******************************************************************
	 * Gets the list of students.
	 * 
	 * @return An ArrayList of all students
	 ******************************************************************/
	public ArrayList<Student> getStudents() {
		return students;
	}
	
	/*******************************************************************
	 * Gets the student at the requested index.
	 * 
	 * @param index the index of the requested student
	 * @return the student at the specified index
	 * @throws IndexOutOfBoundsException When index is out of range 
	 ******************************************************************/
	public Student getAtIndex(int index) 
	        throws IndexOutOfBoundsException {
			return students.get(index);
		
	}
	
	/*******************************************************************
	 * Returns the student at the selected index.
	 * 
	 * @param index	Index selected to retrieve student
	 * @return	Student at the selected index
	 * @throws Exception If index is out of bounds of list
	 ******************************************************************/
	public Student getAt(int index) throws Exception {
		if (index <= students.size() - 1) {
			return students.get(index);
		} else {
			throw new Exception();
		}
	}
	
	/*******************************************************************
	 * Converts a string input into a Student object.
	 * 
	 * @param input The values to be converted into a Student object
	 * @return Student object that holds the same information as the 
	 * passed in values
	 * 
	 * @throws Exception When there are incorrect values
	 ******************************************************************/
	public Student stringToStudent(String input) throws Exception {
		String[] items = input.split(",");
		
		if (items.length == 6) {
			GregorianCalendar date = new GregorianCalendar();
			String[] parsedDate = items[2].split("/");
			date.set(GregorianCalendar.MONTH, Integer.parseInt(
			        parsedDate[0]) - 1);
			date.set(GregorianCalendar.DAY_OF_MONTH, 
			        Integer.parseInt(parsedDate[1]));
			date.set(GregorianCalendar.YEAR, Integer.parseInt(
			        parsedDate[2]));
			
		} else {
			throw new Exception("File is corrupted");
		}
		return null;
	}
	
	/******************************************************************
	 * This method checks that there is no duplicate G-Numbers and that
	 * it starts with a G.
	 * 
	 * @param s Student to check G-number
	 * @return True if valid, false if invalid.
	 ******************************************************************/
	public boolean isValidGNum(Student s) {
		for (Student s1: students) {
			if (s1.getGNum().equals(s.getGNum())) {
				return false;
			}
		}
		return true;
	}
	
	/*******************************************************************
	 * This method sorts the students by name using a sort class called 
	 * SortByName().
	 ******************************************************************/
	public void sortByName() {
		Collections.sort(getStudents(), new SortByName());
	}
	
	/*******************************************************************
	 * This method sorts the students by name.
	 * 
	 * @return The alphabetical order between two  names
	 ******************************************************************/
	private class SortByName implements Comparator<Student> {
		@Override
		public int compare(Student o1, Student o2) {
		 return o1.getName().compareTo(o2.getName());
		}
	}
	
	/*******************************************************************
	 * This method sorts the standings of the students, 
	 * using an anonymous class in Java.
	 ******************************************************************/
	public void sortByStanding() {
		Collections.sort(getStudents(), new Comparator<Student>() {
			   public int compare(Student o1, Student o2) {
				   return o1.getStanding().compareTo(
				       o2.getStanding());
			   } });
	} 
	
	/*******************************************************************
     * Saves the list of students as a serialized file.
     * 
     * @param filename name of file to save to 
     ******************************************************************/
    public void saveAsSerialized(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            } catch (IOException e) {
                e.printStackTrace();
        }
        
    }
    
    /*******************************************************************
     * Loads list of students from serialized file.
     * 
     * @param filename Name of file to load from
     ******************************************************************/
    @SuppressWarnings("unchecked")
    public void loadFromSerialized(String filename) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            students = (ArrayList<Student>) in.readObject();
            in.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Invalid File!");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Invalid File!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid File!");
        }    
    }
}
