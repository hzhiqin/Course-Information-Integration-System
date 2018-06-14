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
 * Created by kindl on 10/25/2016.
 * Key word match search servlet
 */
public class SearchServlet extends HttpServlet{

    public static final String SQL1 = "SELECT courses.* FROM courses WHERE courses.crn=?;";//Accurate search with class CRN code
    public static final String SQL2 = "SELECT * FROM courses WHERE courses.course_name LIKE '%";
    // Fuzzy match by name

    Connection connection = null; //Access objects
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int isSpecific= Integer.parseInt(req.getParameter("spec")); //Specifying whether use CRN
        if (isSpecific==1){
            int crn= Integer.parseInt(req.getParameter("crn"));
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PW);
                preparedStatement = connection.prepareStatement(SQL1);
                preparedStatement.setInt(1, crn);
                rs = preparedStatement.executeQuery();

                JSONArray jsonArray= CourseAdapter.courseConverter(rs);

                resp.setContentType("json");
                PrintWriter out = resp.getWriter();
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
        } else {
            String characters= req.getParameter("name_char");
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PW);
                preparedStatement = connection.prepareStatement(SQL2+characters+"%'");//finish SQL statement
                rs = preparedStatement.executeQuery();

                JSONArray jsonArray= CourseAdapter.courseConverter(rs);

                resp.setContentType("text/html");
                PrintWriter out = resp.getWriter();
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
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
