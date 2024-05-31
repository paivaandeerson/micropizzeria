Start RabbitMQ

1. Init `docker pull rabbitmq:management`
2. `docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
  `
3. Browser > http://localhost:15672/ guest / guest > Add queue: `order-queue` > Add exchange `order-exchange`> edit exchange bind using `order-routing-key`

Start Kafka

1. `docker pull confluentinc/cp-kafka`
2. `docker run -d --name zookeeper -p 2181:2181 confluentinc/cp-zookeeper:latest`
3. Optional `docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper confluentinc/cp-kafka:latest`