

SELECT userinfo.userid, userinfo.firstname, userinfo.surname,newsarticles.image,newsarticles.header,newsarticles.articlegroup,clickcount FROM userinfo
					INNER JOIN newsarticles ON userinfo.userid=newsarticles.author WHERE
					articlegroup % 2 =0 ORDER BY id DESC limit 18;

SELECT * from userinfo;
SELECT * FROM users;
SELECT * FROM newsarticles;

