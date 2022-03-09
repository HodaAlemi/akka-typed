package actors

import actors.ProcessOrderActor.ProcessOrder
import akka.actor.TypedActor.context
import akka.actor.typed.ActorRef
import akka.actor.typed.scaladsl.adapter.ClassicActorContextOps
import akka.actor.{ActorLogging, Props, Actor => untypedActor}
import akka.actor.typed.scaladsl.adapter._



object RegisterOrderActor{
  case class Order(id: Int, items: Seq[Item], totalPrice: Double, date: String)

  case class Orders(orders: Seq[Order])

  case class Item(id: Int, name: String, amount: Int, price: Double)

  case class SubmitOrder(order: Order)
  case class GetOrders()
  def props: Props = Props[RegisterOrderActor]
}

class RegisterOrderActor extends untypedActor with ActorLogging {

  import RegisterOrderActor._

  var orders = Set.empty[Order]
  val processOrderActor: ActorRef[ProcessOrderActor.ProcessOrder] = context.spawn(ProcessOrderActor.behavior, "processOrderActor")
  context.watch(processOrderActor)

  def receive: Receive = {
    case GetOrders() =>
      sender() ! Orders(orders.toSeq)

    case SubmitOrder(order) =>
      log.info("registering the order")
      orders += order
      processOrderActor ! ProcessOrder(order, sender())
  }
}
