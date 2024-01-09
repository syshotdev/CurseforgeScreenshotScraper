import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("To quit, exit out of Command Prompt (too lazy to code exit sequence)");

        // User inputs
        String mcInstanceFolder = askQuestionUntilAnswerValid("Curseforge instance path: (looks like 'C:\\Users\\name\\curseforge\\minecraft\\instances')", input);
        System.out.println(mcInstanceFolder);

        String outputFolder = askQuestionUntilAnswerValid("Folder to output screenshots to:", input);
        System.out.println(outputFolder);


        // Init the gatherer
        GatherScreenshotDirs gatherer;
        gatherer = new GatherScreenshotDirs(FileUtils.addBackslashToEnd(mcInstanceFolder));


        // Get the screenshot paths
        ArrayList<String> screenshotPaths = gatherer.getScreenshotStrings();
        printPaths(screenshotPaths);
        System.out.println("Amount of screenshots: " + screenshotPaths.size());


        // Write the screenshots
        System.out.println("Writing screenshots to folder...");

        var files = FileUtils.getFilesFromPaths(screenshotPaths);
        FileUtils.copyFiles(files, outputFolder);

        System.out.println("Done");
    }

    private static String askQuestionUntilAnswerValid(String question, Scanner scanner){
        String answer;

        do{
            answer = askQuestion(question, scanner);

            if(isValidFilePath(answer)){
                return answer;
            }else {
                System.out.println("Not a valid directory");
            }

        }while (true);
    }

    // Not every situation does it catch, but it will check if it has a colon and /
    public static boolean isValidFilePath(String string){
        if(!string.contains(":")){
            return false;
        } else return string.contains("\\");
    }

    private static void printPaths(ArrayList<String> paths){
        for(String path : paths){
            System.out.println(path);
        }
    }


    private static String askQuestion(String question, Scanner scanner){
        System.out.println(question);
        return scanner.next();
    }
}