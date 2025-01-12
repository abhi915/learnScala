package playground

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._


object Playground {

  def main(args: Array[String]) : Unit = {
    val root = ActorSystem(
      Behaviors.receive[String]{ (context, message) =>
        context.log.info(s"just received message $message")
        Behaviors.same
      }, "dummySystme"
    )

    root ! "Hey Akka"

    implicit  val ec: ExecutionContext  = root.executionContext
    root.scheduler.scheduleOnce(10.second, () => root.terminate())
  }

}
