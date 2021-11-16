public class Event implements Comparable<Event> {

    private String EventName;

    private Integer RemainingTickets;

    public Event(String eventName, Integer remainingTickets) {
        super();
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

    public void SetRemainingTickets(Integer remaningTickets) {
        RemainingTickets = remaningTickets;
    }

    public String GetEventName() {
        return EventName;
    }

    public Integer GetRemainingTickets() {
        return RemainingTickets;
    }
}
