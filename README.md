# ScalaPoet
Scala app that generates poems from Twitter feeds.

## Configuring

In order to download a list of tweets you will need to provide a consumer key and consumer secret key and put 
them into a file called scalapoet.json, a dist file is provided as a reference for the format it should take.

## Compiling

The application can be compiled using SBT and running this command:

    sbt assembly

That will produce a jar file that can be used to generate poems

## Running

The application accepts one parameter which is the Twitter handle to use for reference, it is invoked with:

    java -jar scalapoet.jar pogotc

This should return a beautiful poem, how exciting!
