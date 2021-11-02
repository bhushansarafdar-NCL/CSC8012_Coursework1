public class Event implements Comparable<Event> {

    private String EventName;

    private Integer RemainingTickets;

    public Event(String eventName, Integer remainingTickets) {
        EventName = eventName;
        RemainingTickets = remainingTickets;
    }

    public int compareTo(Event event) {
        int eCmp = EventName.compareTo(event.EventName);
        if (eCmp != 0) {
            return eCmp;
        }
        else {
            return 0;
        }
    }
}
