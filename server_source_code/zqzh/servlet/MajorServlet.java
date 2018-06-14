package zqzh.servlet;

import org.json.JSONArray;
import zh.tool.CourseAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static zh.tool.DatabaseParameters.*;

/**
 * Created by kindl on 11/29/2016.
 */
public class MajorServlet extends HttpServlet {

    Connection connection = null; //Access objects
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    private static final String INISTATEMENT="SELECT DISTINCT major from courses;";
    private static final String STATEMENT="SELECT * FROM courses WHERE major=?;";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html"); //Setting return type to text
        PrintWriter out = resp.getWriter();
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            preparedStatement=connection.prepareStatement(INISTATEMENT);
            rs = preparedStatement.executeQuery();

            JSONArray jsonArray= CourseAdapter.courseConverter(rs);
            out.println(jsonArray.toString());
        } catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException classException){
            classException.printStackTrace();
        } finally { //Resources recycling
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String major=req.getParameter("majorname");
        resp.setContentType("text/html"); //Setting return type to text
        PrintWriter out = resp.getWriter(); //Getting a PrintWriter to response

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            preparedStatement=connection.prepareStatement(STATEMENT);
            preparedStatement.setString(1,major);
            rs=preparedStatement.executeQuery();

            JSONArray jsonArray= CourseAdapter.courseConverter(rs);
            out.println(jsonArray.toString());
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally { //Resources recycling
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
