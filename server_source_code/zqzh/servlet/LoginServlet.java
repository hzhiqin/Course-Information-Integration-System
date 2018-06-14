package zqzh.servlet;

import java.io.*;
import java.lang.StringBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import static zh.tool.DatabaseParameters.*;

/**
 * Created by kindl on 10/20/2016.
 * Login verification servlet
 */
public class LoginServlet extends HttpServlet{

    public static final String SQLSTATEMENT = "SELECT * FROM user WHERE user_name= ";

    public Connection connection = null; //Access objects
    public Statement statement = null;
    public ResultSet rs = null;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        /*
         Database query
         */
        String userName = request.getParameter("username"); //Getting data by keys
        String pw = request.getParameter("pw");
        response.setContentType("text/html"); //Setting return type to text
        System.out.print(userName+pw);
        PrintWriter out = response.getWriter(); //Getting a PrintWriter to response

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            statement = connection.createStatement();
            rs = statement.executeQuery(SQLSTATEMENT + "\'"+ userName + "\'" + ";");

            /*
             Result handling
             Verification
             */
            while (rs.next()) {
                StringBuilder rsp = new StringBuilder(); //storing response
                if (rs.getString("password").equals(pw)) {
                    rsp.append("{\"echo\":\"true\", \"uid\":\"");
                    rsp.append(rs.getInt("user_id"));
                    rsp.append("\"}");
                } else {
                    rsp.append("{\"echo\":\"false\", \"uid\":\"-1\"}");
                }
                out.println(rsp.toString());
                rsp.delete(0, rsp.length() - 1);
            }
        } catch (SQLException sqle) {//Handle errors for JDBC
            sqle.printStackTrace();
        } catch (Exception e) {//Handle errors for Class.forName
            e.printStackTrace();
        } finally { //Resources recycling
            try {
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) statement.close();
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
