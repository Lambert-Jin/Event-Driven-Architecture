package tributary;

import java.time.LocalDateTime;

public class Event<T> {
    private Header header;
    private String partitionId;
    private T value;

    public Event(String id, String payloadType, String partitionId, T value) {
        this.header = new Header(id, payloadType);
        this.partitionId = partitionId;
        this.value = value;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void consume() {
        System.out.println(value.toString());
    }

    public T getValue() {
        return value;
    }
}
