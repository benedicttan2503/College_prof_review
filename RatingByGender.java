package finalproject;

import java.util.ArrayList;
//Benedict Tan 261102994
public class RatingByGender extends DataAnalyzer{

	public RatingByGender(Parser p) {
		super(p);
	}

	@Override
	public MyHashTable<String, Integer> getDistByKeyword(String keyword) {
		// ADD YOUR CODE BELOW THIS
		MyHashTable<String, Integer> genderCount = new MyHashTable<String, Integer>();
		genderCount.put("1", 0);
		genderCount.put("2", 0);
		genderCount.put("3", 0);
		genderCount.put("4", 0);
		genderCount.put("5", 0);
		String [] genQuaDiff = keyword.split(",");
		String gender = genQuaDiff[0].trim();
		String quaDiff = genQuaDiff[1].trim();
		boolean found = false;
		for (String[] profReview : parser.data) {
			String genderCat = String.valueOf(profReview[parser.fields.get("gender")]).trim();
			String choice = profReview[parser.fields.get("student_difficult")];
				if (quaDiff.equalsIgnoreCase("quality")) {
					choice = profReview[parser.fields.get("student_star")];
				}
			Float rating = Float.parseFloat(choice);
			if (rating != null){
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
				if (genderCat.equals(gender)) {
					found = true;
					Integer count = genderCount.get(rateCat);
					if (count != null) {
						genderCount.put(rateCat, count + 1);
					}
					else{
						genderCount.put(rateCat, 1);}
				}

			}}if (!found){
			return null;
		}
		return genderCount;
		//ADD YOUR CODE ABOVE THIS
	}

	@Override
	public void extractInformation() {
		// ADD YOUR CODE BELOW THIS

		//ADD YOUR CODE ABOVE THIS
	}
	public static void main(String[] src){
		Parser parser = new Parser("/src/finalproject/RateMyProf_Data_Gendered.csv");
		parser.read();
		RatingByGender ratingByGender = new RatingByGender(parser);
		MyHashTable<String, Integer> dist = ratingByGender.getDistByKeyword("F,difficulty");
		dist.get("1");
	}
}
