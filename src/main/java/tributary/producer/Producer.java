package tributary.producer;

import tributary.Event;
import tributary.Partition;
import tributary.Topic;
import tributary.allocation.AllocationStrategy;

import java.util.List;
import java.util.UUID;

public class Producer<T> {
    private final Class<T> valueType;
    private String id;
    private AllocationStrategy<T> allocationStrategy;

    public Producer(String id, AllocationStrategy<T> allocationStrategy, Class<T> valueType) {
        this.id = id;
        this.allocationStrategy = allocationStrategy;
        this.valueType = valueType;
    }

    public void produce(Topic<T> topic, Event<T> event) {
        List<Partition<T>> partitionList = topic.getPartitions();
        Partition<T> partition = allocationStrategy.allocate(event, partitionList);
        partition.addEvent(event);
    }

    public Class<T> getValueType() {
        return valueType;
    }
}
