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
 * Created by kindl on 10/24/2016.
 * Single user courses finder servlet
 */
public class UserCourseServlet extends HttpServlet {

    private static final String SQLSTATEMENT = "SELECT courses.* FROM user_courses, courses " +
            "WHERE (user_courses.course_crn=courses.crn AND user_courses.user_id=?);";

    public Connection connection = null; //Access objects
    public PreparedStatement preparedStatement = null;
    public ResultSet rs = null;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uIdString=req.getParameter("uid"); //Accessing user parameter
        int uId=Integer.parseInt(uIdString);
        resp.setContentType("text/html"); //Setting return type to text
        PrintWriter out = resp.getWriter(); //Getting a PrintWriter to response

        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            preparedStatement=connection.prepareStatement(SQLSTATEMENT);
            preparedStatement.setInt(1,uId);
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
