package com.joescii.chat.comet

import net.liftweb.actor.LiftActor
import net.liftweb.http.{CometListener, ListenerManager}
import net.liftmodules.ng.SimpleBindingActor
import net.liftmodules.ng.Angular.NgModel

object ChatServer extends LiftActor with ListenerManager {
  private var msgs = List.empty[String]

  def createUpdate = ChatMessages(msgs)

  override def lowPriority = {
    case msg:String => {
      msgs = msgs :+ msg
      updateListeners()
    }

  }
}

case class ChatMessages(msgs:List[String] = List())
  extends NgModel

class ChatBinder extends SimpleBindingActor  (
  "chat",
  ChatMessages()
) with CometListener {
  def registerWith = ChatServer
}