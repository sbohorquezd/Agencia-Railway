package infraestructura.persistencia;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static Connection instancia = null;
    private static String dbPath = null;

    private static String resolverRutaDB() {
        if (dbPath != null) return dbPath;

        // 1. Variable de entorno (para Railway con volumen persistente)
        String envPath = System.getenv("DB_PATH");
        if (envPath != null && !envPath.isEmpty()) {
            dbPath = envPath;
            System.out.println("Usando DB desde variable de entorno: " + dbPath);
            return dbPath;
        }

        // 2. Extraer el .db incluido en el JAR a directorio temporal
        try {
            File tmpDir = new File(System.getProperty("java.io.tmpdir"));
            File dbFile = new File(tmpDir, "Agencia_viajes.db");

            if (!dbFile.exists()) {
                InputStream is = Conexion.class.getClassLoader()
                        .getResourceAsStream("Agencia_viajes.db");
                if (is == null) {
                    throw new RuntimeException("No se encontro Agencia_viajes.db en el classpath");
                }
                OutputStream os = Files.newOutputStream(dbFile.toPath());
                is.transferTo(os);
                os.close();
                is.close();
                System.out.println("DB extraida del JAR a: " + dbFile.getAbsolutePath());
            } else {
                System.out.println("Usando DB existente en: " + dbFile.getAbsolutePath());
            }

            dbPath = dbFile.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Error extrayendo DB: " + e.getMessage());
            dbPath = System.getProperty("user.dir") + "/Agencia_viajes.db";
        }

        return dbPath;
    }

    public static Connection conectando() {
        try {
            if (instancia == null || instancia.isClosed()) {
                String cadena = "jdbc:sqlite:" + resolverRutaDB() + "?busy_timeout=5000";
                Class.forName("org.sqlite.JDBC");
                instancia = DriverManager.getConnection(cadena);
                instancia.setAutoCommit(true);
                System.out.println("Conexion SQLite establecida.");
            }
        } catch (Exception er) {
            System.err.println("Error de conexion: " + er.getMessage());
        }
        return instancia;
    }
}
