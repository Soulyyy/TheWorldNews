

CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY,
username VARCHAR(225) NOT NULL UNIQUE, password VARCHAR(225),
accessrights integer NOT NULL);

create table if not exists userinfo(userid INTEGER PRIMARY KEY, firstname VARCHAR(225) NOT NULL,
surname VARCHAR(225) NOT NULL, email VARCHAR(225) NOT NULL, country integer NOT NULL)

CREATE TABLE IF NOT EXISTS newsarticles(id SERIAL NOT NULL PRIMARY KEY, image varchar(225),
					 header TEXT NOT NULL, content TEXT NOT NULL, articlegroup integer NOT NULL, author integer not null, clickcount integer DEFAULT 0);

					 
CREATE TABLE IF NOT EXISTS tags(id SERIAL NOT NULL PRIMARY KEY, tagname VARCHAR(225) NOT NULL UNIQUE, tagcount INTEGER NOT NULL);
