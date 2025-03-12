package latina.integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    Connection con;
    String host = "jdbc:mysql://localhost:3306/bdlatina";
    String user = "root";
    String pswd = "root";

    public Test() {
        try {
            con = DriverManager.getConnection(host, user, pswd);
            PreparedStatement ps = con.prepareStatement("SELECT first_name, last_name FROM actor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(String.format("%s\t%s", rs.getString(1), rs.getString(2)));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
