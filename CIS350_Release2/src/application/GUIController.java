package application;

import java.util.List;
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
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/***********************************************************************
 * This class controls all of the functionality that occurs within our 
 * Student Database application. This is known as the controller class
 * where a TableView is used in replacement of a JTable.
 * 
 * @author William Shreeve, Hai Duong, and Trung-vuong Pham
 **********************************************************************/
public class GUIController {
    
    /** TableView of Students */
    @FXML
    private TableView<Student> table;
    
    /** The various TableColumns used in our application */
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
    
    /** Button for adding students */
    @FXML
    private Button addButton;
    
    /** Various TextFields needed by our application */
    @FXML
    private TextField gpaField;
    @FXML
    private TextField majorField;
    @FXML
    private TextField gNumField;
    @FXML
    private TextField nameField;
    
    /** The drop down menu for class standing */
    @FXML
    private ComboBox<String> standingBox;
    
    /** Items on the MenuBar */
    @FXML
    private MenuItem deleteStudent;
    @FXML
    private MenuItem addStudent;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem saveX;
    @FXML
    private MenuItem load;
    
    /** ObservableList of students in the database */
    private ObservableList<Student> list;
    
    /** Our instance of using a Table with Release 1 */
    private StudentTable studentList;
    
    /*******************************************************************
     * This initializes all of the property value factories and set 
     * cell factories used for our application.
     ******************************************************************/
    @FXML
    public void initialize() {
        studentList = new StudentTable();
        list = FXCollections.observableArrayList(studentList.returnList());
                                            
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
        
        table.setItems(list);
        table.setEditable(true);
        
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        gpaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        majorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        standingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        gNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    /*******************************************************************
     * This simply quits the application.
     ******************************************************************/
    @FXML
    public void quit() {
        System.exit(0);
    }
    
    /*******************************************************************
     * This method deletes a student off the database.
     ******************************************************************/
    @FXML
    public void delete() {
        Student s = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(s);
    }
    
    /*******************************************************************
     * This method adds a student to the database.
     ******************************************************************/
    @FXML
    public void add() {
        Student s = new Student();

        try {
            if(standingBox.getValue() == null) {
                throw new Exception("No standing selected.");
            }
            s = new Student(nameField.getText(), gpaField.getText(), 
                    majorField.getText(), standingBox.getValue().
                    toString(), gNumField.getText());
            table.getItems().add(s);
            
            // when student is successfully added, empty text fields
            nameField.setText("");
            gpaField.setText("");
            majorField.setText("");
            standingBox.setPromptText("Standing");
            gNumField.setText("");
        }
        catch(Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("ERROR: " + e.toString());
            alert.showAndWait();
        }
    }
    
    /*******************************************************************
     * This method allows the user to save a list of students into a 
     * serialized file.
     ******************************************************************/
    @FXML
    public void save() {
        String filename = "";
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showSaveDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().
                    getAbsolutePath();
        }else {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Student> toArray = table.getItems();
            toArray = new ArrayList<Student>(toArray);
            oos.writeObject(toArray);
            oos.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    
    }
    
    @FXML
    public void saveAsExcel() throws IOException {
        @SuppressWarnings("resource")
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("database");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if(table.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
        String filename = "";
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showSaveDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().
                    getAbsolutePath();
        }
        FileOutputStream fileOut = new FileOutputStream(filename);
        workbook.write(fileOut);
        fileOut.close();
    }
    
    /*******************************************************************
     * This method loads a file to the student database.
     ******************************************************************/
    @SuppressWarnings("unchecked")
    @FXML
    public void load() {
        list.removeAll(list);
        String filename = "";
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION) {
            filename = chooser.getSelectedFile().
                    getAbsolutePath();
        }else {
            return;
        }
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Student> toArray = (List<Student>) in.readObject();
            toArray = new ArrayList<Student>(toArray);
            table.setItems(FXCollections.observableArrayList(toArray));
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
    
    /*******************************************************************
     * These methods are helpers to allow editing within the cells of 
     * the TableView.
     ******************************************************************/
    /*******************************************************************
     * This method is used to edit the names in 'Name' column.
     * 
     * @param studentStringCellEditEvent The click event
     * @throws Exception Any errors that can occur
     ******************************************************************/
    public void onEditName(TableColumn.CellEditEvent
        <Student, String> studentStringCellEditEvent) throws Exception {
        Student s = table.getSelectionModel().getSelectedItem();
        s.setName(studentStringCellEditEvent.getNewValue());
    }
    
    /*******************************************************************
     * This method is used to edit the GPA in 'GPA' column.
     * 
     * @param studentStringCellEditEvent The click event
     * @throws Exception Any errors that can occur
     ******************************************************************/
    public void onEditGPA(TableColumn.CellEditEvent
        <Student, String> studentStringCellEditEvent) throws Exception {
        Student s = table.getSelectionModel().getSelectedItem();
        s.setGPA(studentStringCellEditEvent.getNewValue());
    }
    
    /*******************************************************************
     * This method is used to edit the majors in 'Major' column.
     * 
     * @param studentStringCellEditEvent The click event
     * @throws Exception Any errors that can occur
     ******************************************************************/
    public void onEditMajor(TableColumn.CellEditEvent
        <Student, String> studentStringCellEditEvent) throws Exception {
        Student s = table.getSelectionModel().getSelectedItem();
        s.setMajor(studentStringCellEditEvent.getNewValue());
    }
    
    /*******************************************************************
     * This method is used to edit the standings in 'Standing' column.
     * 
     * @param studentStringCellEditEvent The click event
     * @throws Exception Any errors that can occur
     ******************************************************************/
    public void onEditStanding(TableColumn.CellEditEvent
        <Student, String> studentStringCellEditEvent) throws Exception {
        Student s = table.getSelectionModel().getSelectedItem();
        s.setStanding(studentStringCellEditEvent.getNewValue());
    }
    
    /*******************************************************************
     * This method is used to edit the G Number in 'G Number' column.
     * 
     * @param studentStringCellEditEvent The click event
     * @throws Exception Any errors that can occur
     ******************************************************************/
    public void onEditGNum(TableColumn.CellEditEvent
        <Student, String> studentStringCellEditEvent) throws Exception {
        Student s = table.getSelectionModel().getSelectedItem();
        s.setGNum(studentStringCellEditEvent.getNewValue());
    }
}
