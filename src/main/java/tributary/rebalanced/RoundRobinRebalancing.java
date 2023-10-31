package tributary.rebalanced;

import tributary.Consumer;
import tributary.Partition;

import java.util.List;


public class RoundRobinRebalancing<T> implements RebalancingStrategy<T> {
    @Override
    public void rebalance(List<Consumer<T>> consumers, List<Partition<T>> partitions) {
        int numConsumers = consumers.size();
        int numPartitions = partitions.size();

        for (int i = 0; i < numPartitions; i++) {
            int consumerIndex = i % numConsumers;
            consumers.get(consumerIndex).assignPartition(partitions.get(i));
        }
    }
}

