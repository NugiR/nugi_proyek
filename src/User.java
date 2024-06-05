import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String username;
    public String password;
    public String role;
    Statement statement;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

   public void createTabelUser() throws SQLException{
        Statement statement = DatabaseManager.getConnection().createStatement();
        statement.execute("""
                CREATE TABLE IF EXISTS custemer (
                    id_user int PRIMARY KEY AUTO_INCREMENT,
                    id_customer int,
                    username varchar(255) NOT NULL UNIQUE,
                    password varchar(255) NOT NULL)
                 """
        );
    }

    public void createTabelPengguna() {
            Statement statement;
            try {
                statement = DatabaseManager.getConnection().createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS pengguna (id_pengguna int PRIMARY KEY AUTO_INCREMENT, peran_pengguna varchar(50), username varchar(255) NOT NULL UNIQUE, password varchar(255) NOT NULL)");
                insertDataIntoKantinTable("Kantin A");
                insertDataIntoKantinTable("Kantin B");
                insertDataIntoKantinTable("Kantin C");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    private void insertDataIntoKantinTable(String namaKantin) {
        String queryInsertKantin = "INSERT INTO kantin (Nama_Kantin) VALUES (?)";
        try {
            PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(queryInsertKantin);
            stmt.setString(1, namaKantin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPengguna()  {
        String queryInsertPengguna = "INSERT INTO pengguna (username, password, peran_pengguna) VALUE (?, ?, ?)";
        PreparedStatement stmt;
        try {
            stmt = DatabaseManager.getConnection().prepareStatement(queryInsertPengguna,
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        
    }

    public void checkUsername() {
        String query = "SELECT username FROM pengguna WHERE username = ?";
        
        try {
            PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(query);
            statement.setString(1, username);

            // Gunakan ResultSet untuk memeriksa apakah nama pengguna sudah ada
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username anda sudah terdaftar ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTabelKantin(String namaKantin)  {
        Statement statement;
        try {
            statement = DatabaseManager.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS kantin (ID_Kantin int PRIMARY KEY AUTO_INCREMENT, Nama_Kantin varchar(255) NOT NULL UNIQUE)");
            insertDataIntoKantinTable(namaKantin);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    private void insertKantin(String namaKantin) {
    String queryInsertKantin = "INSERT INTO kantin (Nama_Kantin) VALUES (?)";
    try {
        PreparedStatement stmt = DatabaseManager.getConnection().prepareStatement(queryInsertKantin);
        stmt.setString(1, namaKantin);
        stmt.executeUpdate();
        System.out.println("Data kantin berhasil ditambahkan ke dalam tabel kantin.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void createTabelPengelolaKantin() {
        Statement statement;
        try {
            statement = (Statement) DatabaseManager.getConnection().createStatement();
            statement.execute(" CREATE TABLE IF NOT EXISTS pengelola_kantin ( ID_Pengelola int PRIMARY KEY AUTO_INCREMENT, Nama_Kantin varchar(255) NOT NULL, ID_Kantin int, Pembayaran DECIMAL(10, 2) NOT NULL, FOREIGN KEY (ID_Kantin) REFERENCES kantin(ID_Kantin))");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}   


    

