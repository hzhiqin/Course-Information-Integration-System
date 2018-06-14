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
 * Created by kindl on 10/27/2016.
 * for user to sign up
 * data then can be stored in server
 */
public class SignUpServlet extends HttpServlet{
    private final String STATEMENT="INSERT INTO user (user_name,password) VALUES (?,?);";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null; //Access objects
        PreparedStatement preparedStatement = null;

        String account=req.getParameter("username");
        String password=req.getParameter("pw");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PW);
            preparedStatement=connection.prepareStatement(STATEMENT);
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,password);
            int rs=preparedStatement.executeUpdate();
            /*
            Checking whether data update successfully
             */
            if (rs==1){
                out.println("[{\"sign_up\":\"1\"}]");
            } else {
                out.println("[{\"sign_up\":\"0\"}]");
            }

        } catch (SQLException | ClassNotFoundException sqlE){
            sqlE.printStackTrace();
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
