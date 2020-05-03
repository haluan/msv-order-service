package id.com.masiaveli.order.modules

import com.google.inject.AbstractModule
import id.com.masiaveli.order.repositories.{OrderRepo, OrderRepoImpl}

class OrderModules extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[OrderRepo]).to(classOf[OrderRepoImpl ])
  }
}
