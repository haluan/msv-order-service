package id.com.masiaveli.order.controllers

import java.time.ZonedDateTime
import java.util.UUID

import id.com.masiaveli.order.controllers.requests.CreateOrderRequest
import id.com.masiaveli.order.controllers.responses.CreateOrderResponse
import id.com.masiaveli.order.model.Order
import id.com.masiaveli.order.controllers.requests.RequestsFormatter._
import id.com.masiaveli.order.controllers.responses.ResponsesFormatter._
import id.com.masiaveli.order.model.ModelsFormatter._
import id.com.masiaveli.order.repositories.OrderRepo

import javax.inject.Inject
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{Action, Controller}

import scala.concurrent.{ExecutionContext, Future}

class OrderController @Inject()(orderRepo: OrderRepo)(implicit ec: ExecutionContext) extends Controller {
  def create = Action.async(parse.json) {
    request =>
      request.body.validate[CreateOrderRequest] map { createOrderRequest =>
        val order = buildOrder(createOrderRequest)
        orderRepo.create(order) map { dbResult =>
          Created (createOrderProcessing(dbResult.message))
        }
      } recoverTotal { ex =>
        print(ex.errors)
        Future.successful(BadRequest)
      }
  }

  def list = Action.async {
    orderRepo.list() map { dbResult =>
      Ok(Json.toJson(dbResult))
    }
  }

  def get(id: String) = Action.async {
    orderRepo.get(id) map {
      case Some(dbResult) =>
        Ok(Json.toJson(dbResult))
      case None =>
        NotFound
    }
  }

  private def buildOrder(createOrderRequest: CreateOrderRequest): Order = {
    Order(id = UUID.randomUUID().toString, title = createOrderRequest.title, minAmount = createOrderRequest.minAmount,
      maxAmount = createOrderRequest.maxAmount, `type` = createOrderRequest.`type`,
      description = createOrderRequest.description, createdTime = ZonedDateTime.now(),
      updatedTime = None, closedTime = None, paidTime = None, finishedTime = None, closedReason = None)
  }

  private def createOrderProcessing(requestId: String): JsValue = {
    Json.toJson {
      CreateOrderResponse(UUID.randomUUID().toString, ZonedDateTime.now(), requestId)
    }
  }
}
