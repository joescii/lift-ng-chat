package com.joescii.chat.snippet

import net.liftweb.util.Helpers._
import java.net.InetAddress._

object ChatSnips {
  def title = ".title *" #>
    ("http://"+getLocalHost.getHostAddress+":8080")
}
