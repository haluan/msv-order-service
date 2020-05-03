package id.com.masiaveli.order.controllers.requests

import id.com.masiaveli.order.model.MultiCurrencyMoney
import play.api.libs.json.Json


object RequestsFormatter {
  implicit val multiCurrencyMoneyReader = Json.reads [MultiCurrencyMoney]
  implicit val createOrderRequestReader = Json.reads [CreateOrderRequest]
}
