package DataBaseLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static TGConstant.Constant.*;

public class DataBaseConnection {


    public static Connection dbConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void updateDb(String sqlStmt) throws SQLException {
        try (Connection dbConnection = dbConnection(); Statement statement = dbConnection.createStatement()) {
            statement.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
