package theworldnews.database.news.objects;

public class ArticlegroupEncoding {

	// Article encodings, change file name if more final data is generated
	private static final int news = 2;
	private static final int business = 3;
	private static final int sports = 5;
	private static final int science = 7;
	private static final int arts = 11;
	private static final int fashionAndStyle = 13;

	// Null means a problem
	private static int oneArticleStringToInt(String input) {
		if (input.equals("News"))
			return news;
		if (input.equals("Business"))
			return business;
		if (input.equals("Sports"))
			return sports;
		if (input.equals("Science"))
			return science;
		if (input.equals("Arts"))
			return arts;
		if (input.equals("FashionAndStyle"))
			return fashionAndStyle;
		else
			return 0;

	}

	/**
	 * 
	 * @param articlegroups
	 *            : String, articlegroups separated by ";"
	 * @return integer value of the articlegroup
	 */
	public static int stringToInt(String articlegroups) {
		System.out.println("We got this val:");
		System.out.println(articlegroups);
		String[] separateValues = articlegroups.split(";");
		int multiple = 1;
		for (String value : separateValues) {
			multiple = multiple * oneArticleStringToInt(value);
		}
		return multiple;
	}

	// If String empty, throw error, assert this
	private static String oneArticleIntToString(int input) {
		if (input == 2)
			return "News";
		if (input == 3)
			return "Business";
		if (input == 5)
			return "Sports";
		if (input == 7)
			return "Science";
		if (input == 11)
			return "Arts";
		if (input == 13)
			return "FashionAndStyle";
		else
			return "";
	}

	/**
	 * 
	 * @param encoding
	 *            : int, value of the articlegroups
	 * @return : articlegroups separated by ";"
	 */
	public static String intToString(int encoding) {
		System.out.println("We want to encode int, WHY?");
		int[] valueSpace = { 2, 3, 5, 7, 11, 13 };
		String output = "";
		for (int i : valueSpace) {
			if (encoding % i == 0) {
				output += oneArticleIntToString(i) + ";";
			}
		}
		return output;
	}

}
