package ru.sushkova.models;

//import jakarta.validation.constraints.*;

public class Person {

    private int id;

    //@Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    //@NotEmpty(message = "Имя не должно быть пустым")
    private String fullName;

    //@Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int birthYear;

    public Person(int id, String fullName, int birthYear) {
        this.id = id;
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
