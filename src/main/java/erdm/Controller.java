package erdm;

import java.util.List;

public class Controller {
    public static void main(String[] args) throws Exception {

        final SQSHandler sqsHandler = new SQSHandler();
        final DynamoQuery dynamoQuery = new DynamoQuery();

        sqsHandler.spinUp();
        List<String> messages = sqsHandler.sendAndReceiveMessage("price = 100");
        for(String message : messages) {
            System.out.println(message);
        }
        messages = sqsHandler.sendAndReceiveMessage("price = 101");
        for(String message : messages) {
            System.out.println(message);
        }
        messages = sqsHandler.sendAndReceiveMessage("price = 102");
        for(String message : messages) {
            System.out.println(message);
        }
        dynamoQuery.sendQuery(messages);
        messages = sqsHandler.sendAndReceiveMessage("price_yesterday = 100");
        dynamoQuery.sendQuery(messages);

//        Thread.sleep(100000);
//        sqsHandler.sendAndReceiveMessage("hello");
        // sqsHandler.deleteMessageAndQueue();
//        dynamoQuery.sendQuery();
    }
}
