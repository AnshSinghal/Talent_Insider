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

@WebServlet("/companyLogin")
public class companyLoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String signupFormHtml = 
            "<!DOCTYPE html>" +
            "<html lang=\"en\">" +
            "<head>" +
            "    <meta charset=\"UTF-8\">" +
            "    <title>Company Signup</title>" +
            "    <style>" +
            "        /* Basic Styling - Put your CSS here */" +
            "        .signup-form { " +
            "            width: 400px; " +
            "            margin: 50px auto; " +
            "            padding: 30px; " +
            "            background: #fff; " +
            "            border-radius: 5px; " +
            "        } " + 
            "        /* ... Rest of your CSS ... */" +
            "    </style>" +
            "</head>" +
            "<body>" +
            "    <div class=\"signup-form\">" +
            "        <h2>Company Signup</h2>" +
            "        <form action=\"/companySignup\" method=\"POST\">" +
            "            <input type=\"text\" name=\"username\" placeholder=\"Username\" required><br>" +
            "            <input type=\"password\" name=\"password\" placeholder=\"Password\" required><br>" + 
            "            <button type=\"submit\">Signup</button>" + 
            "        </form>" +
            "    </div>" +
            "</body>" +
            "</html>";

        out.println(signupFormHtml); 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");

            String sql = "SELECT * FROM company WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) { // Check if username exists
                String storedPassword = result.getString("password");
                if (storedPassword.equals(password)) { // Replace with secure password comparison (hashing)
                    // Successful Login
                    HttpSession session = request.getSession();
                    session.setAttribute("companyUsername", username); // Change to your company dashboard
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
