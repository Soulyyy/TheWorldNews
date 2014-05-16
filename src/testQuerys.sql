

SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,newsarticles.header,newsarticles.articlegroup,clickcount FROM userinfo
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE
					articlegroup % 1 =0 ORDER BY id DESC limit 2;

SELECT * from userinfo;
SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,
					 newsarticles.header, newsarticles.content, newsarticles.articlegroup FROM userinfo
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE userinfo.userid=42;


SELECT * FROM users;
SELECT * FROM newsarticles;

