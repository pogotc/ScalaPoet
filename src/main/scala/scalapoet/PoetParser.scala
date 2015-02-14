package scalapoet

class PoetParser {

  def createTextList(input: String): List[String] = {
    val charsToIgnore = "!,.?".toSet
    input.filterNot(charsToIgnore).split(" ").toList
  }

}