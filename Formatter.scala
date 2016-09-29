
trait Item {
  val x:Int
  val y:Int
  def format(term:String): String

}


class Entity(val x:Int, val y:Int) extends Item {
  def format(term:String): String = s"<strong>$term</strong>"
}


class Link(val x:Int, val y:Int) extends Item {
  def format(term:String): String = s"""<a href="$term"</a>"""
}


class TwitterUser(val x:Int, val y:Int) extends Item {
  def format(term:String): String = {
    val name = term.substring(1)
    s"""@<a href="http://twitter.com/$name"$name</a>"""
  }
}


class TwitterHashTag(val x:Int, val y:Int) extends Item {
  def format(term:String): String = {
    val tag = term.substring(1)
    s"""#<a href="https://twitter.com/hashtag/$tag"$tag</a>"""
  }
}


object Formatter {

  // todo traversable
  // todo: sort
  // todo tail recur

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

  def format(input:String, items:List[Item]):String = formatIter(input, "", items, 0)

  def main(args:Array[String]) = {
    val input = "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile see also #obama #facebook"
    val result:String = format(input, List(
      new Entity(0, 5),
      new Entity(14, 22),
      new Link(37, 54),
      new TwitterUser(55, 67),
      new TwitterHashTag(77, 83),
      new TwitterHashTag(84, 93)
    ))
    println(result)
  }

}
