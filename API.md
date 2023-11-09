# Tributary 系统 API 文档

## 接口方法

### createTopic

- **方法签名**: `createTopic(String id, String type)`
- **描述**: 在系统中创建一个具有唯一标识符和类型的新主题。
- **前置条件**:
    - `id` 参数不能为空。
    - `type` 参数不能为空。
    - 系统中不能存在与 `id` 参数相同标识符的主题。
- **后置条件**: 系统中添加了一个带有指定标识符和类型的新主题。
- **参数**:
    - `id`: 新主题的唯一标识符。
    - `type`: 新主题的类型。
- **异常**: 如果系统中已存在给定标识符的主题，抛出 `IllegalArgumentException`。
- **返回值**: 无。

### createPartition

- **方法签名**: `createPartition(String topicId, String partitionId)`
- **描述**: 为给定主题创建新分区，每个分区都与特定主题关联，并具有唯一标识符。
- **前置条件**:
    - `topicId` 参数不能为空。
    - `partitionId` 参数不能为空。
    - 必须存在与 `topicId` 参数相等标识符的主题。
- **后置条件**: 系统中添加了一个带有指定标识符的新分区，并与指定的主题关联。
- **参数**:
    - `topicId`: 要创建分区的主题的标识符。
    - `partitionId`: 新分区的唯一标识符。
- **异常**: 如果系统中不存在给定标识符的主题，抛出 `IllegalArgumentException`。
- **返回值**: 无。

### createConsumerGroup

- **方法签名**: `void createConsumerGroup(String id, String topicId, String rebalancingMethod)`
- **描述**: 创建一个与指定主题和重平衡方法相关联的新消费者组。
- **前置条件**:
  - `id`、`topicId` 和 `rebalancingMethod` 参数都不能为空。
  - 必须存在一个与 `topicId` 参数相同标识符的主题。
- **后置条件**: 一个具有指定标识符、关联主题和重平衡方法的新消费者组被添加到系统中。
- **参数**:
  - `id`: 新消费者组的唯一标识符。
  - `topicId`: 消费者组要关联的主题的标识符。
  - `rebalancingMethod`: 消费者组的重平衡方法。
- **异常**: 如果没有给定标识符的主题存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。

### createConsumer

- **方法签名**: `void createConsumer(String groupId, String consumerId)`
- **描述**: 在指定的消费者组中创建并添加一个新的消费者。
- **前置条件**:
  - `groupId` 和 `consumerId` 参数都不能为空。
  - 必须存在一个与 `groupId` 参数相同标识符的消费者组。
- **后置条件**: 一个具有指定标识符的新消费者被添加到指定的消费者组和系统中。
- **参数**:
  - `groupId`: 消费者将要加入的消费者组的标识符。
  - `consumerId`: 新消费者的唯一标识符。
- **异常**: 如果没有给定标识符的消费者组存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。

### deleteConsumer

- **方法签名**: `void deleteConsumer(String consumerId)`
- **描述**: 从其关联的消费者组中删除指定的消费者。
- **前置条件**:
  - `consumerId` 参数不能为空。
  - 必须存在一个与 `consumerId` 参数相同标识符的消费者。
- **后置条件**: 指定的消费者已从其关联的消费者组和系统中删除。
- **参数**:
  - `consumerId`: 要删除的消费者的标识符。
- **异常**: 如果没有给定标识符的消费者存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。

### createProducer

- **方法签名**: `void createProducer(String id, String allocationMethod, String typeName)`
- **描述**: 创建一个带有指定分配方法和事件类型的新生产者。
- **前置条件**:
  - `id`、`allocationMethod` 和 `typeName` 参数都不能为null。
  - `allocationMethod` 参数必须是 "Random" 或 "Manual"。
- **后置条件**: 一个带有指定标识符、分配方法和事件类型的新生产者被添加到系统中。
- **参数**:
  - `id`: 新生产者的唯一标识符。
  - `allocationMethod`: 新生产者的分配方法。
  - `typeName`: 生产者将要创建的事件类型。
- **异常**: 如果 `allocationMethod` 参数既不是 "Random" 也不是 "Manual"，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。

### manualProduceEvent

- **方法签名**: `void manualProduceEvent(String producerId, String topicId, String value, String partitionId)`
- **描述**: 手动向指定的主题和分区生产一个事件。
- **前置条件**:
  - `producerId`、`topicId`、`value` 和 `partitionId` 参数都不能为null。
  - 必须存在与给定标识符匹配的生产者、分区和主题。
- **后置条件**: 一个具有唯一标识符的新事件被添加到系统中，并生产到指定的主题和分区。
- **参数**:
  - `producerId`: 生产者的标识符。
  - `topicId`: 主题的标识符。
  - `value`: 事件的值。
  - `partitionId`: 分区的标识符。
- **异常**: 如果给定标识符的分区、生产者或主题不存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。

### randomProduceEvent

- **方法签名**: `void randomProduceEvent(String producerId, String topicId, String value)`
- **描述**: 向指定主题的随机分区生产一个事件。
- **前置条件**:
  - `producerId`、`topicId` 和 `value` 参数都不能为null。
  - 必须存在与给定标识符匹配的生产者和主题。
- **后置条件**: 一个具有唯一标识符的新事件被添加到系统中，并生产到指定主题的随机分区。
- **参数**:
  - `producerId`: 生产者的标识符。
  - `topicId`: 主题的标识符。
  - `value`: 事件的值。
- **异常**: 如果给定标识符的生产者或主题不存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。

### consume

- **方法签名**: `Event consume(String consumerId, String partitionId)`
- **描述**: 该方法允许消费者从指定分区消费一个事件。
- **前置条件**:
  - `consumerId` 和 `partitionId` 参数不能为null。
  - 必须存在与给定标识符匹配的消费者和分区。
- **后置条件**: 指定分区的事件已被指定消费者消费。
- **参数**:
  - `consumerId`: 消费者的标识符。
  - `partitionId`: 分区的标识符。
- **异常**: 如果给定标识符的消费者或分区不存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 该方法返回被消费的事件。

### consumeEvents

- **方法签名**: `List<Event> consumeEvents(String consumerId, String partitionId, int num)`
- **描述**: 该方法允许消费者从指定分区消费特定数量的事件。
- **前置条件**:
  - `consumerId` 和 `partitionId` 参数不能为null。
  - `num` 参数必须大于0。
  - 必须存在与给定标识符匹配的消费者和分区。
- **后置条件**: 一个事件列表，其大小等于 `num`，已被指定消费者从指定分区消费。
- **参数**:
  - `consumerId`: 消费者的标识符。
  - `partitionId`: 分区的标识符。
  - `num`: 要消费的事件数量。
- **异常**: 如果给定标识符的消费者或分区不存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 该方法返回一个已消费事件的列表。

### playback

- **方法签名**: `List<Event> playback(String consumerId, String partitionId, int num)`
- **描述**: 该方法为指定消费者从指定分区回放特定数量的事件。
- **前置条件**:
  - `consumerId` 和 `partitionId` 参数不能为null。
  - `num` 参数必须大于0。
  - 必须存在与给定标识符匹配的消费者和分区。
- **后置条件**: 一个事件列表，其大小等于 `num`，已被指定消费者从指定分区回放。
- **参数**:
  - `consumerId`: 消费者的标识符。
  - `partitionId`: 分区的标识符。
  - `num`: 要回放的事件数量。
- **异常**: 如果给定标识符的消费者或分区不存在于系统中，则抛出 `IllegalArgumentException`。
- **返回值**: 该方法返回一个已回放事件的列表。

### setRebalancingStrategy

- **方法签名**: `void setRebalancingStrategy(String consumerGroupId, String rebalancing)`
- **描述**: 该方法为指定的消费者组更改重平衡策略。
- **前置条件**:
  - `consumerGroupId` 和 `rebalancing` 参数必须非null。
  - 系统中必须存在具有给定标识符的消费者组。
- **后置条件**: 指定消费者组的重平衡策略已更改为指定的策略。
- **参数**:
  - `consumerGroupId`: 消费者组的标识符。
  - `rebalancing`: 消费者组的新重平衡策略。
- **异常**: 如果系统中不存在具有给定标识符的消费者组，则抛出 `IllegalArgumentException`。
- **返回值**: 此方法不返回任何值。
