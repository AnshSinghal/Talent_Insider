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

@WebServlet("/companySignup")
public class companySignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String website = request.getParameter("website");

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            // Check for existing username (ideally also email)
             String checkExisting = "SELECT * FROM company WHERE username = ?"; 
             PreparedStatement checkStmt = connection.prepareStatement(checkExisting);
             checkStmt.setString(1, username); 
             ResultSet existingResult = checkStmt.executeQuery();

            if (existingResult.next()) {
                out.println("Username already exists. Please try a different one.");
                return; 
            }

            // Insert company data into the database
            String sql = "INSERT INTO company (username, password, email, name, description, website) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, name);
            statement.setString(5, description);
            statement.setString(6, website);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("Company signup successful!");
                // Further, maybe redirect to a login or a welcome page
            } else {
                out.println("Company signup failed. Please try again.");
            }

            connection.close(); // Close the database connection
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
    
}
