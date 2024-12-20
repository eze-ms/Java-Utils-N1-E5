package auxiliar;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Utils {

    //! Convierte un array de cadenas en una única cadena separada por espacios.
    public static String argsToString(String[] args) {
        return String.join(" ", args); // Variante simplificada de for.
    }

    //! Solicita al usuario o procesa una cadena para convertirla en un objeto Path válido.
    public static Path validatePath(String pathString) {
        if (pathString.isEmpty()) {
            System.out.println("Input the path:");
            pathString = new Scanner(System.in).nextLine(); // Solicita entrada manual.
        }

        try {
            Path path = Paths.get(pathString); // Convierte la cadena en Path.
            if (!Files.exists(path)) {
                System.out.println("Path not found.");
                return null;
            }
            if (!Files.isDirectory(path) && !Files.isRegularFile(path)) {
                System.out.println("The provided path is neither a directory nor a regular file.");
                return null;
            }
            return path; // Retorna el Path si es válido.
        } catch (InvalidPathException e) {
            System.out.println("Invalid path: " + e.getMessage());
            return null;
        }
    }

    //! Formatea la fecha de la última modificación de un archivo/directorio.
    public static String formatLastModified(long lastModified) {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(lastModified);
    }

    //! Serializa un objeto a un archivo .ser
    public static void serializeObject(String archivo, Serializable objeto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(objeto);
            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            System.out.println("Error serializing objecto: " + e.getMessage());
        }
    }

    //! Deserializa un objeto desde un archivo .ser
    public static Object deserializeObject(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al deserializar el objeto: " + e.getMessage());
        }
        return null;
    }
}
