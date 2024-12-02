package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop;

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Файл config.properties не найден");
            }
            prop = new Properties();
            prop.load(new InputStreamReader(input, StandardCharsets.UTF_8));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }

    public static String getUsername() {
        return prop.getProperty("username");
    }

    public static String getPassword() {
        return prop.getProperty("password");
    }

    public static String getCourseName() {
        return prop.getProperty("courseName");
    }

    public static String getCsvFilePath() {
        return prop.getProperty("csvFilePath");
    }
}

