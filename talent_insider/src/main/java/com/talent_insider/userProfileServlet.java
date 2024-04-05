package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userProfile") 
public class userProfileServlet extends HttpServlet {

    // ... (Other imports and class definition)

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json"); 
        PrintWriter out = response.getWriter(); 

        // Get the name parameter from the request
        String name = request.getParameter("name");

        if (name == null || name.isEmpty()) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Please provide a name.");
            out.println(errorResponse.toString());
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            String sql = "SELECT name, age, bio, skills, experience FROM user_profile WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name); 
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                JSONObject profileData = new JSONObject();
                profileData.put("name", result.getString("name"));
                profileData.put("age", result.getInt("age"));
                profileData.put("bio", result.getString("bio"));
                profileData.put("skills", result.getString("skills"));
                profileData.put("experience", result.getString("experience"));

                out.println(profileData.toString());
            } else {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "User profile not found.");
                out.println(errorResponse.toString()); 
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }


    // protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //     // Get user information from the request
    //     String name = request.getParameter("name"); // Assuming name is used as the identifier
    //     int age = Integer.parseInt(request.getParameter("age")); 
    //     String bio = request.getParameter("bio");
    //     String skills = request.getParameter("skills");
    //     String experience = request.getParameter("experience");
    
    //     PrintWriter out = response.getWriter();
    
    //     try {
    //         updateUserProfile(name, age, bio, skills, experience);
    //         out.println("User profile updated successfully!");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
    //     }
    // }
    
    // private void updateUserProfile(String name, int age, String bio, String skills, String experience) throws SQLException, ClassNotFoundException {
    //     Class.forName("com.mysql.cj.jdbc.Driver"); 
    //     Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");
    
    //     // Update SQL to find the user based on 'name'
    //     String sql = "INSERT INTO user_profile name = ?, age = ?, bio = ?, skills = ?, experience = ?"; 
    //     PreparedStatement statement = connection.prepareStatement(sql);
    //     statement.setString(1, name);
    //     statement.setInt(2, age);
    //     statement.setString(3, bio);
    //     statement.setString(4, skills);
    //     statement.setString(5, experience);
    
    //     ResultSet rowsUpdated = statement.executeQuery();
    //     connection.close(); 
    // }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input parameters
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String bio = request.getParameter("bio");
        String skills = request.getParameter("skills");
        String experience = request.getParameter("experience");
        
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");
            String sql = "INSERT INTO user_profile (name, age, bio, skills, experience) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, age);
            statement.setString(3, bio);
            statement.setString(4, skills);
            statement.setString(5, experience);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("User signed up successfully!");
            }
            else{
                out.println("User not signed up successfully!");
            }


        } catch(Exception e){
            out.println(e);

        }
    
    
    }
}
