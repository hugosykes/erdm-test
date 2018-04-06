# Initial scoping of the project

[![Build Status](https://travis-ci.org/hugosykes/erdm-test.svg?branch=master)](https://travis-ci.org/hugosykes/erdm-test)

## SQS

Java class called SQSHandler which generates a queue, sends and receives messages from it and deletes it.

## DynamoDB

Another class which scans a Dynamo database of price points.

### Plan

* Have the SQS Handler set up a queue
* Get DynamoQuery to send a query through the queue
* This will involve:
* - sending json as the message
* - have SQS handler listen for messages
* - when a new message comes in, pass it to DynamoQuery
* - having DynamoDB scan using that filter expression
* - somehow sending the response back