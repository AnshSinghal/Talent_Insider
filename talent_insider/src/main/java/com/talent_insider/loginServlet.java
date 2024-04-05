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
import org.mindrot.jbcrypt.BCrypt; //bcrypt is used to hash the password for security 

@WebServlet("/login")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            String sql = "SELECT * FROM user_login WHERE username = ?"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) { 
                String storedPasswordHash = result.getString("password"); 

                if (BCrypt.checkpw(password, storedPasswordHash)) { // Assuming you're using bcrypt
                    JSONObject successResponse = new JSONObject();
                    successResponse.put("message", "Login successful.");
                    out.println(successResponse.toString()); 
                } else {
                    JSONObject errorResponse = new JSONObject();
                    errorResponse.put("error", "Incorrect password.");
                    out.println(errorResponse.toString()); 
                }

            } else {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "Username does not exist.");
                out.println(errorResponse.toString()); 
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
