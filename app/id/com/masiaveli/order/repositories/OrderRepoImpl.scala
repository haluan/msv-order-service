package id.com.masiaveli.order.repositories

import java.time.ZonedDateTime
import java.util.Date
import javax.inject.Inject

import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.commands.WriteResult

import scala.concurrent.{ExecutionContext, Future}
import play.modules.reactivemongo.json._
import reactivemongo.api.ReadPreference
import reactivemongo.bson.{BSONDateTime, BSONDocument}
import id.com.masiaveli.order.model.Order
import id.com.masiaveli.order.model.ModelsFormatter._

import scala.concurrent.{ExecutionContext, Future}

class OrderRepoImpl @Inject()(reactiveMongoApi: ReactiveMongoApi)(implicit ec: ExecutionContext) extends OrderRepo {

  import play.modules.reactivemongo.json._

  protected def collection =
    reactiveMongoApi.db.collection[JSONCollection]("orders")
  override def create(document: Order): Future[WriteResult] =
    collection.insert(document)

  override def list(): Future[List[Order]] =
    collection.find(Json.obj()).cursor[Order](ReadPreference.Primary).collect[List]()

  override def get(id: String): Future[Option[Order]] = {
    val query = BSONDocument("id" -> id)
    collection.find(query).one[Order]
  }
}
