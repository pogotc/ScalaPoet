package scalapoet

class SuffixMapper {

  def map(input: List[String]): Map[String, List[String]] = {
    mapWithSuffix(input.distinct, 5)
  }

  private def mapWithSuffix(input: List[String], suffixLength: Int): Map[String, List[String]] = {
    if (input.isEmpty || suffixLength < 3) return Map()
    else {
      val wordMap =
    	  input.filter(_.size >= suffixLength).
          groupBy(x => x.substring(x.size - suffixLength)).
          filter(x => x._2.size > 1)

      val foundWords = wordMap.flatMap(_._2).toList

      return wordMap ++ mapWithSuffix(input.filter(x => !foundWords.contains(x)), suffixLength - 1)
    }
  }

}