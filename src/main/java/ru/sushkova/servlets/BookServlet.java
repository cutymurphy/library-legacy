package ru.sushkova.servlets;

import ru.sushkova.dao.BookDAO;
import ru.sushkova.dao.PersonDAO;
import ru.sushkova.models.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
    private final BookDAO bookDAO = new BookDAO();
    private final PersonDAO personDAO = new PersonDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Book> books = bookDAO.index();
            req.setAttribute("books", books);
            req.getRequestDispatcher("/WEB-INF/views/books/index.jsp").forward(req, resp);
        } else if (pathInfo.equals("/new")) {
            req.getRequestDispatcher("/WEB-INF/views/books/new.jsp").forward(req, resp);
        } else if (pathInfo.matches("^/\\d+/edit$")) {
            int id = Integer.parseInt(pathInfo.replaceAll("\\D+", ""));
            req.setAttribute("book", bookDAO.show(id));
            req.getRequestDispatcher("/WEB-INF/views/books/edit.jsp").forward(req, resp);
        } else if (pathInfo.matches("^/\\d+$")) {
            int id = Integer.parseInt(pathInfo.substring(1));
            Book book = bookDAO.show(id);
            req.setAttribute("book", book);
            req.setAttribute("owner", bookDAO.getPersonByBookId(id));
            req.setAttribute("people", personDAO.index());
            req.getRequestDispatcher("/WEB-INF/views/books/show.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String method = req.getParameter("_method");

        if ("PATCH".equalsIgnoreCase(method)) {
            doPatch(req, resp);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(req, resp);
        } else if ("ASSIGN".equalsIgnoreCase(method)) {
            int bookId = Integer.parseInt(req.getPathInfo().replaceAll("\\D+", ""));
            int personId = Integer.parseInt(req.getParameter("personId"));
            bookDAO.giveBookToPerson(bookId, personId);
            resp.sendRedirect(req.getContextPath() + "/books/" + bookId);
        } else if ("RETURN".equalsIgnoreCase(method)) {
            int bookId = Integer.parseInt(req.getPathInfo().replaceAll("\\D+", ""));
            bookDAO.takeBookFromPerson(bookId);
            resp.sendRedirect(req.getContextPath() + "/books/" + bookId);
        } else {
            Book book = new Book();
            book.setTitle(req.getParameter("title"));
            book.setAuthor(req.getParameter("author"));
            book.setYear(Integer.parseInt(req.getParameter("year")));

            bookDAO.save(book);
            resp.sendRedirect(req.getContextPath() + "/books");
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().replaceAll("\\D+", ""));
        Book book = new Book();
        book.setTitle(req.getParameter("title"));
        book.setAuthor(req.getParameter("author"));
        book.setYear(Integer.parseInt(req.getParameter("year")));

        bookDAO.update(id, book);
        resp.sendRedirect(req.getContextPath() + "/books/" + id);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().replaceAll("\\D+", ""));
        bookDAO.delete(id);
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
