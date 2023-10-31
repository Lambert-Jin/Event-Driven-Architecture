package tributary.allocation;

import tributary.Event;
import tributary.Message;
import tributary.Partition;

import java.util.List;

public class ManualAllocation<T> implements AllocationStrategy<T>{

    @Override
    public Partition<T> allocate(Event<T> event, List<Partition<T>> partitions) {
        int partitionId = Integer.parseInt(event.getPartitionId());
        return partitions.get(partitionId);
    }
}
