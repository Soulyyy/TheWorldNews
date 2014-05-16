
SELECT * from userinfo;
SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,
					 newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE userinfo.userid=42;


SELECT * FROM users;
SELECT * FROM newsarticles;

