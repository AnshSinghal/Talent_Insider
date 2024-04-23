package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;
import org.json.JSONArray; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// import org.apache.logging.log4j.Logger; 
// import org.apache.logging.log4j.LogManager;


@WebServlet("/jobDatas") // Adjust the URL pattern if needed
public class jobDataServlet extends HttpServlet {
    // private static final Logger logger = LogManager.getLogger(jobServlet.class);
    // private static final Logger logger = LogManager.getLogger(jobDataServlet.class);
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
    
        String tableName = request.getParameter("tableName");
        String name = request.getParameter("name");
        String number = request.getParameter("number"); // Use 'number' if that's your column name
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String bio = request.getParameter("bio");
        String skills = request.getParameter("skills");
        String experience = request.getParameter("experience");

    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");
    
            String sql = "INSERT INTO `" + tableName + "` (name, number, email, age, bio, skills, experience) VALUES (?, ?, ?, ?, ?, ?, ?)"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, number); // Replace with hashed numberription
            statement.setString(3, email);
            statement.setString(4, age);
            statement.setString(5, bio);
            statement.setString(6, skills);
            statement.setString(7, experience);
    
            int rowsInserted = statement.executeUpdate();
    
            if (rowsInserted > 0) {
                JSONObject successResponse = new JSONObject();
                successResponse.put("message", "Job posted successfully.");
                out.println(successResponse.toString()); 
            } else {
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "Failed to post job.");
                out.println(errorResponse.toString()); 
            }
    
            connection.close(); // Close the database connection
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error",e.getMessage());
            out.println(errorResponse.toString()); 
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String tableName = request.getParameter("tableName");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            String sql = "SELECT * FROM `" + tableName + "`"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            JSONArray jobList = new JSONArray();
            while (result.next()) {
                JSONObject job = new JSONObject();
                job.put("name", result.getString("name"));
                job.put("number", result.getString("number"));
                job.put("age", result.getString("age"));
                job.put("email", result.getString("email"));
                job.put("bio", result.getString("bio"));
                job.put("skills", result.getString("skills"));
                job.put("experience", result.getString("experience"));
                // ... Add any other relevant columns 

                jobList.put(job);
            }

            out.println(jobList.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
