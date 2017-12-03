package ai.reps.NLP.small_preprocessing;



import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.util.CoreMap;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	String text_1 = "If you want to know what the Walmart of the future looks like, "
    			+ "it's best to look at Amazon for a rough sketch.";
    	
    	String text_2 = "In rare public remarks, US President Donald Trump’s senior adviser and son-in-law "
    			+ "Jared Kushner said on Sunday that Trump has not yet made up his mind on "
    			+ "moving the US embassy to Jerusalem or recognizing the city as Israel’s capital. ";
    	
    	List<CoreMap> analysisText_1 = NLProc.analyzeText(text_1);
    	List<CoreMap> analysisText_2 = NLProc.analyzeText(text_2);
    	
    	String text_1_lemmas = AuxiliaryMethods
				.list2string(ParseNLPOutput.parseAndSelect(
						analysisText_1).get("sorted_lemmata"));
    	
    	String text_1_content_words = AuxiliaryMethods
				.list2string(ParseNLPOutput.parseAndSelect(
						analysisText_1).get("content_words"));
    	
    	String text_2_lemmas = AuxiliaryMethods
				.list2string(ParseNLPOutput.parseAndSelect(
						analysisText_2).get("sorted_lemmata"));
    	
    	String text_2_content_words = AuxiliaryMethods
				.list2string(ParseNLPOutput.parseAndSelect(
						analysisText_2).get("content_words"));
    	
    	
    	System.out.println();
    	System.out.println(text_1_lemmas);
    	System.out.println();
    	System.out.println(text_1_content_words);
    	System.out.println();
    	System.out.println();
    	System.out.println(text_2_lemmas);
    	System.out.println();
    	System.out.println(text_2_content_words);
    	System.out.println();
    }
}
