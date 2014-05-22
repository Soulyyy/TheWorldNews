package theworldnews.database.news.objects;

import theworldnews.database.users.objects.User;

public class ArticleUserTuple {

	Article article;
	User user;

	public ArticleUserTuple(Article article, User user) {
		this.user = user;
		this.article = article;
	}

}
