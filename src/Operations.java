import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

public class Operations {

    SortedArrayList<Client> clientList = new SortedArrayList<>();
    SortedArrayList<Event> eventList = new SortedArrayList<>();
    SortedArrayList<Booking> bookings = new SortedArrayList<>();

    public Operations() {

    }

    public void StartApplication() {
        System.out.println("Welcome to Winter Olympics Ticketing App\n" +
                "Please choose an option from below to perform an action:\n" +
                "b - Book a ticket\n" +
                "c - Display information for all clients\n" +
                "e - Display information for all events\n" +
                "f - Exit program\n" +
                "r - Cancel a ticket");

        Scanner userInput = new Scanner(System.in);

        try {
            String userActionInput = userInput.nextLine();

            switch (userActionInput.toLowerCase()) {
                case "b":
                    BookTickets();
                    break;
                case "c":
                    break;
                case "e":
                    DisplayEventInfo();
                    break;
                case "f":
                    System.out.println("Thank you for using Winter Olympics Ticketing App");
                    System.exit(0);
                    break;
                case  "r":
                    break;
                default:
                    break;
            }
        }
        catch (Exception ex) {
            System.out.println("This is an invalid input. Please enter a valid input.\n");
        }
    }

    public void ReadFile() {
        try {
            FileReader inputFileReader = new FileReader("./src/input.txt");
            Scanner inputFile = new Scanner(inputFileReader);

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
                    Client client = new Client(clientName[0], clientName[1], (NumberOfClients - (clientList.size() - 1)));
                    clientList.add(client);
                }
            }
        }
        catch (FileNotFoundException ex) {

        }
    }

    public void DisplayEventInfo() {
        System.out.println("All Events");
        for (Event event:
             eventList) {
            System.out.println("Event Name: " + event.GetEventName() + "\n" +
                                "Tickets Available: " + event.GetRemainingTickets() + "\n");
        }
    }

    public void BookTickets() {
        System.out.println("Which client from the below do you wish to book the ticket for?");
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("Client Id: " + i + " Client Name: " + clientList.get(i).GetClientName());
        }
        System.out.println("Please enter the integer Client Id");

        Scanner userInput = new Scanner(System.in);

        try {
            Integer clientId = Integer.parseInt(userInput.nextLine());

            if (clientId > 0 && clientId <= clientList.size()) {
                BookEventForClient(clientId);
            }
            else {
                System.out.println("Invalid Client Id. Please try again.");

            }
        }
        catch (Exception ex) {

        }
    }

    public void BookEventForClient(Integer clientId) {
        String clientName = clientList.get(clientId).GetClientName();
        System.out.println("Booked Event Tickets for " + clientName);
        for (Booking booking:
             bookings) {
            if (booking.GetClientId().equals(clientId)) {
                System.out.println("Event Name: " + booking.GetEventName() +
                                " Number of Tickets Booked: " + booking.GetNumberOfTicketsBooked());
            }
        }
        System.out.println("Do you wish to update an existing booking or book a new event?");
    }
}
