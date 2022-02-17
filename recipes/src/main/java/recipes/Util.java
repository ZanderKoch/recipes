package recipes;

import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Zander Koch
 */
public class Util{
    /**
     * attempts to get value of given property
     * @param key
     * @return property's value if successful, <code>null</code> if not
     */
    public static String getProperty(PropKey key){
        System.out.println("");
        try{
            System.out.println("attempting to get property"
                    + key.toString()
                    + "from config file");
            
            //get inputstream linking to property file
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = classloader.getResourceAsStream("properties\\config.properties");
            
            //create a key-value-pair list and populate it from input stream
            Properties properties = new Properties();
            properties.load(inputStream);
            
            return properties.getProperty(key.toString());
        } 
        catch(Exception e) {
            System.out.println(e +  "while trying to read property \""
                    + key.toString() + "\" from config");
            return null;
        }
    }
}
