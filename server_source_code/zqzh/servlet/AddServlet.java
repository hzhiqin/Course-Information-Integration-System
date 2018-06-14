package zqzh.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static zh.tool.DatabaseParameters.*;

/**
 * Created by kindl on 10/26/2016.
 * Handling Adding classes request from students
 */
public class AddServlet extends HttpServlet {

    private static final String SQLSTATEMENT = "INSERT INTO user_courses (user_id,course_crn)" +
            "SELECT ?,? " +
            "FROM dual " +
            "WHERE NOT EXISTS( " +
            "      SELECT * " +
            "      FROM user_courses " +
            "      WHERE user_id = ? and course_crn=? " +
            ");"; //SQL statement : can insert new record to table
                // including repeating check
    private static final String DELETESTATEMENT="DELETE FROM user_courses WHERE user_id=? and course_crn=?;";
    Connection connection = null; //Access objects
    PreparedStatement preparedStatement = null;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int uId= Integer.parseInt(req.getParameter("uid"));
        int crn= Integer.parseInt(req.getParameter("crn"));
        resp.setContentType("text/html"); //Setting return type to text
        PrintWriter out = resp.getWriter();

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            preparedStatement=connection.prepareStatement(SQLSTATEMENT);
            preparedStatement.setInt(1,uId);//Filling up SQL statement
            preparedStatement.setInt(2,crn);
            preparedStatement.setInt(3,uId);
            preparedStatement.setInt(4,crn);
            int rs=preparedStatement.executeUpdate();
            if (rs==1) {
                out.println("[{\"add\":\"1\"}]");
            } else {
                out.println("[{\"add\":\"0\"}]");
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally { //Resources recycling
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uId= Integer.parseInt(req.getParameter("uid"));
        int crn= Integer.parseInt(req.getParameter("crn"));
        resp.setContentType("text/html"); //Setting return type to text
        PrintWriter out = resp.getWriter();

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            preparedStatement=connection.prepareStatement(DELETESTATEMENT);
            preparedStatement.setInt(1,uId);//Filling up SQL statement
            preparedStatement.setInt(2,crn);
            int rs=preparedStatement.executeUpdate();
            if (rs==1) {
                out.println("[{\"delete\":\"1\"}]");
            } else {
                out.println("[{\"delete\":\"0\"}]");
            }
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally { //Resources recycling
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
