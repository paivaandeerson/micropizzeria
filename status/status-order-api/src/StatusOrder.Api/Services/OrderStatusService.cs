using Microsoft.Extensions.Options;
using MongoDB.Driver;
using StatusOrder.Api.Infrastructure;
using StatusOrder.Api.Models;

namespace StatusOrder.Api.Services;

public class OrderStatusService
{
    private readonly IMongoCollection<OrderStatus> _collection;

    public OrderStatusService(IMongoClient client, IOptions<MongoSettings> settings)
    {
        var database = client.GetDatabase(settings.Value.Database);
        _collection = database.GetCollection<OrderStatus>(settings.Value.Collection);
    }

    public async Task<OrderStatus?> GetByOrderIdAsync(Guid orderId)
    {
        return await _collection
            .Find(x => x.OrderId == orderId)
            .FirstOrDefaultAsync();
    }
}