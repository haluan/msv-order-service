package id.com.masiaveli.order.controllers.requests

import id.com.masiaveli.order.model.MultiCurrencyMoney


case class CreateOrderRequest
(
  requestId: String,
  title: String,
  minAmount: MultiCurrencyMoney,
  maxAmount: MultiCurrencyMoney,
  `type`: String,
  description: String
)
