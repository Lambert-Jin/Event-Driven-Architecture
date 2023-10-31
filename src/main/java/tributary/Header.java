package tributary;

import java.time.LocalDateTime;

public class Header {
    private LocalDateTime created;
    private String id;
    private String payloadType;

    public Header(String id, String payloadType) {
        this.created = LocalDateTime.now();
        this.id = id;
        this.payloadType = payloadType;
    }
}
