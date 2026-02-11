using Microsoft.AspNetCore.Mvc;
using StatusOrder.Api.Services;

namespace StatusOrder.Api.Controllers;

[ApiController]
[Route("api")]
public class StatusController : ControllerBase
{
    private readonly OrderStatusService _service;

    public StatusController(OrderStatusService service)
    {
        _service = service;
    }

    [HttpGet("health")]
    public IActionResult Health()
    {
        return Ok(new { status = "healthy" });
    }

    [HttpGet("checkstatus/{orderId:guid}")]
    public async Task<IActionResult> CheckStatus(Guid orderId)
    {
        //TODO: Mongo integration isn't working
        var result = await _service.GetByOrderIdAsync(orderId);

        if (result is null)
            return NotFound();

        return Ok(result);
    }
}