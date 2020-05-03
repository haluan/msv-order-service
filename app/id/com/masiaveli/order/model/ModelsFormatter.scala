package id.com.masiaveli.order.model

import play.api.libs.json.Json

object ModelsFormatter {

  implicit val multiCurrencyModelMoneyReader = Json.reads [MultiCurrencyMoney]
  implicit val multiCurrencyModelMoneyWriter = Json.writes [MultiCurrencyMoney]

  implicit val orderModelReader = Json.reads [Order]
  implicit val orderModelWriter = Json.writes [Order]
}
