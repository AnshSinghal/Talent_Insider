package com.talent_insider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/job") // Adjust URL as needed
public class jobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input parameters
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String skills = request.getParameter("skills");
        String salary = request.getParameter("salary");
        String deadline = request.getParameter("deadline");

        PrintWriter out = response.getWriter();

        // Get company username from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("companyUsername") == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You must be logged in as a company.");
            return;
        }
        String companyUsername = (String) session.getAttribute("companyUsername");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            // Insert talent requirement data into the database
            String sql = "INSERT INTO job (title, description, skills, salary, deadline, company_username) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, skills);
            statement.setInt(4, Integer.parseInt(salary));
            statement.setDate(5, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(deadline).getTime()));
            statement.setString(6, companyUsername); 

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("Talent requirement added successfully!");
                // Redirect to company dashboard or other success page
            } else {
                out.println("Failed to add talent requirement. Please try again.");
            }

            connection.close(); // Close the database connection
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
