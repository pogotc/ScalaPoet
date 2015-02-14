package scalapoet

import java.util.Random

class PoetParser(input: String) {

  val suffixMapper = new SuffixMapper
  val wordListWithPunctuation = input.replaceAll("\n", " ").split(" ").toList
  val wordListWithoutPunctuation = createTextList
  val rand = new Random(System.currentTimeMillis());

  def createTextList(): List[String] = {
    val charsToIgnore = "!,.?".toSet
    input.filterNot(charsToIgnore)
      .replaceAll("\n", " ")
      .split(" ")
      .toList
  }

  def createSuffixMap(): Map[String, List[String]] = suffixMapper.map(createTextList())

  def getContextForWord(word: String): String = {
    val wordOccurrences = allOccurrencesOf(wordListWithoutPunctuation, word, 1)
    val wordPos = wordOccurrences(rand.nextInt(wordOccurrences.length))
    val line = wordListWithPunctuation.slice(wordPos - 4, wordPos + 1).mkString(" ")

    return formatLine(line)
  }

  private def formatLine(line: String): String = line(0).toUpper + line.substring(1)

  private def allOccurrencesOf(input: List[String], txt: String, from: Int): List[Int] = {
    val idx = input.indexOf(txt, from)
    if (idx == -1)
      List()
    else
      List(idx) ++ allOccurrencesOf(input, txt, idx + 1)
  }
}