package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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


@WebServlet("/job") // Adjust the URL pattern if needed
public class jobServlet extends HttpServlet {
    // private static final Logger logger = LogManager.getLogger(jobServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
    
        String title = request.getParameter("title");
        String description = request.getParameter("description"); // Use 'description' if that's your column name
        String skills = request.getParameter("skills");
        String salary = request.getParameter("salary");
        String deadline = request.getParameter("deadline");
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");
    
            String sql = "INSERT INTO job (title, description, skills, salary, deadline) VALUES (?, ?, ?, ?, ?)"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description); // Replace with hashed descriptionription
            statement.setString(3, skills);
            statement.setString(4, salary);
            statement.setString(5, deadline);
    
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
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            String sql = "SELECT * FROM job"; 
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            JSONArray jobList = new JSONArray();
            while (result.next()) {
                JSONObject job = new JSONObject();
                job.put("title", result.getString("title"));
                job.put("description", result.getString("description"));
                job.put("salary", result.getString("salary"));
                job.put("skills", result.getString("skills"));
                job.put("deadline", result.getString("deadline"));
                // ... Add any other relevant columns 

                jobList.put(job);
            }

            out.println(jobList.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
