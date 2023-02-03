package com.project.luigi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.project.dbhandler.PostgresConnector;
import com.project.models.Student; 

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSingleton {
    
    private static final DataSingleton instance = new DataSingleton();

    private ObservableList<Student> studentList =FXCollections.observableArrayList();

    private DataSingleton() {        
        this.loadStudents();
    }

    public static DataSingleton getInstance() { 
        return instance; 
    }

    public ObservableList<Student> getStudents() {
        return this.studentList;
    }

    public void addStudent(Student student) {
        this.studentList.add(student);
    }
    
    private void loadStudents() {
        PostgresConnector pgConnect = new PostgresConnector();
        String sql = "SELECT student_id, student_firstname, student_lastname, student_birthdate, student_email, student_phone, student_level FROM students";
        
        try (Connection connection = pgConnect.getConnection(); Statement statement =connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                String id = resultSet.getString("student_id");
                String firstName = resultSet.getString("student_firstname");
                String lastName = resultSet.getString("student_lastname");
                LocalDate birthDate = resultSet.getDate("student_birthdate").toLocalDate();
                String email = resultSet.getString("student_email");
                String phone = resultSet.getString("student_phone");
                int level = resultSet.getInt("student_level");
                studentList.add(new Student(id, firstName, lastName, birthDate, email, phone, level));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
