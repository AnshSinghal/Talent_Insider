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

@WebServlet("/signup") 
public class signupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter(); 

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String password = request.getParameter("password");
        
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            if (userExists(connection, username, email, number)) {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "User already exists.");
                out.println(errorResponse.toString()); 
            } else {
                insertUser(connection, username, email, number,name,password); 
                JSONObject successResponse = new JSONObject();
                successResponse.put("message", "User signed up successfully!");
                out.println(successResponse.toString()); 
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }

    private boolean userExists(Connection connection, String username, String email, String number) throws SQLException {
        String sql = "SELECT * FROM user_login WHERE username = ? OR email = ? OR number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, number);
        ResultSet result = statement.executeQuery();
        return result.next(); // Returns true if a user is found
    }

    private void insertUser(Connection connection, String username, String email, String number, String name, String password) throws SQLException {
        String sql = "INSERT INTO user_login (username, email, number, password, name) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, email);
        statement.setString(3, number);
        statement.setString(4, password);
        statement.setString(5, name);
        statement.executeUpdate();
    }
}
