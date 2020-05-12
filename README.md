# kaf-producer
Sample project to test kafka message producer with spring boot.

* String message sending part is commented, since multiple message 
deserialization is not enabled.

* Disable all PersonMessage producing part, in order to enable and test String message sending.

* Start Kafka and zookeeper using the following commands before start the application. Go to Kafka 
installation directory and run:

        bin/zookeeper-server-start.sh config/zookeeper.properties
        bin/kafka-server-start.sh config/server.properties