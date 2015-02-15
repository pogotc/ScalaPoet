package scalapoet

import scalatwitter.TwitterClient

object ScalaPoet {

  def main(args: Array[String]): Unit = {
    
    val config = PoetConfigReader("scalapoet.json").getConfig
    
    val client = new TwitterClient(config.consumerKey, config.consumerSecretKey)
    val timeline = client.getTimelineFor(args(0))
    val allTweets = for(tweets <- timeline.items) yield tweets.text
    val poet = new Poet()
    val poem = poet.writePoem(allTweets.mkString("\n"))
    
    println(poem)
  }

}