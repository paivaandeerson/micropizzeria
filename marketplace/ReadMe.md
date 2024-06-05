## To debug

- `docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management`
- Swagger:/swagger-ui/index.html


### To run on docker
- `docker build -t marketplace-api .`
- `docker run -d --name marketplace-api -p 8080:8080 marketplace-api`