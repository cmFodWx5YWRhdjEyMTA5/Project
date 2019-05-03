package com.example.mydatastorageapplication;

public class Person {
    int id;
    String name;
    String lastname;
    String qualification;

    public Person() {}

    public Person(String name, String lastname, String qualification) {
        this.name = name;
        this.lastname = lastname;
        this.qualification = qualification;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | Name: " + name + " " + lastname + " | Qualification: " + qualification;
    }
}
