package com.example.app_.MenuUserOptions;

public class ClientsModel {

    private int id;
    private String name;
    private String surname;
    private String city;
    private String department;
    private int salary;

    public ClientsModel(int id, String name, String surname, String city, String department, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.department = department;
        this.salary = salary;
    }

    public ClientsModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name +
                ", Surname: " + surname + ", City: " + city +
                ", Department: " + department + ", Salary: " + salary;
    }
}
