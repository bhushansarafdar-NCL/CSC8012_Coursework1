public class Event implements Comparable<Event> {

    private Integer EventId;

    private String EventName;

    private Integer RemainingTickets;

    public Event(String eventName, Integer remainingTickets, Integer eventId) {
        super();
        EventId = eventId;
        EventName = eventName;
        RemainingTickets = remainingTickets;
    }

    @Override
    public int compareTo(Event event) {
        int eCmp = EventName.compareTo(event.EventName);
        if (eCmp != 0) {
            return eCmp;
        }
        else {
            return 0;
        }
    }

    public void SetRemainingTickets(Integer remaningTickets, Boolean isBooking) {
        RemainingTickets = remaningTickets;

        if (isBooking) {
            System.out.println("Tickets Booked Successfully!");
        }
        else {
            System.out.println("Tickets Cancelled Successfully!");
        }
    }

    public String GetEventName() {
        return EventName;
    }

    public Integer GetRemainingTickets() {
        return RemainingTickets;
    }

    public Integer GetEventId() {
        return  EventId;
    }
}
