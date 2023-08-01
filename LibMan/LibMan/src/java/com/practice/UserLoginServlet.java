package com.practice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Add your validation logic here to check the user credentials
        // For demonstration, let's assume the username is "user123" and password is "pass123"
        if ("user123".equals(username) && "pass123".equals(password)) {
            // User login successful, redirect to the user dashboard
            response.sendRedirect("user_dashboard.html"); // Replace with the actual user dashboard page
        } else {
            // User login failed, redirect back to the user login page with an error message
            response.sendRedirect("user_login.html?error=1");
        }
    }
}
