package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUIController {
	
	private StudentTable studentList;
	
	@FXML
	private TableView<Student> table;
	@FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> gpaColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;
    @FXML
    private TableColumn<Student, String> gNumberColumn;
    @FXML
    private TableColumn<Student, String> standingColumn;
    @FXML
    private Button addButton;
    @FXML
    private TextField gpaField;
    @FXML
    private TextField majorField;
    @FXML
    private TextField gNumField;
    @FXML
    private ComboBox<String> standingBox;
    @FXML
    private TextField nameField;
    @FXML
    private MenuItem deleteStudent;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem load;
    
    private ObservableList<Student> list;
    

    @FXML
    public void initialize() {
        studentList = new StudentTable();
        ObservableList<Student> list = FXCollections.observableArrayList(studentList.returnList());
        table.setItems(list);
        									
        nameColumn.setCellValueFactory(
        		  new PropertyValueFactory<Student,String>("name")
        		);
        gpaColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String >("GPA")
        		);
        majorColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String>("major")
        		);
        gNumberColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String>("gNum")
        		);
        standingColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String>("standing")
        		);
    }
    
    @FXML
    public void delete() {
    	Student s = table.getSelectionModel().getSelectedItem();
    	table.getItems().remove(s);
    }
    
    @FXML
    public void add() {
    	Student s = new Student();

    	try {
    		if(standingBox.getValue() == null) {
    			throw new Exception("No standing selected.");
    		}
    		s = new Student(nameField.getText(), Float.parseFloat(gpaField.getText()), majorField.getText(),
    				standingBox.getValue().toString(), gNumField.getText());
    		
    		table.getItems().add(s);
    		
    	}
    	catch(Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("ERROR: " + e.toString());
    		alert.showAndWait();
    	}
    	
    	nameField.setText("");
    	gpaField.setText("");
    	majorField.setText("");
    	standingBox.setPromptText("Standing");
    	gNumField.setText("");
    }
    
    @FXML
    public void save() {
    	String filename = "";
        JFileChooser chooser = new JFileChooser();
		int status = chooser.showSaveDialog(null);
		if(status == JFileChooser.APPROVE_OPTION) {
			filename = chooser.getSelectedFile().
					getAbsolutePath();
		}
    	try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(table);
			oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
    
    }
    
    @FXML
    public void load() {
    	String filename = "";
    	JFileChooser chooser = new JFileChooser();
  		int status = chooser.showSaveDialog(null);
  		if(status == JFileChooser.APPROVE_OPTION) {
  			filename = chooser.getSelectedFile().
  					getAbsolutePath();
  		}
    	try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			list = (ObservableList<Student>) in.readObject();
			in.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog
			(null, "Invalid File!");
		}
		catch(ClassNotFoundException e) {
			JOptionPane.showMessageDialog
			(null, "Invalid File!");
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog
			(null, "Invalid File!");
		}
    }
    
}