package scalapoet

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PoetParserSuite extends FunSuite {

  val poetParser = new PoetParser()

  test("can turn input string into a list of words") {
    val input = "this is a test"
    val output = List("this", "is", "a", "test")

    assert(poetParser.createTextList(input) === output)
  }

  test("it ignores punctuation") {
    val input = "this. is, a!, test?"
    val output = List("this", "is", "a", "test")

    assert(poetParser.createTextList(input) === output)
  }
  
  test("it splits across multiple lines") {
    val input = "this is\na test"
    val output = List("this", "is", "a", "test")

    assert(poetParser.createTextList(input) === output)
  }
}