package com.project.models;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

public class Person {
    
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String email;
    private String phoneNumber;

    public Person (String id, String firstName, String lastName, LocalDate birthDate, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName(){
        return this.getFirstName()+" "+this.getLastName();
    }
  
    public int getAge(){
        LocalDate now = LocalDate.now();
        return Period.between(this.birthDate, now).getYears();
    }

    public void print(){;      
        String id = this.getId();
        String fullname = this.getFullName();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(this.getBirthDate());
        int age = this.getAge();
        String mail = this.getEmail();
        String phone = this.getPhoneNumber();

        System.out.println("\n");
        System.out.println("***** Person Information *****");
        System.out.println("------- "+fullname+" -------");
        System.out.println("ID: "+id);
        System.out.println("Birth Date: "+date);
        System.out.println("Age: "+age+" años.");
        System.out.println("Email: "+mail);
        System.out.println("Phone Number: "+phone);
    }
}
