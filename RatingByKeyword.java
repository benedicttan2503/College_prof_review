package finalproject;

import java.util.ArrayList;
import java.util.HashMap;
//Benedict Tan 261102994
public class RatingByKeyword extends DataAnalyzer {
	
    public RatingByKeyword(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Integer> ratingCount = new MyHashTable<String, Integer>();
		ratingCount.put("1", 0);
		ratingCount.put("2", 0);
		ratingCount.put("3", 0);
		ratingCount.put("4", 0);
		ratingCount.put("5", 0);
		boolean found = false;
		for (String[] profReview : parser.data) {
			String comment = profReview[parser.fields.get("comments")].trim().toLowerCase();
			comment = comment.replaceAll("[^'a-z]", " ");
			String[] words = comment.split(" ");
			Float rate = Float.parseFloat(profReview[parser.fields.get("student_star")]);
			if (rate != null) {
				String rateCat = "";
				if (rate >= 1 && 2 > rate) {
					rateCat = "1";
				} else if (rate >= 2 && 3 > rate) {
					rateCat = "2";
				} else if (rate >= 3 && 4 > rate) {
					rateCat = "3";
				} else if (rate >= 4 && 5 > rate) {
					rateCat = "4";
				} else if (rate == 5) {
					rateCat = "5";
				}
				for (String word : words) {
					if (word.equalsIgnoreCase(keyword.trim())) {
						found = true;
						Integer count = ratingCount.get(rateCat);
						if (count == null) {
							ratingCount.put(rateCat, 1);
							break;
						}
						else{
						ratingCount.put(rateCat, count + 1);
						break;}
					}
				}
			}
		}
		if (!found) {
			return null;
		}
		return ratingCount;
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		//ADD YOUR CODE ABOVE THIS
	}
}
