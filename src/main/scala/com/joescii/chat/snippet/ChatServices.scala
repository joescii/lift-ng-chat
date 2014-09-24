package com.joescii.chat.snippet

import net.liftmodules.ng.Angular._
import net.liftweb.common.Empty
import com.joescii.chat.comet.ChatServer

object ChatServices {
  def render = renderIfNotAlreadyDefined(
    angular.module("ChatServices")
    .factory("chatService", jsObjFactory().
      jsonCall("sendChat", (msg:String) => {
        ChatServer ! msg
        Empty
    })
      )
  )
}
