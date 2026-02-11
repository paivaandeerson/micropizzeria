using Microsoft.Extensions.Options;
using MongoDB.Driver;
using StatusOrder.Api.Infrastructure;
using StatusOrder.Api.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.Configure<MongoSettings>(
    builder.Configuration.GetSection("MongoDB"));

builder.Services.AddSingleton<IMongoClient>(sp =>
{
    var settings = sp.GetRequiredService<IOptions<MongoSettings>>().Value;
    return new MongoClient(settings.ConnectionString);
});

builder.Services.AddScoped<OrderStatusService>();

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

app.UseSwagger();
app.UseSwaggerUI();

app.MapControllers();

app.Run();