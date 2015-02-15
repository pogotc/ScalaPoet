package scalapoet

import spray.json._
import DefaultJsonProtocol._

object JsonFun {

  case class Person(name: String, age: String)
  case class FriendList(items: List[Person])

  object FriendsProtocol extends DefaultJsonProtocol {
    implicit val personFormat = jsonFormat2(Person)
    implicit object friendListJsonFormat extends RootJsonFormat[FriendList] {
      def read(value: JsValue) = FriendList(value.convertTo[List[Person]])
      def write(f: FriendList) = ???
    } 
  }

  def main(args: Array[String]): Unit = {

    import FriendsProtocol._

    val input = scala.io.Source.fromFile("test.json")("UTF-8").mkString.parseJson

    val friendList = input.convertTo[FriendList]

    println(friendList)
  }

}