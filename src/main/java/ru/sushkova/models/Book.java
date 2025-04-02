package ru.sushkova.models;

//import jakarta.validation.constraints.*;

public class Book {

    private int id;

    //@NotEmpty(message = "Название не должно быть пустым")
    //@Size(min = 2, max = 100, message = "Название должно быть от 2 до 100 символов длиной")
    private String title;

    //@NotEmpty(message = "Автор не должен быть пустым")
    //@Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов длиной")
    private String author;

    //@Min(value = 1500, message = "Год должен быть больше чем 1500")
    private int year;

    private Integer borrowerId;

    public Book(int id, String title, String author, int year, Integer borrowerId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.borrowerId = borrowerId;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }
}
