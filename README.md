# micropizzeria microservices

### 1. Run RabbitMQ
1. Init `docker pull rabbitmq:management`
2. `docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management `
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

### 2. Run payment-api
`npm start`

to run on docker: 
- `docker build -t payment-api .`
- `docker run -p 3000:3000 payment-api`

### 3. Run kitchen-worker
TODO run on docker

### 4. Run marketplace-api
TODO run on docker

![Technologies](util/technologies.png)
