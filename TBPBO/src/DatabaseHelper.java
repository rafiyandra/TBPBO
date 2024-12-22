import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/hasil_tani";
        String user = "root";
        String password = ""; // Ganti dengan password MySQL Anda
        return DriverManager.getConnection(url, user, password);
    }
}