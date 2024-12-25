package configProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnect {
    // init database constants
    private static final String DATABASE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    // init connection object
    private Connection connection;
    // init properties object
    private Properties properties;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", FileReaderManager.getInstance().getConfigReader().getUserDatabase());
            properties.setProperty("password", FileReaderManager.getInstance().getConfigReader().getPassDatabase());
            properties.setProperty("MaxPooledStatements", FileReaderManager.getInstance().getConfigReader().getMaxPool());
        }
        return properties;
    }

    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(FileReaderManager.getInstance().getConfigReader().getDatabaseUrl(), getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
