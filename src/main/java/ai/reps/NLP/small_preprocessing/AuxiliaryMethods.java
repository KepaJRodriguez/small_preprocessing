package ai.reps.NLP.small_preprocessing;

import java.util.List;

public class AuxiliaryMethods {

	
	public static String list2string(List<String> listOfStrings){
		
		StringBuilder sb = new StringBuilder();
		for (String s : listOfStrings) {
			sb.append(s);
			sb.append(" ");
		}

		return sb.toString();
	}
	
}
