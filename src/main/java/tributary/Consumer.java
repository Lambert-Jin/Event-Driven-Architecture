package tributary;

import java.util.*;

public class Consumer<T> {
    private String id;
    private List<Partition<T>> partitions;

    private Map<Partition<T>, Integer> offsets = new HashMap<>();

    public Consumer(String id) {
        this.id = id;
        partitions = new ArrayList<>();
    }

    public void assignPartition(Partition<T> partition) {
        partitions.add(partition);
        offsets.put(partition, 0);
    }

    public void consume(Event<?> event) {
        event.consume();
    }

    public String getId() {
        return id;
    }
    public void setOffset(Partition<T> partition, int offset) {
        if (offsets.containsKey(partition)) {
            offsets.put(partition, offset);
        }
    }
}
