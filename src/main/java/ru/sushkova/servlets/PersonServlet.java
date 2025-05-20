package ru.sushkova.servlets;

import ru.sushkova.dao.PersonDAO;
import ru.sushkova.models.Person;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/people/*")
public class PersonServlet extends HttpServlet {
    private PersonDAO personDAO;

    @Override
    public void init() {
        this.personDAO = (PersonDAO) getServletContext().getAttribute("personDAO");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Person> people = personDAO.index();
            req.setAttribute("people", people);
            req.getRequestDispatcher("/WEB-INF/views/people/index.jsp").forward(req, resp);
        } else if (pathInfo.equals("/new")) {
            req.getRequestDispatcher("/WEB-INF/views/people/new.jsp").forward(req, resp);
        } else if (pathInfo.matches("^/\\d+/edit$")) {
            int id = Integer.parseInt(pathInfo.replaceAll("\\D+", ""));
            req.setAttribute("person", personDAO.show(id));
            req.getRequestDispatcher("/WEB-INF/views/people/edit.jsp").forward(req, resp);
        } else if (pathInfo.matches("^/\\d+$")) {
            int id = Integer.parseInt(pathInfo.substring(1));
            req.setAttribute("person", personDAO.show(id));
            req.setAttribute("books", personDAO.getBooksByPersonId(id));
            req.getRequestDispatcher("/WEB-INF/views/people/show.jsp").forward(req, resp);
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
        } else {
            Person person = new Person();
            person.setFullName(req.getParameter("fullName"));
            person.setBirthYear(Integer.parseInt(req.getParameter("birthYear")));

            personDAO.save(person);
            resp.sendRedirect(req.getContextPath() + "/people");
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().replaceAll("\\D+", ""));
        Person person = new Person();
        person.setFullName(req.getParameter("fullName"));
        person.setBirthYear(Integer.parseInt(req.getParameter("birthYear")));

        personDAO.update(id, person);
        resp.sendRedirect(req.getContextPath() + "/people/" + id);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().replaceAll("\\D+", ""));
        personDAO.delete(id);
        resp.sendRedirect(req.getContextPath() + "/people");
    }
}
