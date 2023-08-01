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
import javax.servlet.RequestDispatcher;

public class MainServlet extends HttpServlet {

    // ... (previously defined methods and code) ...

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("adminLogin")) {
                handleAdminLogin(request, response);
            } else if (action.equals("userLogin")) {
                handleUserLogin(request, response);
            } else {
                response.sendRedirect("index.html"); // Redirect to the login page if the action is not recognized
            }
        } else {
            response.sendRedirect("index.html"); // Redirect to the login page if no action parameter is provided
        }
    }

    private void handleAdminLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("adminUsername");
        String password = request.getParameter("adminPassword");

        if (isValidAdmin(username, password)) {
            // Admin login is successful, redirect to the admin dashboard or desired page
            response.sendRedirect("admin_dashboard.html");
        } else {
            // Admin login failed, redirect back to the login page with an error message
            response.sendRedirect("index.html?loginError=admin");
        }
    }


    private boolean isValidAdmin(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean isValidUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
private void handleUserLogin(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String username = request.getParameter("userUsername");
    String password = request.getParameter("userPassword");

    if (isValidUser(username, password)) {
        // User login is successful, redirect to the UserLogin.html page
        response.sendRedirect("UserLogin.html");
    } else {
        // User login failed, redirect back to the login page with an error message
        response.sendRedirect("index.html?loginError=user");
    }
}


    
}