# Initial scoping of the project

[![Build Status](https://travis-ci.org/hugosykes/erdm-test.svg?branch=master)](https://travis-ci.org/hugosykes/erdm-test)

## SQS

Java class called SQSHandler which can generate a queue; send, receive and delete messages from it; and delete it.

## DynamoDB

Another class which scans a Dynamo database of price points of a particular price

## Controller

Controls the other two classes and sends messages, etc. to the queue

### Process

1. Controller creates instances of both classes above
2. Spins up the SQS queue
3. Sends a message of 'price = 100' to the queue
4. Receives the message back from the queue
5. Translates the text message into a scan query for DynamoDB
6. Queries the DynamoDB table with above query and displays results
7. Deletes both the messages and queue