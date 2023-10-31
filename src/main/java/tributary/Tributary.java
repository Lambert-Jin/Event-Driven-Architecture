package tributary;

import tributary.allocation.AllocationStrategy;
import tributary.allocation.ManualAllocation;
import tributary.allocation.RandomAllocation;
import tributary.producer.Producer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Tributary {
    // Feel free to delete/change this file, this is just here to give directory structure.
    private Map<String, Topic<?>> topics = new HashMap<>();
    private Map<String, ConsumerGroup<?>> consumerGroups = new HashMap<>();
    private Map<String, Consumer<?>> consumers = new HashMap<>();

    private Map<String, Producer<?>> producers = new HashMap<>();

    private Map<String, Event<?>> events = new HashMap<>();
    private Map<String, Partition<?>> partitions = new HashMap<>();

    public <T> void createTopic(String id, Class<T> type) {
        if (topics.containsKey(id)) {
            throw new IllegalArgumentException("Topic with ID " + id + " already exists.");
        }
        topics.put(id, new Topic<>(id, type));
    }

    public void createPartition(String topicId, String partitionId) {
        Topic<?> topic = topics.get(topicId);
        if (topic == null) {
            return;
        }

        Partition partition = new Partition(partitionId, topic);

        topic.addPartition(partition);
    }

    public <T> void createConsumerGroup(String id, String topicId, String rebalancingMethod) {
        Topic<?> topic = topics.get(topicId);
        ConsumerGroup<T> consumerGroup = (ConsumerGroup<T>) new ConsumerGroup<>(id, topic, rebalancingMethod);
        consumerGroups.put(id, consumerGroup);
    }

    public <T> void createConsumer(String groupId, String consumerId) {
        Consumer<T> consumer = new Consumer<>(consumerId);
        ConsumerGroup<T> group = (ConsumerGroup<T>) consumerGroups.get(groupId);
        if (group != null) {
            group.addConsumer(consumer);
        }
    }

    public <T> void deleteConsumer(String consumerId) {
        for (ConsumerGroup<?> group : consumerGroups.values()) {
            if (group.hasConsumer(consumerId)) {
                group.removeConsumer(consumerId);
                return;
            }
        }
    }

    public <T> void createProducer(String id, String allocationMethod, Class<T> type) {
        AllocationStrategy<T> allocationStrategy;

        switch (allocationMethod) {
            case "Random":
                allocationStrategy = new RandomAllocation<>();
                break;
            case "Manual":
                allocationStrategy = new ManualAllocation<>();
                break;
            default:
                throw new IllegalArgumentException("Unknown allocation method: " + allocationMethod);
        }

        Producer<T> producer = new Producer<>(id, allocationStrategy, type);
        producers.put(id, producer);
    }

    public Producer<?> getProducerById(String id) {
        return producers.get(id);
    }

    public <T> void produceEvent(String producerId, String topicId, T value, String partitionId) {
        Producer<T> producer = (Producer<T>) getProducerById(producerId);
        Class<T> valueType = producer.getValueType();
        Topic<T> topic = (Topic<T>) getTopicsById(topicId);
        String eventId = UUID.randomUUID().toString();
        Event<T> event = new Event<>(eventId, producer.getValueType().getName(), partitionId, value);
        producer.produce(topic, event);
    }


    public void consume(String consumerId, String partitionId) {
        Consumer<?> consumer = getConsumerById(consumerId);
        Partition<?> partition = getPartitionById(partitionId);
        consumer.consume(partition.consumeEvent());
    }

    public void consumeEvents(String consumerId, String partitionId, int num) {
        for(int i = 0; i < num; i++){
            consume(consumerId, partitionId);
        }
    }
    public Topic<?> getTopicsById(String id) {
        return topics.get(id);
    }

    public Partition<?> getPartitionById(String id){
        return partitions.get(id);
    }

    public Consumer<?> getConsumerById(String id){
        return consumers.get(id);
    }
    public ConsumerGroup<?> getConsumerGroupById(String id){
        return consumerGroups.get(id);
    }

    public void playback(String consumerId, String partitionId, int num){
        Consumer<?> consumer = getConsumerById(consumerId);
        Partition<?> partition = getPartitionById(partitionId);
        partition.playback(consumer, num);
    }

    public void setRebalancingStrategy(String consumerGroupId, String rebalancing){
        ConsumerGroup<?> consumerGroup = getConsumerGroupById(consumerGroupId);
        consumerGroup.changeRebalancingStrategy(rebalancing);
    }
}
