package tributary;

import tributary.rebalanced.RebalancingStrategy;

import java.util.List;

public class RangeRebalancing<T> implements RebalancingStrategy<T> {
    @Override
    public void rebalance(List<Consumer<T>> consumers, List<Partition<T>> partitions) {
        int numConsumers = consumers.size();
        int numPartitions = partitions.size();
        int partitionsPerConsumer = numPartitions / numConsumers;
        int extraPartitions = numPartitions % numConsumers;

        int partitionIndex = 0;
        for (int i = 0; i < numConsumers; i++) {
            int partitionsToAssign = partitionsPerConsumer + (i < extraPartitions ? 1 : 0);
            for (int j = 0; j < partitionsToAssign; j++) {
                consumers.get(i).assignPartition(partitions.get(partitionIndex++));
            }
        }
    }
}
