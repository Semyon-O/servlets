package ru.netology;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        System.out.println("Сервлет вызван!");  // Должно появиться в консоли Tomcat
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println(
                "<h1>Работает через web.xml!</h1>" +
                        "<p>Context Path: " + req.getContextPath() + "</p>" +
                        "<p>Servlet Path: " + req.getServletPath() + "</p>"
        );
    }
}
// Почему работает только через аннотацию webServlet??????