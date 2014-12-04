package com.joescii.chat
package comet

import model._
import net.liftweb.actor._
import net.liftweb.http._
import net.liftmodules.ng.{SimpleNgModelBinder, BindingToClient, AngularActor}

object ChatServer extends LiftActor with ListenerManager {
  var msgs = List.empty[String]

  def createUpdate = ChatMessages(msgs)

  override def lowPriority = {
    case msg:String =>
      msgs = (msgs :+ msg) takeRight 10
      updateListeners()

    case user @ User(ip) =>
      println("New User "+ip)
      sendListenersMessage(user)
  }
}

class ChatBinder extends SimpleNgModelBinder(
  "chat",
  ChatMessages()
) with BindingToClient with CometListener {
  def registerWith = ChatServer
}

class NewUserNotifier extends AngularActor with CometListener {
  def registerWith = ChatServer

  override def lowPriority = {
    case user @ User(ip) =>
      println("emitting "+user)
      scope.emit("newUser", ip)
  }
}