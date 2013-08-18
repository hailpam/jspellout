
JSpellOut
=========

A portable Java SE framework to create application able to spell out numbers (
up to trillions), no matter of the anguage.

Current implementation has the full spelling feature for English numbers.

How to start the Test Application
=================================

From the project directory, simply perform the following entry:

 > mvn exec:java

The console application will be run and will prompt to insert the number to spell
out in English.


How to rebuild from scratch
===========================

In order to rebuild from scratch the project, you need to perform the following
steps:

1. clean the current build

 > mvn clean

2. compile the project

 > mvn compile

3. test the release

 > mvn test

4. install the jar in the repo

 > mvn install

Of course, the above steps can be collapsed in only one, as follows:

 > mvn clean compile test install


Alternative way to start the Test Application
=============================================

Once the above rebuilding has been accomplished, it is possible to perform the
following entry from the command prompt:
 
 > java -jar target/jspellout-1.0.jar

this command will start the test Application by using the executable JAR provided
in box by this release.

 
Versions
________

1.0 - first relesae




