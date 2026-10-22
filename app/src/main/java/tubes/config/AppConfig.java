package tubes.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();

    static {
        // Membaca file db.properties dari root project
        try (InputStream input = new FileInputStream("db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Gagal memuat db.properties! Pastikan file berada di root project.");
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        String host = properties.getProperty("db.host");
        String port = properties.getProperty("db.port");
        String dbName = properties.getProperty("db.name");

        // PostgreSQL JDBC URL dengan SSL mode require untuk Supabase
        return String.format("jdbc:postgresql://%s:%s/%s?sslmode=require", host, port, dbName);
    }

    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}