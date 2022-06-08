import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serial;

import java.sql.*;

@WebServlet
public class TestDbServlet extends HttpServlet {
//TODO delete test

    @Serial
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        // setup connection variables
        String username = "root";
        String password = "root";

        String jdbcUrl = "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        // get connection to database
        try {
            PrintWriter out = response.getWriter();

            out.println("Connecting to database: " + jdbcUrl);

            Class.forName(driver);

            Connection myCon = DriverManager.getConnection(jdbcUrl, username, password);

            out.println("SUCCESS!");

            myCon.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }
}
