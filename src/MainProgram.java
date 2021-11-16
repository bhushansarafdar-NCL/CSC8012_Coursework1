import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        try {
            FileReader inputFileReader = new FileReader("./src/input.txt");
            Scanner inputFile = new Scanner(inputFileReader);
            SortedArrayList<Client> clientList = new SortedArrayList<>();
            SortedArrayList<Event> eventList = new SortedArrayList<>();

            Integer NumberOfEvents = 0;
            Integer NumberOfClients = 0;
            Integer LineNumber = 1;

            while (inputFile.hasNext()) {
                if (LineNumber == 1) {
                    NumberOfEvents = Integer.parseInt(inputFile.nextLine().trim());
                    LineNumber++;
                }

                if (LineNumber == ((2 * NumberOfEvents) + 2)) {
                    NumberOfClients = Integer.parseInt(inputFile.nextLine().trim());
                    LineNumber++;
                }

                if (LineNumber > 1 && LineNumber < (3 + NumberOfEvents * 2)) {
                    String eventName = inputFile.nextLine().trim();
                    Event event = new Event(eventName, Integer.parseInt(inputFile.nextLine().trim()));
                    eventList.add(event);
                    LineNumber = LineNumber + 2;
                }

                if (LineNumber > ((2 * NumberOfEvents) + 2) && clientList.size() < NumberOfClients) {
                    String clientName[] = inputFile.nextLine().trim().split(" ");
                    Client client = new Client(clientName[0], clientName[1]);
                    clientList.add(client);
                }
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
