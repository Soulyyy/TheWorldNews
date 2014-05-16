

SELECT users.id, COUNT(newsarticles.id) AS NumberOfArticles FROM newsarticles LEFT JOIN users ON users.id=newsarticles.author
				WHERE users.id=42 GROUP BY users.id;

SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,
					newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE newsarticles.id=42;

SELECT * from userinfo;
SELECT * FROM users;
SELECT * FROM newsarticles;

