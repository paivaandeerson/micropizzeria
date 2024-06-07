Start RabbitMQ

1. Init `docker pull rabbitmq:management`
2. `docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
  `
3. Browser > http://localhost:15672/ guest / guest > Add queue: `order-queue` > Add exchange `order-exchange`> edit exchange bind using `order-routing-key` OR <pre>`
docker exec -it rabbitmq bash -c "
    apt-get update && \
    apt-get install -y curl && \
    curl -LO http://localhost:15672/cli/rabbitmqadmin && \
    chmod +x rabbitmqadmin && \
    ./rabbitmqadmin declare queue name=order-queue durable=true && \
    ./rabbitmqadmin declare exchange name=order-exchange type=direct durable=true && \
    ./rabbitmqadmin declare binding source='order-exchange' destination_type='queue' destination='order-queue' routing_key='order-routing-key' </pre>
"`

Start Kafka

1. `docker pull confluentinc/cp-kafka`
2. `docker run -d --name zookeeper -p 2181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 confluentinc/cp-zookeeper:latest
   `
3. `docker run -d --name kafka -p 9092:9092 --link zookeeper 
   -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 
   -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 
   confluentinc/cp-kafka:latest`