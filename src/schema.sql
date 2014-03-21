CREATE TABLE IF NOT EXISTS users (id SERIAL NOT NULL PRIMARY KEY,
username VARCHAR(225) NOT NULL UNIQUE, password VARCHAR(225),
accessrights integer NOT NULL, country integer NOT NULL);


CREATE TABLE IF NOT EXISTS newsarticles(id SERIAL NOT NULL PRIMARY KEY, image varchar(225),
					 header TEXT NOT NULL, content TEXT NOT NULL, articlegroup integer NOT NULL);