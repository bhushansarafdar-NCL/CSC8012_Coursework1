public class Booking {

    public Integer ClientId;

    public String EventName;

    public Integer BookedTicket;

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
}
