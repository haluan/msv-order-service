package id.com.masiaveli.order.controllers.responses

import java.time.format.DateTimeFormatter
import java.time.{ZoneId, ZonedDateTime}

import play.api.libs.json.{JsString, JsValue, Json, Writes}

object ResponsesFormatter {
  implicit val zonedDateTime = new Writes[ZonedDateTime] {
    override def writes(d: ZonedDateTime): JsValue = JsString(toZonedDateTimeToUTCISOFormat(d))
  }
  implicit val createOrderResponse = Json.writes [CreateOrderResponse]

  private val ISO_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
  private def toZonedDateTimeToUTCISOFormat(zonedDateTime: ZonedDateTime): String = {
    val zoneID = ZoneId.of("UTC")
    val timeToUTC = zonedDateTime.withZoneSameInstant(zoneID)
    val dateTimeFormatter = DateTimeFormatter.ofPattern(ISO_DATE_TIME_PATTERN)
    timeToUTC.format(dateTimeFormatter)
  }
}
