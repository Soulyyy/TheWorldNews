package TheWorldNews.newsdata;

public class NewsEncoding {
	
	//Article encodings, change file name if more final data is generated
	private static final int news =2;
	private static final int business =3;
	private static final int sports =5;
	private static final int science =7;
	private static final int arts =11;
	private static final int fashionAndStyle =13;
	
	//Null means a problem
	private static int convertToInt(String input) {
		if(input.equals("News")) return news;
		if(input.equals("Business")) return business;
		if(input.equals("Sports")) return sports;
		if(input.equals("Science")) return science;
		if(input.equals("Arts")) return arts;
		if(input.equals("FasionAndStyle")) return fashionAndStyle;
		else return 0;
		
	}
	
	//Convert output to single int
	public static int jointArticleConvertToInt(String longQuery) {
		String[] separateValues = longQuery.split(";");
		int multiple = 1;
		for(String value: separateValues) {
			multiple = multiple *convertToInt(value);
		}
		return multiple;
	}
	//If String empty, throw error, assert this
	private static String mapNumberToString(int input) {
		if(input==2) return "News";
		if(input==3) return "Business";
		if(input==5) return "Sports";
		if(input==7) return "Science";
		if(input==11) return "Arts";
		if(input==13) return "FashionAndStyle";
		else return "";
	}

	//If null, serious trouble in database
	public static String convertArticleEncodingToString(int encoding) {
		int[] valueSpace = {2,3,5,7,11,13};
		String output="";
		for(int i : valueSpace) {
			if(encoding%i==0){
				output+= mapNumberToString(i)+";";
			}
		}return output;
	}
	
}
