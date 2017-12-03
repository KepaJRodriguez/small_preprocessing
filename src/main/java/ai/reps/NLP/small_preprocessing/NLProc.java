package ai.reps.NLP.small_preprocessing;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class NLProc {


	
	public static List<CoreMap> analyzeText(String text) throws IOException{	
		Properties props = new Properties();

		props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse");

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// create an empty Annotation just with the given text
		Annotation document = new Annotation(text);

		// run all Annotators on this text
		pipeline.annotate(document);


		List<CoreMap> analysis = document.get(SentencesAnnotation.class);		

		
		return analysis;
		
	}
	
	
}
