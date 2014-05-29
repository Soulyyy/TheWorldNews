CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY,
username VARCHAR(225) NOT NULL UNIQUE,
password VARCHAR(225),
accessrights integer NOT NULL
);

create table if not exists userinfo(
userid INTEGER NOT NULL REFERENCES users(id),
firstname VARCHAR(225) NOT NULL,
surname VARCHAR(225) NOT NULL,
email VARCHAR(225) NOT NULL,
country INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS newsarticles(
id SERIAL NOT NULL PRIMARY KEY,
image varchar(225),
header TEXT NOT NULL,
content TEXT NOT NULL,
articlegroup integer NOT NULL,
author INTEGER NOT NULL REFERENCES users(id),
clickcount integer DEFAULT 0
);

					 CREATE TABLE IF NOT EXISTS tags(
id SERIAL NOT NULL PRIMARY KEY, userid INTEGER NOT NULL, articleid INTEGER NOT NULL, 
tagname VARCHAR(225) NOT NULL
);

CREATE INDEX content_ginidx on newsarticles using gin(to_tsvector('english',content));