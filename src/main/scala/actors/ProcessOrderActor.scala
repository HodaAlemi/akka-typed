package actors

import actors.ProcessOrderActor.ActionExecuted
import actors.RegisterOrderActor.Order
import akka.actor.{ActorLogging, Props, Actor => untypedActor}

object ProcessOrderActor {
  case class ActionExecuted(description: String)

  def props: Props = Props[ProcessOrderActor]

}

class ProcessOrderActor extends untypedActor with ActorLogging {

   def receive: Receive = {
     case order:Order =>
       log.info("process order actor received the order")
       sender() ! ActionExecuted(s"OrderID ${order.id} processed.")
   }


}
