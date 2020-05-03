package id.com.masiaveli.order.repositories

import id.com.masiaveli.order.model.Order
import reactivemongo.api.commands.WriteResult

import scala.concurrent.Future

trait OrderRepo {
  def create(document: Order): Future[WriteResult]
  def list(): Future[List[Order]]
  def get(id: String): Future[Option[Order]]
}
