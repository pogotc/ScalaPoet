package scalapoet

import scalatwitter.TwitterClient

object ScalaPoet {

  def main(args: Array[String]): Unit = {
    
    val client = new TwitterClient("", "")
    
    val timeline = client.getTimelineFor("pogotc")
    val allTweets = for(tweets <- timeline.items) yield tweets.text
    val poet = new Poet()
    val poem = poet.writePoem(allTweets.mkString("\n"))
    
    println(poem)
  }

}