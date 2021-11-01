import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        try {
            FileReader inputFileReader = new FileReader("./src/input.txt");
            Scanner inputFile = new Scanner(inputFileReader);
            ArrayList<Client> clientList = new ArrayList<>();
            ArrayList<Event> eventList = new ArrayList<>();

            while (inputFile.hasNext()) {
                
            }
        }
        catch (FileNotFoundException ex) {

        }
        catch (Exception ex) {

        }
    }
}
