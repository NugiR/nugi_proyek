import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {
    
    private static DatabaseManager instance;
    private Connection con;

    private DatabaseManager() {
        String dlink = "jdbc:mysql://localhost:3306/db_kantin";
        String dbUser = "root";
        String dbPass = "";
        try {
            con = DriverManager.getConnection(dlink,dbUser, dbPass);
        } catch (SQLException e) {
            throw new IllegalStateException("DB Errors: ", e);
        }
    }
    
    public static Connection getConnection(){
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance.con;
    }
}
