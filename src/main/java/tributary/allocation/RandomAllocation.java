package tributary.allocation;

import tributary.Event;
import tributary.Message;
import tributary.Partition;

import java.util.List;
import java.util.Random;

public class RandomAllocation<T> implements AllocationStrategy<T> {
    @Override
    public Partition<T> allocate(Event<T> message, List<Partition<T>> partitions) {
        int randomIndex = new Random().nextInt(partitions.size());
        return partitions.get(randomIndex);
    }
}
