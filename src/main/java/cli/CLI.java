package cli;

import tributary.Tributary;

import java.util.Scanner;

public class CLI {
    private Tributary tributary;

    public CLI() {
        tributary = new Tributary();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String command = parts[0];
            try {
                switch (command) {
                    case "create":
                        createCommand(parts);
                        break;
                    case "delete":
                        deleteConsumer(parts);
                        break;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage());
            }
        }
    }

    private void createCommand(String[] parts) {
        String subCommand = parts[1];
        switch (subCommand) {
            case "topic":
                createTopic(parts);
                break;
            case "partition":
                createPartition(parts);
                break;
            case "consumer":
                createConsumer(parts);
                break;
        }
    }

    private void createTopic(String[] parts) {
        String id = parts[2];
        Class<?> type = parts[3].equalsIgnoreCase("Integer") ? Integer.class : String.class;
        tributary.createTopic(id, type);
        System.out.println("Topic created with ID: " + id);
    }

    private void createPartition(String[] parts) {
        String topicId = parts[2];
        String partitionId = parts[3];
        tributary.createPartition(topicId, partitionId);
        System.out.println("Partition created with ID: " + partitionId);
    }

    private void createConsumer(String[] parts) {
        String subConsumer = parts[2];
        if (subConsumer.equals("group")) {
            String consumerGroupId = parts[3];
            String topicId = parts[4];
            String rebalancing = parts[5];
            tributary.createConsumerGroup(consumerGroupId, topicId, rebalancing);
            System.out.println("Consumer group created with ID: " + consumerGroupId);
        } else {
            String groupId = parts[2];
            String consumerId = parts[3];
            tributary.createConsumer(groupId, consumerId);
            System.out.println("Consumer created with ID: " + consumerId);
        }
    }

    private void createProducer(String[] parts){
        String producerId = parts[2];
        String type = parts[3];
        String allocation = parts[4];
        tributary.createProducer();
    }
    private void deleteConsumer(String[] parts) {
        String consumerId = parts[2];
        tributary.deleteConsumer(consumerId);
        System.out.println("Consumer was deleted with ID: " + consumerId);
    }

    public static void main(String[] args) {
        new CLI().run();
    }
}
