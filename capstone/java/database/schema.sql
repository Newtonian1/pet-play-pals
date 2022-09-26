BEGIN TRANSACTION;


DROP TABLE IF EXISTS pets, users;
DROP SEQUENCE IF EXISTS seq_pet_id, seq_user_id;

CREATE SEQUENCE seq_user_id
  	INCREMENT BY 1
  	START WITH 1001
  	NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_pet_id
  	INCREMENT BY 1
  	START WITH 2001
  	NO MAXVALUE;

CREATE TABLE pets (
	pet_id int NOT NULL DEFAULT nextval('seq_pet_id'),
	pet_name varchar(50) NOT NULL,
	owner_id int NOT NULL,
	pet_type_id int NOT NULL,
	breed_id int NOT NULL,
	gender_id int NOT NULL,
	size_id int NOT NULL,
	is_fixed boolean NOT NULL DEFAULT FALSE,
	is_up_to_date_with_vaccinations boolean NOT NULL DEFAULT FALSE,
	description varchar(2000),
	CONSTRAINT PK_pet PRIMARY KEY (pet_id),
	CONSTRAINT FK_pet_user FOREIGN KEY (owner_id) REFERENCES users (user_id)
);

COMMIT TRANSACTION;
