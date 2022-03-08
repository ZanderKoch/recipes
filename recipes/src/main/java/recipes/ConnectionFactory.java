package recipes;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class for getting connections to the database
 * @author Zander Koch
 */
public class ConnectionFactory{
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        System.out.println("ConnectionFactory.getConnection(): "
                + "attempting to get database connection");
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();

        String user = Util.getProperty(PropKey.DB_USER);
        String password = Util.getProperty(PropKey.DB_PASSWORD);
        String url = Util.getProperty(PropKey.DB_URL);
        
        return (Connection) DriverManager.getConnection(url, user, password);
    }
}
