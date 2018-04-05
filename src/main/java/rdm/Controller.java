package rdm;

public class Controller {
    public static void main(String[] args) throws Exception {

        final SQSHandler sqsHandler = new SQSHandler();
        final DynamoQuery dynamoQuery = new DynamoQuery();

        sqsHandler.spinUp();
//        Thread.sleep(100000);
//        sqsHandler.sendAndReceiveMessage("hello");
//        sqsHandler.deleteMessageAndQueue();
//        dynamoQuery.sendQuery();
    }
}
