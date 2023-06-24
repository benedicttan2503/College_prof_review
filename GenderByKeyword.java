package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
//Benedict Tan 261102994
public class GenderByKeyword extends DataAnalyzer {
	
	public GenderByKeyword(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Integer> genderCount = new MyHashTable<String, Integer>();
		genderCount.put("M", 0);
		genderCount.put("F", 0);
		genderCount.put("X", 0);
		boolean found = false;
		for (String[] profReview : parser.data) {
			String word = profReview[parser.fields.get("comments")];
			if (word != null) {
				word = word.trim().toLowerCase().replaceAll("[^'a-z]", " ");
				String[] words = word.split("\s");
				Integer count = genderCount.get(keyword.toLowerCase());
				if (count == null) {
					count = 0;
				}
				for (String comment : words) {
					if (comment.equalsIgnoreCase(keyword.trim().toLowerCase())) {
						found = true;
						count++;
						break;
					}
				}
				String gender = profReview[parser.fields.get("gender")];
					if (gender.equals("F")) {
						Integer F = genderCount.get("F");
						if (F == null) {
							F = 0;}
						genderCount.put("F", F + count);
					} else if (gender.equals("M")) {
						Integer M = genderCount.get("M");
						if (M == null) {
							M = 0;}
						genderCount.put("M", M + count);}
					else if (gender.equals("X")){
						Integer X = genderCount.get("X");
						if (X == null) {
							X = 0;}
						genderCount.put("X", X + count);}
				}
			}
		if (!found){
			return null;
		}
		return genderCount;}



	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		//ADD YOUR CODE ABOVE THIS
	}

}
