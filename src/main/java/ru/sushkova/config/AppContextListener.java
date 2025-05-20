package ru.sushkova.config;

import ru.sushkova.dao.BookDAO;
import ru.sushkova.dao.PersonDAO;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("bookDAO", new BookDAO());
        sce.getServletContext().setAttribute("personDAO", new PersonDAO());
    }
}