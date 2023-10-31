package tributary;

import java.util.LinkedList;
import java.util.List;

public class Partition<T> {
    private String id;
    private Topic<T> topic;
    private List<Event<T>> events;
    private int offset;

    public Partition(String id, Topic<T> topic) {
        this.id = id;
        this.topic = topic;
        this.events = new LinkedList<>();
        this.offset = 0;
    }

    public String getId() {
        return id;
    }

    public void addEvent(Event<T> event) {
        events.add(event);
    }

    public Event<T> consumeEvent() {
        int temp = offset;
        offset++;
        return events.get(temp);
    }

    public void playback(Consumer<?> consumer, int num) {
        for(int i = num; i < offset; i++){
            consumer.consume(events.get(num));
        }
    }
}
