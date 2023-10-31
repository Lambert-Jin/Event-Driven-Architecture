package tributary.rebalanced;

import tributary.Consumer;
import tributary.Partition;

import java.util.List;

public interface RebalancingStrategy<T> {
    void rebalance(List<Consumer<T>> consumers, List<Partition<T>> partitions);
}
