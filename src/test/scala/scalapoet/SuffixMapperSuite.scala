package scalapoet

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SuffixMapperSuite extends FunSuite {

  val suffixMapper = new SuffixMapper();

  test("Returns empty map for empty input") {
    assert(suffixMapper.map(List()) === Map())
  }

  test("Returns 3 char suffix for words with 3 chars in common") {
    val input = List("band", "bland")
    val output = Map("and" -> List("band", "bland"))
    assert(suffixMapper.map(input) === output)
  }

  test("Does not return words that do not have a matching suffix") {
    val input = List("band", "bland", "badger")
    val output = Map("and" -> List("band", "bland"))
    assert(suffixMapper.map(input) === output)
  }

  test("Maps endings for input lists with different sized suffixes") {
    val input = List("band", "bland", "badger", "information", "explanation")
    val output = Map("and" -> List("band", "bland"), "ation" -> List("information", "explanation"))
    assert(suffixMapper.map(input) === output)
  }
}