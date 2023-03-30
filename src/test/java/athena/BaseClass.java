package athena;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseClass {

    public String url;
    public String ClientId;
    public String SecretKey;
    public String bearerAdminToken = null;
    Properties properties;

    public String getURL() throws IOException {
        properties = new Properties();
        String userdir = System.getProperty("user.dir");
        String propertydir = "\\src\\test\\java\\athena\\global.properties";
        FileInputStream fileInputStream = new FileInputStream(userdir + propertydir);
        properties.load(fileInputStream);
        url = properties.getProperty("url");
        return url;
    }

    public static String getGeneratedString(String file) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\payloads\\" + file;
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

}
