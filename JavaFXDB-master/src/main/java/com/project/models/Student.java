package com.project.models;

import java.sql.*;
import java.time.LocalDate;

import com.project.dbhandler.PostgresConnector;

import com.project.luigi.DataSingleton;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student extends Person {

    private static ObservableList<Student> studentList = FXCollections.observableArrayList();
    private int level;

     public Student (String id, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber, int level) {
        super(id, firstName, lastName, birthDate, email, phoneNumber);
        this.level = level;
     }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
  
    @Override
    public void print() {
        super.print();
        System.out.println("Level: "+this.level);
    }

    @Override
    public String getFullName() { 
        return super.getFullName();
    }

    public SimpleStringProperty idProperty(){
        return new SimpleStringProperty(this.getId());
    }

    public SimpleStringProperty firstNameProperty(){
        return new SimpleStringProperty(this.getFirstName());
    }

    public SimpleStringProperty lastNameProperty(){
        return new SimpleStringProperty(this.getLastName());
    }

    public SimpleStringProperty emailProperty(){
        return new SimpleStringProperty(this.getEmail());
    }
  
    public SimpleIntegerProperty ageProperty(){
        return new SimpleIntegerProperty(this.getAge());
    }

    public SimpleIntegerProperty levelProperty(){
        return new SimpleIntegerProperty(this.getLevel());
    }

    public void saveStudent() {
        final String sql = "INSERT INTO students" + 
        " (student_id, student_firstname, student_lastname, student_birthdate, student_email, student_phone, student_level) VALUES " + 
        " (?, ?, ?, ?, ?, ?, ?);"; 
        PostgresConnector pgConnect = new PostgresConnector();
        Connection connection = pgConnect.getConnection();
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, this.getId());
            preparedStatement.setString(2, this.getFirstName());
            preparedStatement.setString(3, this.getLastName());
            preparedStatement.setDate(4, Date.valueOf(getBirthDate()));
            preparedStatement.setString(5, this.getEmail());
            preparedStatement.setString(6, this.getPhoneNumber());
            preparedStatement.setInt(7, this.getLevel());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ObservableList<Student> loadStudents() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        PostgresConnector pgConnect = new PostgresConnector();
        String sql = "SELECT * FROM students";
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
            return studentList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteStudent(Student student) {
        PostgresConnector pgConnect = new PostgresConnector();
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection connection = pgConnect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        studentList.remove(student);
    }
}


