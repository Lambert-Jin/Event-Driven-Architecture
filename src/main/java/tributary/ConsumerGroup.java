package tributary;

import tributary.rebalanced.RebalancingStrategy;
import tributary.rebalanced.RoundRobinRebalancing;

import java.util.ArrayList;
import java.util.List;

public class ConsumerGroup<T> {
    private String id;
    private List<Consumer<T>> consumers;
    private Topic<T> topic;
    private RebalancingStrategy<T> rebalancingStrategy;

    public ConsumerGroup(String id, Topic<T> topic, String rebalancingMethod) {
        this.id = id;
        this.topic = topic;
        this.consumers = new ArrayList<>();

        if (rebalancingMethod.equals("Range")) {
            this.rebalancingStrategy = new RangeRebalancing<>();
        } else if (rebalancingMethod.equals("RoundRobin")) {
            this.rebalancingStrategy = new RoundRobinRebalancing<>();
        } else {
            throw new IllegalArgumentException("Unknown rebalancing method");
        }
    }

    public void addConsumer(Consumer<T> consumer) {
        consumers.add(consumer);
        rebalance();
    }

    public void removeConsumer(String consumerId) {
        consumers.removeIf(consumer -> consumer.getId().equals(consumerId));
        rebalance();
    }

    public void rebalance() {
        List<Partition<T>> partitions = topic.getPartitions();
        rebalancingStrategy.rebalance(consumers, partitions);
    }

    public void changeRebalancingStrategy(String newStrategy) {
        if (newStrategy.equals("Range")) {
            this.rebalancingStrategy = new RangeRebalancing<>();
        } else if (newStrategy.equals("RoundRobin")) {
            this.rebalancingStrategy = new RoundRobinRebalancing<>();
        } else {
            throw new IllegalArgumentException("Unknown rebalancing method");
        }
    }

    public boolean hasConsumer(String consumerId) {
        return consumers.stream().anyMatch(consumer -> consumer.getId().equals(consumerId));
    }
}
