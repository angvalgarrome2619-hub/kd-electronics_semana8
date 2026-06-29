package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/kd_electronics";
    private static final String USUARIO = "root";

    // CAMBIA ESTA CONTRASEÑA POR LA TUYA
    private static final String PASSWORD = "12345";

    public static Connection getConnection() {

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");

        } catch (SQLException e) {

            System.out.println("Error al conectar con la base de datos: " + e.getMessage());

        }

        return conexion;
    }
}