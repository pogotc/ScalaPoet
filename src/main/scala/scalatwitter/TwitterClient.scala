package scalatwitter

import sun.misc.BASE64Encoder
import scalaj.http.Http
import scalaj.http.Base64
import spray.json._
import DefaultJsonProtocol._
import com.sun.jmx.remote.internal.Unmarshal

case class Tweet(text: String)
case class Timeline(items: List[Tweet])

object TwitterProtocol extends DefaultJsonProtocol {
  implicit val tweetFormat = jsonFormat1(Tweet)
  implicit object TimelineJsonFormat extends RootJsonFormat[Timeline] {
    def read(value: JsValue) = Timeline(value.convertTo[List[Tweet]])
    def write(f: Timeline) = ???
  }
}

class TwitterClient(consumerKey: String, consumerSecretKey: String) {
  import TwitterProtocol._

  val accessToken = getTokenForKeys()

  private def getTokenForKeys(): String = {
    val encodedKey = Base64.encodeString(consumerKey + ":" + consumerSecretKey)
    val data = Http("https://api.twitter.com/oauth2/token").
      header("Authorization", "Basic " + encodedKey).
      postForm(Seq("grant_type" -> "client_credentials")).
      asString
    val mappedData = data.body.parseJson.convertTo[Map[String, String]]
    mappedData("access_token")
  }

  def getTimelineFor(user: String): Timeline = {
    val data = Http("https://api.twitter.com/1.1/statuses/user_timeline.json").
      header("Authorization", "Bearer " + accessToken).
      param("screen_name", user).
      asString
    data.body.parseJson.convertTo[Timeline]
  }
}