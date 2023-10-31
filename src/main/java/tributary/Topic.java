package tributary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Topic<T> {
    private String id;
    private Class<T> type;
    private List<Partition<T>> partitions;

    public Topic(String id, Class<T> type) {
        this.id = id;
        this.type = type;
        this.partitions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public Class<T> getType() {
        return type;
    }

    public void addPartition(Partition<T> partition) {
        partitions.add(partition);
    }

    public List<Partition<T>> getPartitions() {
        return partitions;
    }

    public Partition<T> getPartitionById(String partitionId) {
        for (Partition<T> partition : partitions) {
            if (Objects.equals(partition.getId(), partitionId)) {
                return partition;
            }
        }
        return null;
    }
}
