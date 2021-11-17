import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        try {
            Operations operations = new Operations();
            operations.ReadFile();
            operations.StartApplication();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
