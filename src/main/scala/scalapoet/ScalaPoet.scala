package scalapoet

object ScalaPoet {

  def main(args: Array[String]): Unit = {
    val input = scala.io.Source.fromFile("tweets.txt")("UTF-8").mkString
      
    val poet = new Poet()
    
    val poem = poet.writePoem(input)
    
    println(poem)
  }

}