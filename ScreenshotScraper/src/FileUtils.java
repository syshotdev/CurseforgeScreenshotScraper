import java.io.*;
import java.util.ArrayList;

public class FileUtils {
    public static ArrayList<File> getFilesFromPaths(ArrayList<String> paths) {
        ArrayList<File> files = new ArrayList<File>();

        for (String path : paths) {
            files.add(new File(path));
        }

        return files;
    }

    public static String addBackslashToEnd(String string) {
        if (string.endsWith("\\")) {
            return string;
        } else {
            return string + "\\";
        }
    }

    public static void copyFiles(ArrayList<File> files, String destination) {
        for (File file : files) {
            String filePath = addBackslashToEnd(destination) + addBackslashToEnd(file.getName());

            File fileDestination = new File(filePath);

            copyFile(file, fileDestination);
        }
    }

    private static void copyFile(File source, File destination) {
        try (InputStream in = new BufferedInputStream(new FileInputStream(source));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))) {

            byte[] buffer = new byte[1024]; // Buffer for chunks of data
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}