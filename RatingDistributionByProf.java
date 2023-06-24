package finalproject;

import javafx.util.Pair;
//Benedict Tan 261102994
public class RatingDistributionByProf extends DataAnalyzer {
    public RatingDistributionByProf(Parser p) {
        super(p);
    }

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		MyHashTable<String, Integer> categories = new MyHashTable<>();
		categories.put("1", 0);
		categories.put("2", 0);
		categories.put("3", 0);
		categories.put("4", 0);
		categories.put("5", 0);
		boolean found = false;
		for (String[] profReview : parser.data) {
			float rating = Float.parseFloat(profReview[parser.fields.get("student_star")]);
			String profName = profReview[parser.fields.get("professor_name")].trim().toLowerCase();
			if (profName.equalsIgnoreCase(keyword.trim().toLowerCase())) {
				found = true;
				String rateCat = "";
				if (rating >= 1 && 2 > rating) {
					rateCat = "1";
				} else if (rating >= 2 && 3 > rating) {
					rateCat = "2";
				} else if (rating >= 3 && 4 > rating) {
					rateCat = "3";
				} else if (rating >= 4 && 5 > rating) {
					rateCat = "4";
				} else if (rating == 5) {
					rateCat = "5";
				}
				Integer value = categories.get(rateCat);
				if (value != null) {
					categories.put(rateCat, value + 1);
				} else {
					categories.put(rateCat, 1);
				}
			}
			}
		if (!found){
			return null;
		}
		return categories;
		//ADD YOUR CODE ABOVE THIS
		}



	@Override
	public void extractInformation() {
		for (String[] profReview : parser.data) {
			float rating = Float.parseFloat(profReview[parser.fields.get("student_star")]);
			String profName = profReview[parser.fields.get("professor_name")].trim().toLowerCase();
		}
	}}

