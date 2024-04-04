package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            String sql = "SELECT * FROM user_login WHERE username = ?"; //  SQL query to check if username exists
            PreparedStatement statement = connection.prepareStatement(sql); // prepared statement to prevent SQL injection  
            statement.setString(1, username); // Set the username parameter
            ResultSet result = statement.executeQuery();

            if (result.next()) { // Check if username exists
                String storedPassword = result.getString("password");
                if (storedPassword.equals(password)) {
                    // Successful Login - Create session
                    HttpSession session = request.getSession();
                    session.setAttribute("username", username);
                    out.println("Login successful."); 
                    // response.sendRedirect("welcome.jsp"); // Change this to your welcome/dashboard page
                } else {
                    out.println("Incorrect password.");
                }
            } else {
                out.println("Username does not exist.");
            }

            connection.close(); 
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}
