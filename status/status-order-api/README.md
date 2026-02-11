# Status Order API

Microserviço responsável por consultar o status de pedidos.

## Stack
- .NET 8 (LTS)
- ASP.NET Core Web API (MVC)
- MongoDB
- Swagger

## Endpoints
- GET /api/health
- GET /api/checkstatus/{orderId}

## Arquitetura
- Controllers: Camada de entrada HTTP
- Services: Regras de aplicação
- Models: Entidades
- Infrastructure: Configurações externas (MongoDB)

## Configuração
As configurações são carregadas via `appsettings.json` ou variáveis de ambiente.

### Variáveis de ambiente
- MongoDB__ConnectionString
- MongoDB__Database
- MongoDB__Collection

## Executar
```bash
dotnet restore
dotnet run
```

Swagger disponível em `/swagger`.