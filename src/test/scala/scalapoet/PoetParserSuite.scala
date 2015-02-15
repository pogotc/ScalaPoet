package scalapoet

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PoetParserSuite extends FunSuite {

  test("can turn input string into a list of words") {
    val input = "this is a test"
    val poetParser = new PoetParser(input)
    val output = List("this", "is", "a", "test")

    assert(poetParser.getTextList() === output)
  }

  test("it ignores punctuation") {
    val input = "this. is, a!, test?"
    val poetParser = new PoetParser(input)
    val output = List("this", "is", "a", "test")

    assert(poetParser.getTextList() === output)
  }

  test("it splits across multiple lines") {
    val input = "this is\na test"
    val poetParser = new PoetParser(input)
    val output = List("this", "is", "a", "test")

    assert(poetParser.getTextList() === output)
  }

  test("it can create a suffix map from an input string") {
    val input = "Some bland information about the band demands an explanation"
    val poetParser = new PoetParser(input)
    val output = Map("and" -> List("bland", "band"), "ation" -> List("information", "explanation"))

    assert(poetParser.createSuffixMap() === output)
  }

  test("it can retrieve the context for a word") {
    val input = "Some bland information about the band demands an explanation"
    val poetParser = new PoetParser(input)

    val output = "The band demands an explanation"

    assert(poetParser.getContextForWord("explanation") === output)
  }

  test("it just returns the word if there are no words before the word we're looking for") {
    val input = "band is unrelated"
    val output = "Band"

    val poetParser = new PoetParser(input)
    
    assert(poetParser.getContextForWord("band") === output)
  }

  test("it can retrieve the context for a word that appears multiple times") {
    val input = "This is some info about the band and this is unrelated to the band"
    val poetParser = new PoetParser(input)

    val possibleOutput1 = "Some info about the band"
    val possibleOutput2 = "Is unrelated to the band"

    var foundOne = false
    var foundTwo = false

    for (x <- 1 to 10) {
      val output = poetParser.getContextForWord("band")
      if (output == possibleOutput1) {
        foundOne = true
      } else if (output == possibleOutput2) {
        foundTwo = true
      }
    }
    assert(foundOne === true, "Did not find output 1")
    assert(foundTwo === true, "Did not find output 2")
  }
}