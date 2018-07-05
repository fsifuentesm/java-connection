package dbsapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Value;
import com.ibm.as400.access.AS400JDBCDriver;
import org.springframework.core.env.Environment;


public class ConnectionSingleton {

    private Connection connection = null;

    private static ConnectionSingleton instance = null;

    private ConnectionSingleton() {}

    public static synchronized ConnectionSingleton getInstance() throws SQLException {
        if (instance == null) {
            DriverManager.registerDriver(new AS400JDBCDriver());

            instance = new ConnectionSingleton();
        }

        return instance;
    }

    public Connection getConnection(Environment env) throws SQLException {
        if (connection == null) {
            DriverManager.registerDriver(new AS400JDBCDriver());

            connection = DriverManager.getConnection(
                env.getProperty("database.url"),
                env.getProperty("database.user"),
                env.getProperty("database.password")
            );
        }

        return connection;
    }

}
