package scalapoet

import java.util.Random

class Poet {

  val rand = new Random(System.currentTimeMillis());

  def writePoem(inspiration: String): String = {
    val parser = new PoetParser(inspiration)
    val poem = List(generateVerse(parser), generateVerse(parser), generateVerse(parser))
    
    poem mkString "\n\n"
  }

  private def getRandomSuffix(suffixMap: Map[String, List[String]]): String = {
    val idx = rand.nextInt(suffixMap.size)
    suffixMap.keys.toVector(idx)
  }

  private def getRandomWordsFromList(words: List[String]): (String, String) = {
    val idx = rand.nextInt(words.size)
    val firstWord = words(idx)

    var nextIdx = idx
    while (nextIdx == idx) {
      nextIdx = rand.nextInt(words.size)
    }

    val secondWord = words(nextIdx)

    (firstWord, secondWord)
  }

  private def generateVerse(parser: PoetParser): String = {
	  val linesOne = getRhymingLines(parser)
	  val linesTwo = getRhymingLines(parser)
    
	  List(linesOne(0), linesTwo(0), linesOne(1), linesTwo(1)) mkString "\n"
  }
  private def getRhymingLines(parser: PoetParser): List[String] = {

    val suffixMap = parser.createSuffixMap()
    val chosenSuffix = getRandomSuffix(suffixMap)
    val words = getRandomWordsFromList(suffixMap(chosenSuffix))
    val firstLine = parser.getContextForWord(words._1)
    val secondLine = parser.getContextForWord(words._2)
    List(firstLine, secondLine)
  }
}