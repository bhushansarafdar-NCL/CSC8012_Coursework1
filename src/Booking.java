import java.awt.print.Book;

public class Booking {

    private Integer ClientId;

    private Integer EventId;

    private Integer BookedTicket;

    public Booking() {

    }
    public Booking(Integer clientId, Integer eventId, Integer bookedTicket) {
        ClientId = clientId;
        EventId = eventId;
        BookedTicket = bookedTicket;
    }

    public void AddTickets(Integer numberOfTicketsTobeAdded) {
        BookedTicket = BookedTicket + numberOfTicketsTobeAdded;
    }

    public void CancelTickets(Integer numberOfTicketsTobeCancelled) {
        BookedTicket = BookedTicket - numberOfTicketsTobeCancelled;
    }

    public Integer GetClientId() {
        return ClientId;
    }

    public Integer GetEventId() {
        return EventId;
    }

    public Integer GetNumberOfTicketsBooked() {
        return BookedTicket;
    }
}
