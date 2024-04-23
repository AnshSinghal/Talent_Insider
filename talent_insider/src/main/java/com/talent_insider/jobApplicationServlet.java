package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/jobApp")
public class jobApplicationServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
    
        String tableName = request.getParameter("tableName");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");
    
            String sql = "CREATE TABLE `talent_insider`.`" + tableName + "`(`name` VARCHAR(255) NOT NULL,`number` VARCHAR(255) NULL,`email` VARCHAR(255) NULL,`age` VARCHAR(255) NULL,`bio` LONGTEXT NULL,`skills` LONGTEXT NULL,`experience` LONGTEXT NULL,PRIMARY KEY (`name`));"; 
            // String sql = "CREATE TABLE `talent_insider`.`?`(`name` VARCHAR(255) NOT NULL,`number` VARCHAR(255) NULL,`email` VARCHAR(255) NULL,`age` VARCHAR(255) NULL,`bio` LONGTEXT NULL,`skills` LONGTEXT NULL,`experience` LONGTEXT NULL,PRIMARY KEY (`name`));"; 

            PreparedStatement statement = connection.prepareStatement(sql);
            // statement.setString(1, tableName);

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
}
