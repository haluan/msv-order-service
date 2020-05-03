package id.com.masiaveli.order.controllers.responses

import java.time.ZonedDateTime

case class CreateOrderResponse
(
  orderId: String,
  createdTime: ZonedDateTime,
  requestId: String
)
