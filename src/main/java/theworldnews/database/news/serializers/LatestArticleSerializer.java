package theworldnews.database.news.serializers;

import java.lang.reflect.Type;

import theworldnews.database.news.objects.Article;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

//Thanks Tõnis for tip
public class LatestArticleSerializer implements JsonSerializer<Article> {
	public JsonElement serialize(Article article, Type typeOfSrc,
			JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		obj.addProperty("id", article.id);
		obj.addProperty("header", article.header);
		obj.addProperty("image", article.image);
		return obj;
	}

}