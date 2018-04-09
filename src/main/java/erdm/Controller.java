package erdm;

import java.util.List;

public class Controller {
    public static void main(String[] args) throws Exception {

        final SQSHandler sqsHandler = new SQSHandler();
        final DynamoQuery dynamoQuery = new DynamoQuery();

        sqsHandler.spinUp();
        List<String> messages = sqsHandler.sendAndReceiveMessage("price = 100");
        dynamoQuery.sendQuery(messages);
        sqsHandler.deleteMessageAndQueue();
    }
}
