package actors

import actors.ProcessOrderActor.ActionExecuted
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import api.WebServer.system


object RegisterOrderActor{
  case class Order(id: Int, items: Seq[Item], totalPrice: Double, date: String)

  case class Orders(orders: Seq[Order])

  case class Item(id: Int, name: String, amount: Int, price: Double)

  case class SubmitOrder(order: Order)
  case class GetOrders()

  def props: Props = Props[RegisterOrderActor]
  val processOrderActor: ActorRef = system.actorOf(ProcessOrderActor.props, "processOrderActor")

}

class RegisterOrderActor extends Actor with ActorLogging {

  import RegisterOrderActor._

  var flowActorRef: ActorRef = _
  var orders = Set.empty[Order]

  def receive: Receive = {
    case GetOrders() =>
      sender() ! Orders(orders.toSeq)

    case SubmitOrder(order) =>
      flowActorRef = sender()
      orders += order
      //context.become(processOrder(sender(), orders))
      //log.info("context became processOrder")
      processOrderActor ! order

    case action: ActionExecuted =>
      flowActorRef ! action
  }

  def processOrder(flowActorRef: ActorRef, orders: Set[Order]): Receive = {
    case action: ActionExecuted =>
      flowActorRef ! action
    case someThingElse => log.warning("did not receive action executed, received " + someThingElse)
  }
}
