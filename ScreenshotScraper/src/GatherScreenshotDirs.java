import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GatherScreenshotDirs {
    String parentFolder;

    GatherScreenshotDirs(String parentFolder) {
        this.parentFolder = parentFolder;
    }

    public ArrayList<String> getScreenshotStrings() {
        // Output strings of all screenshots
        ArrayList<String> strings = new ArrayList<>();
        File parentFile = new File(parentFolder);

        // Grab all the mc instance paths
        ArrayList<String> mcInstancePaths = new ArrayList<>(Arrays.asList(getAbsolutePathsFromDirectory(parentFile.getAbsolutePath())));

        // Grab all the mc instances and get the screenshot folder
        for (String mcInstancePath : mcInstancePaths) {
            if (pathHasScreenshotsFolder(mcInstancePath)) {
                // Get screenshots
                ArrayList<String> screenshotPaths = findScreenshotPathsFromFolder(mcInstancePath + "\\screenshots\\");

                strings.addAll(screenshotPaths);
            }
        }

        return strings;
    }

    private boolean pathHasScreenshotsFolder(String path) {
        var paths = getLocalPathsFromDirectory(path);

        // Look at every path and if it is screenshots say yeah it is screenshots folder
        boolean hasScreenshotsFolder = Arrays.asList(Objects.requireNonNull(paths)).contains("screenshots");
        return hasScreenshotsFolder;
    }


    private ArrayList<String> findScreenshotPathsFromFolder(String parentPath) {
        var paths = getAbsolutePathsFromDirectory(parentPath);
        ArrayList<String> outStrings = new ArrayList<>();

        for (String path : paths) {
            // If path doesn't end with png, don't add it
            if (!path.endsWith(".png")) {
                continue;
            }

            outStrings.add(path);
        }

        return outStrings;
    }


    private String[] getLocalPathsFromDirectory(String path){
        File file = new File(path);
        return file.list();
    }

    private String[] getAbsolutePathsFromDirectory(String path) {
        File file = new File(path);

        String[] localFilePaths = file.list();

        // If localFiles don't exist, return nothing
        if(localFilePaths == null){
            return new String[0];
        }

        String[] absoluteFilePaths = new String[localFilePaths.length];

        // For every local file path, add the path to the start
        for (int i = 0; i < absoluteFilePaths.length; i++){
            absoluteFilePaths[i] = path + "\\" + localFilePaths[i];
        }
        return absoluteFilePaths;
    }
}