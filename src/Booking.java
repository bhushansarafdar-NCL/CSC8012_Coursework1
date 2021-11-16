public class Booking {

    private Integer ClientId;

    private String EventName;

    private Integer BookedTicket;

    public Booking(Integer clientId, String eventName, Integer bookedTicket) {
        ClientId = clientId;
        EventName = eventName;
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

    public String GetEventName() {
        return EventName;
    }

    public Integer GetNumberOfTicketsBooked() {
        return BookedTicket;
    }
}
