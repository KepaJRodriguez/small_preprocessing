# README #

Small preprocessing pipeline. For each text produces two outputs
a) Text lemmatized with punctuation marks removed.
b) Only content words (nouns, adjectives, verbs, adverbs).

Following entity types have been substituted by tags: PERSON, ORGANIZATION, LOCATION, NUMBER

### Compile  and run ###

Go to the "small_preprocessing" folder and give the command:

mvn compile assembly:single

A new folder "target" will be created with a *jar file. Run it and you will see the results of both analysis, the lemmatization and the content words extraction for the given texts.

### What is inside? ###

* App.java: that is the main class. You will find two texts in which most defined entities appear. 
* NLProc.java: we create a Stanford pipeline and send the text for processing. The output is a CoreMap with all levels of analysis used.
* ParseNLPOutput.java: Parser to extract the relevant parts of the analysis from the CoreMap. 
* AuxiliaryMethods.java: Only one method inside, just to convert the lists of tokens into a text string that can be later used to create the arff file.
