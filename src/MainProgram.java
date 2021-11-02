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

            Integer NumberOfEvents = 0;
            Integer NumberOfClients = 0;
            Integer LineNumber = 1;
            while (inputFile.hasNext()) {
                if (LineNumber == 1) {
                    NumberOfClients = Integer.parseInt(inputFile.nextLine().trim());
                    LineNumber++;
                }

                if (LineNumber == 2) {
                    NumberOfEvents = Integer.parseInt(inputFile.nextLine().trim());
                    LineNumber++;
                }

                if (LineNumber > 2 && LineNumber < (3 + NumberOfClients * 2)) {
                    boolean toggleEventInfo = false;
                    String eventName = inputFile.nextLine().trim();
                    if (toggleEventInfo == true) {
                        Event event = new Event(eventName, Integer.parseInt(inputFile.nextLine().trim()));
                        toggleEventInfo = false;
                    }
                    else {
                        toggleEventInfo = true;
                    }

                }
            }
        }
        catch (FileNotFoundException ex) {

        }
        catch (Exception ex) {

        }
    }
}
