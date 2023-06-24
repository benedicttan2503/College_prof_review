package finalproject;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Benedict Tan 261102994
public class RatingDistributionBySchool extends DataAnalyzer {
	public RatingDistributionBySchool(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		MyHashTable<String, Float> rating = new MyHashTable<>();
		MyHashTable<String, Integer> prof = new MyHashTable<>();
		MyHashTable<String, Integer> profAndRating = new MyHashTable<>();
		boolean found = false;
		for (String[] profReview : parser.data) {
			float rate = Float.parseFloat(profReview[parser.fields.get("student_star")]);
			String profName = profReview[parser.fields.get("professor_name")].trim().toLowerCase();
			String school = profReview[parser.fields.get("school_name")].trim().toLowerCase();
			if (school.equalsIgnoreCase(keyword.trim().toLowerCase())){
				found = true;
				Float ratingVal = rating.get(profName);
				if (ratingVal == null) {
					ratingVal = (float) 0;
				}
				Integer count = prof.get(profName);
				if (count == null) {
					count = 0;
				}
				prof.put(profName, count + 1);
				rating.put(profName, ratingVal + rate);}

		}
		for (String name : prof.getKeySet()) {
			profAndRating.put(name + "\n" + String.valueOf(Math.round(((rating.get(name)/prof.get(name))) * 100.0) / 100.0), prof.get(name));
		}
		if (!found){
			return null;
		}
		return profAndRating;
	}




	@Override
	public void extractInformation() {
		for (String[] profReview : parser.data) {
			float rate = Float.parseFloat(profReview[parser.fields.get("student_star")]);
			String profName = profReview[parser.fields.get("professor_name")].trim().toLowerCase();
			profName = profName.replaceAll("  ", " ");
			String school = profReview[parser.fields.get("school_name")].trim().toLowerCase();
		}
	}}



