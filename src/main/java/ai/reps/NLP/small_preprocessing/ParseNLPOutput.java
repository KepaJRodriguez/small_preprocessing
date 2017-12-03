package ai.reps.NLP.small_preprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.util.CoreMap;

public class ParseNLPOutput {

	/**
	 * @param nlpOutput
	 * @return
	 */
	public static HashMap<String, List<String>> parseAndSelect(
			List<CoreMap> nlpOutput) {

		List<String> content_words = new ArrayList<String>();
		List<String> sorted_lemmata = new ArrayList<String>();

		HashMap<String, List<String>> output = new HashMap<String, List<String>>();

		for (CoreMap sentence : nlpOutput) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			// Used to avoid that a multi-word NE is represented multiple times.
			String lastNE = "";
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				String word = token.get(TextAnnotation.class);
				// this is the POS tag of the token
				String lemma = token.get(LemmaAnnotation.class);
				String pos = token.get(PartOfSpeechAnnotation.class);
				// this is the NER label of the token
				String ne = token.get(NamedEntityTagAnnotation.class);

				if (ne.equals("LOCATION")) {
					if (!lastNE.equals("LOCATION")) {
						content_words.add("#LOCATION");
						sorted_lemmata.add("#LOCATION");
					}
					lastNE = "LOCATION";
				} else {
					if (ne.equals("PERCENT")) {
						if (!lastNE.equals("PERCENT")) {
							content_words.add("#PERCENT");
							sorted_lemmata.add("#PERCENT");
						}
						lastNE = "PERCENT";
					} else {
						if (ne.equals("PERSON")) {
							if (!lastNE.equals("PERSON")) {
								content_words.add("#PERSON");
								sorted_lemmata.add("#PERSON");
							}
							lastNE = "PERSON";
						} else {
							if (ne.equals("ORGANIZATION")) {
								if (!lastNE.equals("ORGANIZATION")) {
									content_words.add("#ORGANIZATION");
									lastNE = "ORGANIZATION";
									sorted_lemmata.add("#ORGANIZATION");
								}
								lastNE = "ORGANIZATION";
							} else {
								if (ne.equals("NUMBER")) {
									content_words.add("#NUMBER");
									lastNE = "NUMBER";
									sorted_lemmata.add("#NUMBER");
								} else {
									if (pos.matches("JJ.*||NN.*||RB.*||VB.*")) {
										content_words.add(lemma);
										lastNE = "";
										sorted_lemmata.add(lemma);
									} else {
										if (pos.matches("\\w.*")) {
											sorted_lemmata.add(lemma);
										}
										lastNE = "";
									}
								}
							}
						}
					}
				}
			}

		}

		output.put("content_words", content_words);
		output.put("sorted_lemmata", sorted_lemmata);

		return output;
	}

}
