package ru.sushkova.dao;

import ru.sushkova.models.Book;
import ru.sushkova.models.Person;
import ru.sushkova.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                people.add(mapPerson(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                person = mapPerson(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        String sql = "INSERT INTO person (full_name, year_of_birth) VALUES (?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, person.getFullName());
            stmt.setInt(2, person.getBirthYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person person) {
        String sql = "UPDATE person SET full_name = ?, year_of_birth = ? WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, person.getFullName());
            stmt.setInt(2, person.getBirthYear());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM person WHERE id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        return new Person(
            rs.getInt("id"),
            rs.getString("full_name"),
            rs.getInt("year_of_birth")
        );
    }

    public List<Book> getBooksByPersonId(int personId) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE person_id = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}