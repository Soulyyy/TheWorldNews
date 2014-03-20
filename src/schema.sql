CREATE TABLE IF NOT EXISTS user(id SERIAL NOT NULL PRIMARY KEY,
username VARCHAR(225) NOT NULL UNIQUE, password VARCHAR(225),
accessrights integer NOT NULL, islogged varchar(10));


CREATE TABLE IF NOT EXISTS newsarticle(id SERIAL NOT NULL PRIMARY KEY, image varchar(225),
					 header MEMO(65535) NOT NULL, articlegroup integer NOT NULL);