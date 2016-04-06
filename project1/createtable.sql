DROP DATABASE IF EXISTS moviedb;
CREATE DATABASE moviedb;
use moviedb;

CREATE TABLE IF NOT EXISTS movies (
  id int not null AUTO_INCREMENT,
  title varchar(100) not null,
  year int not null,
  director varchar(100) not null,
  banner_url varchar(200) default '',
  trailer_url varchar(200) default '',
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stars (
   id int not null AUTO_INCREMENT,
   first_name varchar(50) not null,
   last_name varchar(50) not null,
   dob date,
   photo_url varchar(200) default '',
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS stars_in_movies (
   star_id int not null,
   movie_id int not null,
   FOREIGN KEY (star_id) REFERENCES stars(id),
   FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE IF NOT EXISTS genres (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS genres_in_movies (
   genre_id int not null,
   movie_id int not null,
   FOREIGN KEY (genre_id) REFERENCES genres(id),
   FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE IF NOT EXISTS creditcards (
   id varchar(20) not null,
   first_name varchar(50) not null,
   last_name varchar(50) not null,
   expiration date not null,
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS customers (
   id int not null AUTO_INCREMENT,
   first_name varchar(50) not null,
   last_name varchar(50) not null,
   cc_id varchar(20) not null,
   address varchar(200) not null,
   email varchar(50) not null,
   password varchar(20) not null,
   FOREIGN KEY (cc_id) REFERENCES creditcards(id),
   PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS sales (
   id int not null AUTO_INCREMENT,
   customer_id int not null,
   movie_id int not null,
   sale_date date not null,
   FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE ON UPDATE CASCADE,
   FOREIGN KEY (movie_id) REFERENCES movies(id),
   PRIMARY KEY (id)
);













