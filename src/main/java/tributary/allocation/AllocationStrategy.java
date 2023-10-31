package tributary.allocation;

import tributary.Event;
import tributary.Message;
import tributary.Partition;

import java.util.List;

public interface AllocationStrategy<T> {
    Partition<T> allocate(Event<T> message, List<Partition<T>> partitions);
}
