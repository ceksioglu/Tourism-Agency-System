package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String URL = "jdbc:postgresql://localhost:5432/tourism_agency";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    //6. Projede veritabanı kullanılmış ve DB bağlantı konfigürasyonu doğru bir şekilde yapılmış mı?

    private DatabaseManager() {}

    //getInstance metodunun synchronized olması, aynı anda birden fazla iş parçacığının bu metodu çağırarak birden fazla örnek oluşturmaya çalışmasını engeller.

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
