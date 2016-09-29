package com.jetlore.newsreader

import org.scalatest.FunSuite


class TestFormatter extends FunSuite {

  test("test entity") {
    val result = Formatter.format("test", List(new Entity(0, 4)))
    assert(result == "<strong>test</strong>")
  }

  test("test link") {
    val result = Formatter.format("test", List(new Link(0, 4)))
    assert(result == """<a href="test">test</a>""")
  }

  test("test twitter user") {
    val result = Formatter.format("@test", List(new TwitterUser(0, 5)))
    assert(result == """@<a href="http://twitter.com/test">test</a>""")
  }

  test("test twitter hashtag") {
    val result = Formatter.format("#test", List(new TwitterHashTag(0, 5)))
    assert(result == """#<a href="http://twitter.com/hashtag/test">test</a>""")
  }

  test("test complex") {
    val input = "Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile see also #obama #facebook"
    val result = Formatter.format(input, List(
      new Link(37, 54),
      new Entity(0, 5),
      new Entity(14, 22),
      new TwitterHashTag(77, 83),
      new TwitterHashTag(84, 93),
      new TwitterUser(55, 67)
    ))
    assert(result == """<strong>Obama</strong> visited <strong>Facebook</strong> headquarters: <a href="http://bit.ly/xyz">http://bit.ly/xyz</a> @<a href="http://twitter.com/elversatile">elversatile</a> see also #<a href="http://twitter.com/hashtag/obama">obama</a> #<a href="http://twitter.com/hashtag/facebook">facebook</a>""")
  }

  test("test items order") {
    val input = "@foo #bar BAZ"
    val items = List(
      new TwitterUser(0, 4),
      new TwitterHashTag(5, 9),
      new Entity(10, 13)
    )
    val result1 = Formatter.format(input, items)
    val result2 = Formatter.format(input, util.Random.shuffle(items))
    assert(result1 == result2)
  }

}
