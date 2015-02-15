package scalapoet

import spray.json._
import DefaultJsonProtocol._

case class PoetConfig(consumerKey: String, consumerSecretKey: String)

object PoetConfigProtocol extends DefaultJsonProtocol {
    implicit val poetConfigFormat = jsonFormat2(PoetConfig)
  }

case class PoetConfigReader(configPath: String) {
  import PoetConfigProtocol._
  
  val input = scala.io.Source.fromFile(configPath)("UTF-8").mkString.parseJson
  		  
  def getConfig(): PoetConfig = {
    input.convertTo[PoetConfig]
  }
}