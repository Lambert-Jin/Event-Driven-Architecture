package tributary;

import java.time.LocalDateTime;

public class Message<T> {
    private LocalDateTime datetimeCreated;
    private String id;
    private String key;
    private T value;

    public Message(String id, String payloadType, String key, T value) {
        this.datetimeCreated = LocalDateTime.now();
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public LocalDateTime getDatetimeCreated() {
        return datetimeCreated;
    }

    public String getId() {
        return id;
    }


    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
