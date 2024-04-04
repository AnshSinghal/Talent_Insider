package com.talent_insider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signup")
public class signupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // RequestDispatcher dispatcher = request.getRequestDispatcher("src/main/java/com/talent_insider/frontend/ClientLoginWindow.java");
        // dispatcher.forward(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Method Not Allowed</title></head><body>");
        out.println("<h1>Method Not Allowed</h1>");
        out.println("<p>The requested resource does not support GET requests.</p>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input parameters
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        
        PrintWriter out = response.getWriter();
        RequestDispatcher dispatcher = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/talent_insider", "root", "Ansh@123");
            String sql = "INSERT INTO user_login (name, username, password, email, number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, number);
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
        
        // Assuming number is of type bigint

        // Insert user data into the database
        // try {
        //     Connection connection = DatabaseConnection.getConnection();
        //     String sql = "INSERT INTO user_login (name, username, password, email, number) VALUES (?, ?, ?, ?, ?)";
        //     PreparedStatement statement = connection.prepareStatement(sql);
        //     statement.setString(1, name);
        //     statement.setString(2, username);
        //     statement.setString(3, password);
        //     statement.setString(4, email);
        //     statement.setString(5, number);
        //     int rowsInserted = statement.executeUpdate();
        //     if (rowsInserted > 0) {
        //         PrintWriter out = response.getWriter();
        //         out.println("User signed up successfully!");
        //     }
        //     connection.close();
        // } catch (SQLException e) {
        //     e.printStackTrace();
        //     response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred while signing up");
        // }
    }
}
