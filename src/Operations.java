import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Operations {

    SortedArrayList<Client> clientList = new SortedArrayList<>();
    SortedArrayList<Event> eventList = new SortedArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    public Operations() {

    }

    public void StartApplication() {
        System.out.println("\n----------------------------------------------------------------" +
                "\nWelcome to Winter Olympics Ticketing App\n" +
                "Please choose an option from below to perform an action:\n" +
                "b - Book a ticket\n" +
                "c - Display information for all clients\n" +
                "e - Display information for all events\n" +
                "f - Exit program\n" +
                "r - Cancel a ticket\n" +
                "----------------------------------------------------------------");

        Scanner userInput = new Scanner(System.in);

        try {
            String userActionInput = userInput.nextLine();

            switch (userActionInput.toLowerCase()) {
                case "b":
                    BookTickets();
                    break;
                case "c":
                    DisplayClientInfo();
                    break;
                case "e":
                    DisplayEventInfo(false);
                    break;
                case "f":
                    System.out.println("Thank you for using Winter Olympics Ticketing App");
                    System.exit(0);
                    break;
                case "r":
                    CancelTickets();
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
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
                    Event event = new Event(eventName, Integer.parseInt(inputFile.nextLine().trim()), (eventList.size() + 1));
                    eventList.addItem(event);
                    LineNumber = LineNumber + 2;
                }

                if (LineNumber > ((2 * NumberOfEvents) + 2) && clientList.size() < NumberOfClients) {
                    String clientName[] = inputFile.nextLine().trim().split(" ");
                    Client client = new Client(clientName[0], clientName[1], (clientList.size() + 1));
                    clientList.addItem(client);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File input.txt was not found in the directory. Please contact support.");
            System.exit(0);
        }
    }

    public void DisplayEventInfo(Boolean isBooking) {
        System.out.println("All Events\n");
        for (Event event :
                eventList) {
            System.out.println("\nEvent ID: " + event.GetEventId() +
                    "\nEvent Name: " + event.GetEventName() +
                    "\nTickets Available: " + event.GetRemainingTickets());
        }

        if (!isBooking) {
            StartApplication();
        }
    }

    public void BookTickets() {
        System.out.println("Which client from the below do you wish to book the ticket for?");
        ShowClientListForSelection();
        Scanner userInput = new Scanner(System.in);

        try {
            SelectClient(userInput, false);
        } catch (Exception ex) {
            System.out.println("Invalid Client Id. Please try again.");
            SelectClient(userInput, false);
        }
    }

    private void ShowClientListForSelection() {
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("Client Id: " + clientList.get(i).GetClientId() + " Client Name: " + clientList.get(i).GetClientName());
        }
        System.out.println("Please enter the integer Client Id");
    }

    private void SelectClient(Scanner userInput, Boolean isCancellation) {
        Integer clientId = Integer.parseInt(userInput.nextLine());

        if (clientId > 0 && clientId <= clientList.size()) {
            if (isCancellation) {
                CancelEventForClient(clientId);
            } else {
                BookEventForClient(clientId);
            }
        } else {
            System.out.println("Invalid Client Id. Please try again.");
            SelectClient(userInput, isCancellation);
        }
    }

    public void BookEventForClient(Integer clientId) {
        String clientName = clientList.stream().filter(e -> e.GetClientId() == clientId).findFirst().get().GetClientName();
        System.out.println("Booked Event Tickets for " + clientName);
        Integer NumberOfEventsBookedByClient = 0;
        for (Booking booking :
                bookings) {
            if (booking.GetClientId().equals(clientId)) {
                NumberOfEventsBookedByClient++;
                System.out.println("Event Name: " + eventList.stream().filter(e -> e.GetEventId() == booking.GetEventId()).findFirst().get().GetEventName() +
                        " Number of Tickets Booked: " + booking.GetNumberOfTicketsBooked());
            }
        }
        if (NumberOfEventsBookedByClient == 0) {
            System.out.println("No events booked.");
        }

        BookEvent(clientId, false);
    }

    private void BookEvent(Integer clientId, boolean isInvalidInput) {
        if (!isInvalidInput) {
            System.out.println("Do you wish to book an event? (y/n)");
        }

        Scanner input = new Scanner(System.in);
        switch (input.nextLine().toLowerCase()) {
            case "y":
                DisplayEventInfo(true);
                System.out.println("Please input integer value Event Id of Event to be booked");

                Integer eventId = Integer.parseInt(input.nextLine());
                if (eventId > 0 && eventId <= eventList.size()) {
                    if (eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetRemainingTickets() > 0) {
                        System.out.println("Please enter number of tickets to be booked.");

                        Integer NumberOfTickets = Integer.parseInt(input.nextLine());
                        if (NumberOfTickets > 0 && eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetRemainingTickets() >= NumberOfTickets) {
                            BookTicket(clientId, eventId, NumberOfTickets);
                            BookEvent(clientId, false);
                        } else {
                            System.out.printf("\nOnly %d tickets available. Do you wish to book the remaining tickets? (y/n)", eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetRemainingTickets());
                            switch (input.nextLine().toLowerCase()) {
                                case "y":
                                    BookTicket(clientId, eventId, eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetRemainingTickets());
                                    BookEvent(clientId, false);
                                    break;
                                case "n":
                                    BookEvent(clientId, false);
                                    break;
                                default:
                                    System.out.println("Invalid input. Please enter a valid response. (y/n)");
                                    BookEvent(clientId, true);
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Event is fully booked. Please book another event.");
                        BookEvent(clientId, false);
                    }
                }
                else {
                    System.out.println("Invalid Event Id entered. Please enter again.");
                    BookEvent(clientId, true);
                }
                break;
            case "n":
                StartApplication();
                break;
            default:
                System.out.println("Invalid input. Please enter a valid response. (y/n)");
                BookEvent(clientId, true);
                break;
        }
    }

    private void DisplayClientInfo() {
        for (Client client :
                clientList) {
            System.out.println("\nClient Name: " + client.GetClientName() + "\nEvents Booked :-");
            Integer NoOfBookings = 0;
            for (Booking booking :
                    bookings) {
                if (booking.GetClientId().equals(client.GetClientId())) {
                    NoOfBookings++;
                    System.out.println("Event Name: " + eventList.stream().filter(e -> e.GetEventId() == booking.GetEventId()).findFirst().get().GetEventName() + "\n Number of Tickets Booked: " + booking.GetNumberOfTicketsBooked());
                }
            }

            if (NoOfBookings == 0) {
                System.out.printf(" No Events booked.");
            }
        }
        StartApplication();
    }

    private void BookTicket(Integer clientId, Integer eventId, Integer numberOfTickets) {
        Booking clientBooking = FindClientBooking(clientId, eventId);
        if (clientBooking == null) {
            Booking newBooking = new Booking(clientId, eventId, numberOfTickets);
            bookings.add(newBooking);

            Event event = eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get();
            event.SetRemainingTickets(event.GetRemainingTickets() - numberOfTickets, true);

            WriteLetterForClient("Ticket Booked for " + clientList.stream().filter(e -> e.GetClientId() == clientId).findFirst().get().GetClientName() +
                    "\nEvent Name: " + eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetEventName() +
                    "\nNumber of Tickets: " + numberOfTickets);
        } else {
            clientBooking.AddTickets(numberOfTickets);

            Event event = eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get();
            event.SetRemainingTickets(event.GetRemainingTickets() - numberOfTickets, true);

            WriteLetterForClient("Ticket Booked for " + clientList.stream().filter(e -> e.GetClientId() == clientId).findFirst().get().GetClientName() +
                    "\nEvent Name: " + eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetEventName() +
                    "\nTotal Number of Tickets: " + clientBooking.GetNumberOfTicketsBooked());
        }
    }

    private void CancelTicket(Integer clientId, Integer eventId, Integer numberOfTickets) {
        Booking clientBooking = FindClientBooking(clientId, eventId);
        Event event = eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get();
        if (clientBooking != null) {
            clientBooking.CancelTickets(numberOfTickets);

            if (clientBooking.GetNumberOfTicketsBooked() == 0) {
                Booking booking = new Booking();
                for (int index = 0; index < bookings.size(); index++) {
                    if (bookings.get(index).GetEventId() == eventId && bookings.get(index).GetClientId() == clientId) {
                        booking = bookings.remove(index);
                    }
                }

                event.SetRemainingTickets(event.GetRemainingTickets() + booking.GetNumberOfTicketsBooked(), false);

                WriteLetterForClient("Ticket Cancelled for " + clientList.stream().filter(e -> e.GetClientId() == clientId).findFirst().get().GetClientName() +
                        "\nEvent Name: " + eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetEventName() +
                        "\nNumber of Tickets: " + numberOfTickets);
            } else {
                event.SetRemainingTickets(event.GetRemainingTickets() + numberOfTickets, false);

                WriteLetterForClient("Ticket Cancelled for " + clientList.stream().filter(e -> e.GetClientId() == clientId).findFirst().get().GetClientName() +
                        "\nEvent Name: " + eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetEventName() +
                        "\nNumber of Tickets Cancelled: " + numberOfTickets +
                        "\nNumber of Booked Tickets Remaining: " + FindClientBooking(clientId, eventId).GetNumberOfTicketsBooked());
            }
        } else {
            System.out.println("No booking for the event found for the client. " +
                    "You have to book an event to be able to cancel it." +
                    "\nDo you wish to cancel another event?");
            CancelEvent(clientId, true);
        }
    }

    private Booking FindClientBooking(Integer clientId, Integer eventId) {
        Booking clientBookingToBeeUpdated = null;

        for (Booking booking :
                bookings) {
            if (booking.GetClientId() == clientId && booking.GetEventId() == eventId) {
                clientBookingToBeeUpdated = booking;
                break;
            }
        }

        return clientBookingToBeeUpdated;
    }

    private void CancelTickets() {
        System.out.println("Which client from the below do you wish to cancel the ticket for?");
        ShowClientListForSelection();
        Scanner userInput = new Scanner(System.in);

        try {
            SelectClient(userInput, true);
        } catch (Exception ex) {
            System.out.println("Invalid Client Id. Please try again.");
            SelectClient(userInput, true);
        }
    }

    private void CancelEventForClient(Integer clientId) {
        String clientName = clientList.stream().filter(e -> e.GetClientId() == clientId).findFirst().get().GetClientName();
        System.out.println("Booked Event Tickets for " + clientName);
        Integer NumberOfEventsBookedByClient = 0;
        for (Booking booking :
                bookings) {
            if (booking.GetClientId().equals(clientId)) {
                NumberOfEventsBookedByClient++;
                System.out.println("Event Name: " + eventList.stream().filter(e -> e.GetEventId() == booking.GetEventId()).findFirst().get().GetEventName() +
                        " Number of Tickets Booked: " + booking.GetNumberOfTicketsBooked());
            }
        }
        if (NumberOfEventsBookedByClient == 0) {
            System.out.println("No events booked.");
        }

        CancelEvent(clientId, false);
    }

    private void CancelEvent(Integer clientId, Boolean isInvalidInput) {
        if (!isInvalidInput) {
            System.out.println("Do you wish to cancel an event? (y/n)");
        }

        Scanner input = new Scanner(System.in);
        switch (input.nextLine().toLowerCase()) {
            case "y":
                DisplayEventInfo(true);
                System.out.println("Please input integer value Event Id of Event to be cancelled");

                Integer eventId = Integer.parseInt(input.nextLine());
                if (eventId > 0 && eventId <= eventList.size()) {
                    System.out.println("Please enter number of tickets to be cancelled.");

                    Integer NumberOfTickets = Integer.parseInt(input.nextLine());
                    Integer NumberOfTicketsBookedForEvent = 0;
                     NumberOfTicketsBookedForEvent = bookings.stream().filter(b -> b.GetEventId() == eventId && b.GetClientId() == clientId).findFirst().get().GetNumberOfTicketsBooked();
                    if (NumberOfTickets > 0 && NumberOfTicketsBookedForEvent >= NumberOfTickets) {
                        CancelTicket(clientId, eventId, NumberOfTickets);
                        CancelEvent(clientId, false);
                    } else {
                        System.out.printf("\nOnly %d tickets booked for event. Do you wish to cancel all the remaining tickets? (y/n)", NumberOfTicketsBookedForEvent);
                        switch (input.nextLine().toLowerCase()) {
                            case "y":
                                CancelTicket(clientId, eventId, eventList.stream().filter(e -> e.GetEventId() == eventId).findFirst().get().GetRemainingTickets());
                                CancelEvent(clientId, false);
                                break;
                            case "n":
                                CancelEvent(clientId, false);
                                break;
                            default:
                                System.out.println("Invalid input. Please enter a valid response. (y/n)");
                                CancelEvent(clientId, true);
                                break;
                        }
                    }
                }
                break;
            case "n":
                StartApplication();
                break;
            default:
                System.out.println("Invalid input. Please enter a valid response. (y/n)");
                CancelEvent(clientId, true);
                break;
        }
    }

    private void WriteLetterForClient(String text) {
        try {
            File letterFile = new File("./src/letter.txt");
            if (!letterFile.exists()) {
                letterFile.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(letterFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printFile = new PrintWriter(bufferedWriter);

            printFile.println("\n=========================================================" +
                    "\n@@  Winter Olympics Booking/Cancellation Confirmation   @@  \n");
            printFile.println(text);
            printFile.println("-----------------------------------------------------------\n");

            printFile.close();
        } catch (FileNotFoundException
                ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
