using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace StatusOrder.Api.Models;

public class OrderStatus
{
    [BsonId]
    public ObjectId Id { get; set; }

    public Guid OrderId { get; set; }

    public string Status { get; set; } = string.Empty;

    public DateTime UpdatedAt { get; set; }
}