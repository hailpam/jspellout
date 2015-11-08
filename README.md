
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



Example
=========

 > mvn exec:java

```
 Welcome to jspellout v1.0!
 __________________________

 Usage: type any number (up to billions) to request the spell out
 =================================================================
  QUIT : Exit





 +------------------------------------+
 Please, insert the number to spell out 
 >> 
 1230
 [EnglishRules][applyRules] Order of magnitude in [4, 6] digits 
 	-->> Processing Thousands
 [DigitsProcessor][processThousands] Request to process [4]digits
 IN >>>
 [Pipeline][ThousandsHandler]
 [Pipeline][HundredsHandler]
 [Pipeline][TensHandler]
 [Pipeline][UnitsHandler]
 [Pipeline][HundredsHandler]
 [Pipeline][TensHandler]
 <<< OUT
 [EnglishTemplates][applyTemplate] Order of magnitude [UNITS] 
	-->> Translating Units
 [EnglishTemplates][applyTemplate] Order of magnitude [THOUSANDS] 
	-->> Translating Thousands
 [EnglishTemplates][applyTemplate] Order of magnitude [HUNDREDS] 
	-->> Translating Hundreds
 [EnglishTemplates][applyTemplate] Order of magnitude [TENS] 
	-->> Translating Tens
 +------------------------------------+
 Spelling-->>[one thousand two hundreds and thirty]

 +------------------------------------+
 Please, insert the number to spell out 
 >>
```

Versions
=========

- 1.0 - first relesae




