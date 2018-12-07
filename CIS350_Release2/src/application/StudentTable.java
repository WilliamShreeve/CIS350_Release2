package application;

import java.util.ArrayList;

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
}
