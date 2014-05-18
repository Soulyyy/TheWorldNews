SELECT neswarticles.id,userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,newsarticles.header,newsarticles.articlegroup,clickcount FROM userinfo 
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE
					 articlegroup % 2 =0 ORDER BY id DESC limit 5;


SELECT * from newsarticles;


SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,
					newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo 
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE newsarticles.id=?;


SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,newsarticles.header,newsarticles.articlegroup,clickcount FROM userinfo 
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE
					articlegroup % 1 =0 ORDER BY id DESC limit 6;

SELECT users.id, COUNT(newsarticles.id) AS NumberOfArticles FROM newsarticles LEFT JOIN users ON users.id=newsarticles.author
				WHERE users.id=42 GROUP BY users.id;

SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,
					newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE newsarticles.id=42;

SELECT * from userinfo;
SELECT * FROM users;
SELECT * FROM newsarticles;

