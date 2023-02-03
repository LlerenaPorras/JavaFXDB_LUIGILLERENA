package com.project.luigi;

import java.io.IOException;

import com.project.models.Student;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentListController {
    DataSingleton data  = DataSingleton.getInstance();

    @FXML private TableView studentsTableView;
    @FXML private TableColumn idCol;
    @FXML private TableColumn firstNameCol;
    @FXML private TableColumn lastNameCol;
    @FXML private TableColumn emailCol;
    @FXML private TableColumn birthDateCol;
    @FXML private TableColumn ageCol;
    @FXML private TableColumn levelCol;

    @FXML
    public void initialize() {
        // TODO Auto-generated method stub
        this.idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));
        this.firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        this.lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        this.emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        this.birthDateCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("birthDate"));
        this.ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        this.levelCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("level"));
        this.studentsTableView.setItems(data.getStudents());
    }

    @FXML
    private void displayStudentForm() throws IOException {
        App.setRoot("studentForm");
    }

    @FXML
    private void exitButton() throws IOException {
        System.exit(0);
    }

    @FXML
    private void deleteButton() {
        Student selectedStudent = (Student) studentsTableView.getSelectionModel().getSelectedItem();
        if(selectedStudent != null) {
            String studentID = selectedStudent.getId();
            selectedStudent.deleteStudent(selectedStudent);
            this.studentsTableView.setItems(Student.loadStudents());

        }

    }
}
