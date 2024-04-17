package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/companySignup") // Adjust URL pattern if needed
public class companySignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password"); // Remember to hash this
        String email = request.getParameter("email");
        String name = request.getParameter("name"); 
        String description = request.getParameter("description");
        String website = request.getParameter("website"); 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            if (companyExists(connection, username, email, website)) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "Company already exists.");
                out.println(errorResponse.toString()); 
            } else {
                insertCompany(connection, username, password, email, name, description, website); // Remember to hash password!
                JSONObject successResponse = new JSONObject();
                successResponse.put("message", "Company registered successfully!");
                out.println(successResponse.toString()); 
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    private boolean companyExists(Connection connection, String username, String email, String website) throws SQLException {
        String sql = "SELECT * FROM company WHERE username = ? OR email = ? OR website = ?"; 
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, website);
        ResultSet result = statement.executeQuery();
        return result.next(); // Returns true if a company is found
    }

    private void insertCompany(Connection connection, String username, String password, String email, String name, String description, String website) throws SQLException {
        String sql = "INSERT INTO company (username, password, email, name, description, website) VALUES (?, ?, ?, ?, ?, ?)"; 
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password); // Replace with hashed password
        statement.setString(3, email);
        statement.setString(4, name);
        statement.setString(5, description);
        statement.setString(6, website);
        statement.executeUpdate();
    }

    // ... (rest of the companyServlet code) 

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();

    String username = request.getParameter("username");

    if (username == null || username.isEmpty()) {
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("error", "Please provide a username.");
        out.println(errorResponse.toString()); 
        return;
    }

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

        String sql = "SELECT * FROM company WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            JSONObject companyData = new JSONObject();
            companyData.put("username", result.getString("username"));
            companyData.put("email", result.getString("email"));
            companyData.put("name", result.getString("name"));
            companyData.put("description", result.getString("description"));
            companyData.put("website", result.getString("website"));
            companyData.put("password", result.getString("password"));
            // ... Add other relevant columns

            out.println(companyData.toString()); 
        } else {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Company not found.");
            out.println(errorResponse.toString()); 
        }

        connection.close();
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
    }
}

}
