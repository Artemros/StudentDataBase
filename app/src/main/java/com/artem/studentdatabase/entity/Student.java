package com.artem.studentdatabase.entity;

/**
 * Created by denis on 19.03.2016.
 */
public class Student {
    private Long id;
    private String name;
    private String lastName;
    private String thirdName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    @Override
    public String toString() {
        return (name + " " + lastName).toUpperCase();
    }
}
