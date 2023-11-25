
/*Set your own configs*/

package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    String url = "jdbc:mysql://yourHost:yourPort/sistema_polizas";
    String user = "yourUser";
    String pass = "yourPass";
    Connection con;

    public Connection Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la DB: " + e.getMessage());
        }

        return con;
    }

}