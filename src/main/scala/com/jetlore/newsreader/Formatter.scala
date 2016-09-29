package com.jetlore.newsreader

import annotation.tailrec


trait Item {
  val x:Int
  val y:Int
  def format(term:String): String

}


class Entity(val x:Int, val y:Int) extends Item {
  def format(term:String): String = s"<strong>$term</strong>"
}


class Link(val x:Int, val y:Int) extends Item {
  def format(term:String): String = s"""<a href="$term">$term</a>"""
}


class TwitterUser(val x:Int, val y:Int) extends Item {
  def format(term:String): String = {
    val name = term.substring(1)
    s"""@<a href="http://twitter.com/$name">$name</a>"""
  }
}


class TwitterHashTag(val x:Int, val y:Int) extends Item {
  def format(term:String): String = {
    val tag = term.substring(1)
    s"""#<a href="http://twitter.com/hashtag/$tag">$tag</a>"""
  }
}


object Formatter {

  @tailrec
  def formatIter(input:String, result:String, items:List[Item], pos:Int):String = {
    items match {
      case Nil => result + input.substring(pos)
      case item :: rest => {
        val prefix = input.slice(pos, item.x)
        val text = input.slice(item.x, item.y)
        val node = item.format(text)
        formatIter(input, result + prefix + node, rest, item.y)
      }
    }
  }

  def format(input:String, items:List[Item]):String = {
    val items_sorted = items.sortWith(_.x < _.x)
    formatIter(input, "", items_sorted, 0)
  }

}
