SELECT * FROM users;
SELECT * FROM newsarticles;

SELECT * FROM users WHERE id = '0';


SELECT users.id, users.username, newsarticles.header
FROM users
INNER JOIN newsarticles ON users.id=newsarticles.id;

SELECT newsarticles.id, users.accessrights, newsarticles.header, newsarticles.content,
COUNT(newsarticles.header) AS newsHeaders
FROM(newsarticles
INNER JOIN users ON newsarticles.id = users.id)
GROUP BY users.id, users.accessrights, newsarticles.id;

EXPLAIN SELECT accessrights from users where username = 'jeesus' AND password = 'hesus';