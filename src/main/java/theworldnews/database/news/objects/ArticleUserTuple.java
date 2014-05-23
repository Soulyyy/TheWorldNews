package theworldnews.database.news.objects;

import theworldnews.database.users.objects.UserInfo;

public class ArticleUserTuple {

	public Article article;
	public UserInfo userinfo;

	public ArticleUserTuple(Article article, UserInfo userinfo) {
		this.userinfo = userinfo;
		this.article = article;
	}

}
