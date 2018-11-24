package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    private TextField standingField;
    @FXML
    private TextField nameField;
    
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
      		  new PropertyValueFactory<Student,String>("gpa")
        		);
        majorColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String>("major")
        		);
        gNumberColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String>("gNumber")
        		);
        standingColumn.setCellValueFactory(
      		  new PropertyValueFactory<Student,String>("standing")
        		);
    }
    
    @FXML
    public void add() {
    	Student s = new Student();

    	try {
    		s = new Student(nameField.getText(), Float.parseFloat(gpaField.getText()), majorField.getText(),
    				standingField.getText(), gNumField.getText());
    		
    		table.getItems().add(s);
    		
    	}
    	catch(Exception e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Invalid Student Values!");
    		alert.showAndWait();
    	}
    	
    	nameField.setText("");
    	gpaField.setText("");
    	majorField.setText("");
    	standingField.setText("");
    	gNumField.setText("");
    }
}