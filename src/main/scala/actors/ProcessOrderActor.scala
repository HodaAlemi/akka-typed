package actors

import actors.RegisterOrderActor.Order
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import akka.actor.ActorRef

object ProcessOrderActor {
  case class ActionExecuted(description: String)
  case class ProcessOrder(order: Order, flowActorRef:ActorRef)

  val behavior: Behavior[ProcessOrder] =Behaviors.receive { (context, message) =>
      message match {
        case processOrder: ProcessOrder =>
          context.log.info(s"process order actor received the order ${processOrder.order.id}")
          processOrder.flowActorRef ! ActionExecuted(s"OrderID ${processOrder.order.id} processed.")
      }
    Behaviors.same
  }

}



