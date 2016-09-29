package com.jetlore.newsreader

import org.scalatest.FunSpec
import scala.collection.mutable.Stack


class TestFormatter extends FunSpec {

  describe("A Stack") {

    it("should pop values in last-in-first-out order") {
      val stack = new Stack[Int]
      stack.push(1)
      stack.push(2)
      assert(stack.pop() === 2)
      assert(stack.pop() === 1)
    }

    it("should throw NoSuchElementException if an empty stack is popped") {
      val emptyStack = new Stack[Int]
      intercept[NoSuchElementException] {
        emptyStack.pop()
      }
    }
  }
}

// import collection.mutable.Stack
// import org.scalatest._

// class StackSpec extends FlatSpec {

//   "A Stack" should "pop values in last-in-first-out order" in {
//     val stack = new Stack[Int]
//     stack.push(1)
//     stack.push(2)
//     assert(stack.pop() === 2)
//     assert(stack.pop() === 1)
//   }

//   it should "throw NoSuchElementException if an empty stack is popped" in {
//     val emptyStack = new Stack[String]
//     assertThrows[NoSuchElementException] {
//       emptyStack.pop()
//     }
//   }
// }

// import org.scalatest.FunSuite
// import com.jetlore.newsreader.Formatter


// class TestFormatter extends FunSuite {

//   test("test") {
//     assert True
//   }

//   test("test2") {
//     assert True
//   }

// }
