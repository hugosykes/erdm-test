package rdm;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


public class DynamoQuery {

    static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();

    public static void main(String[] args) throws Exception {
        try {
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            FindPriceDataWithPriceOf(mapper, 100);
        } catch (Throwable t) {
            System.err.println("Error running the DynamoDB Query: " + t);
            t.printStackTrace();
        }
    }

    private static void FindPriceDataWithPriceOf(DynamoDBMapper mapper, Integer priceOf) throws Exception {
        System.out.println("Find Price data with a price of: " + priceOf);
        
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withN(priceOf.toString()));
        
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
        .withFilterExpression("price = :val1").withExpressionAttributeValues(eav);
        
        List<PriceData> priceData = mapper.scan(PriceData.class, scanExpression);

        for (PriceData price : priceData) {
            System.out.println(price.toString());
        }
    }

    @DynamoDBTable(tableName = "hugo_test")
    public static class PriceData {
        private Integer id;
        private Integer price;
        private Integer priceYesterday;
        private String date;

        @DynamoDBHashKey(attributeName = "id")
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @DynamoDBAttribute(attributeName = "price")
        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @DynamoDBAttribute(attributeName = "price-yesterday")
        public Integer getPriceYesterday() {
            return priceYesterday;
        }

        public void setPriceYesterday(Integer priceYesterday) {
            this.priceYesterday = priceYesterday;
        }

        @DynamoDBAttribute(attributeName = "date")
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Price Data [price today=" + price + ", price yesterday=" + priceYesterday + ", date=" + date + ", id=" + id +"]";
        }
    }
}