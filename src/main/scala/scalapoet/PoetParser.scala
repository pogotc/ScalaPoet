package scalapoet

import java.util.Random

class PoetParser(input: String) {
  val sanitisedInput = input.replaceAll("(RT )?@\\w+:?", "")
  val suffixMapper = new SuffixMapper
  val wordListWithPunctuation = sanitisedInput.replaceAll("\n", " ").split(" ").toList
  val wordListWithoutPunctuation = getTextList
  val rand = new Random(System.currentTimeMillis());

  def getTextList(): List[String] = {
    val charsToIgnore = "!,.?".toSet
    sanitisedInput.filterNot(charsToIgnore)
      .replaceAll("\n", " ")
      .split(" ")
      .toList
  }

  def createSuffixMap(): Map[String, List[String]] = suffixMapper.map(getTextList())

  def getContextForWord(word: String): String = {
    val wordOccurrences = allOccurrencesOf(wordListWithoutPunctuation, word, 0)
    val wordPos = wordOccurrences(rand.nextInt(wordOccurrences.length))
    val line = wordListWithPunctuation.slice(wordPos - 4, wordPos + 1).mkString(" ")

    return formatLine(line)
  }

  private def formatLine(line: String): String = {
    val santisedLine = line.replaceAll("http://[^ ]+", "").
    						replaceAll("#[^ ]+", "").
    						replaceAll("\\s{2,}", " ").
    						replaceAll("^ ", "")
    santisedLine(0).toUpper + santisedLine.substring(1)
  }

  private def allOccurrencesOf(input: List[String], txt: String, from: Int): List[Int] = {
    val idx = input.indexOf(txt, from)
    if (idx == -1)
      List()
    else
      List(idx) ++ allOccurrencesOf(input, txt, idx + 1)
  }
}