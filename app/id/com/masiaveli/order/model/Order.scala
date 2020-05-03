package id.com.masiaveli.order.model

import java.time.ZonedDateTime

case class Order
(
  id: String,
  title: String,
  minAmount: MultiCurrencyMoney,
  maxAmount: MultiCurrencyMoney,
  `type`: String,
  description: String,
  createdTime: ZonedDateTime,
  updatedTime: Option[ZonedDateTime],
  closedTime: Option[ZonedDateTime],
  paidTime: Option[ZonedDateTime],
  finishedTime: Option[ZonedDateTime],
  closedReason: Option[ZonedDateTime]
)
