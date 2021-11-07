package com.example.firstproject.model;


public class People {
    Long id;
    String fisrtName;
    String lastName;

    public People(Long id, String fisrtName, String lastName) {
        this.id = id;
        this.fisrtName = fisrtName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
