BEGIN TRANSACTION;

DROP TABLE IF EXISTS 
	pet_personality, 
	play_date, 
	pet_sizes, 
	pet_genders, 
	pet_types, 
	statuses, 
	locations, 
	personalities, 
	pets, 
	users;
DROP SEQUENCE IF EXISTS 
	seq_play_date_id, 
	seq_pet_size_id, 
	seq_gender_id, 
	seq_pet_type_id, 
	seq_status_id, 
	seq_location_id, 
	seq_personality_id, 
	seq_pet_id, 
	seq_user_id;

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
	breed varchar(50),
	gender_id int NOT NULL,
	size_id int NOT NULL,
	is_fixed boolean NOT NULL DEFAULT FALSE,
	is_up_to_date_with_vaccinations boolean NOT NULL DEFAULT FALSE,
	pet_description varchar(2000),
	CONSTRAINT PK_pet PRIMARY KEY (pet_id),
	CONSTRAINT FK_pet_user FOREIGN KEY (owner_id) REFERENCES users (user_id)
);

CREATE SEQUENCE seq_personality_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
	
CREATE TABLE personalities (
	personality_id int NOT NULL DEFAULT nextval('seq_personality_id'),
	personality_type varchar(12) NOT NULL,
	CONSTRAINT PK_personalities PRIMARY KEY (personality_id)
);

CREATE SEQUENCE seq_location_id
	INCREMENT BY 1
	START WITH 4001
	NO MAXVALUE;
	
CREATE TABLE locations (
	location_id int NOT NULL DEFAULT nextval('seq_location_id'),
	address_1 varchar(100),
	address_2 varchar(100),
	city varchar(50) NOT NULL,
	state_abbreviation varchar(2) NOT NULL,
	zip_code varchar(5) NOT NULL,
	latitude decimal(9,6),
	longitude decimal(9,6),
	CONSTRAINT PK_location PRIMARY KEY (location_id)
);
	
CREATE SEQUENCE seq_status_id
  INCREMENT BY 1
  START WITH 5001
  NO MAXVALUE;
	
CREATE TABLE statuses (
	status_id int NOT NULL DEFAULT nextval('seq_status_id'),
	status varchar(8),
	CONSTRAINT PK_statuses PRIMARY KEY (status_id)
);

CREATE SEQUENCE seq_pet_type_id
  INCREMENT BY 1
  START WITH 6001
  NO MAXVALUE;
	
CREATE TABLE pet_types (
	pet_type_id int NOT NULL DEFAULT nextval('seq_pet_type_id'),
	pet_type varchar(8),
	CONSTRAINT PK_pet_types PRIMARY KEY (pet_type_id)
);

CREATE SEQUENCE seq_gender_id
  INCREMENT BY 1
  START WITH 7001
  NO MAXVALUE;
	
CREATE TABLE pet_genders (
	gender_id int NOT NULL DEFAULT nextval('seq_gender_id'),
	gender varchar(8),
	CONSTRAINT PK_genders PRIMARY KEY (gender_id)
);

CREATE SEQUENCE seq_pet_size_id
  INCREMENT BY 1
  START WITH 8001
  NO MAXVALUE;
	
CREATE TABLE pet_sizes (
	pet_size_id int NOT NULL DEFAULT nextval('seq_pet_size_id'),
	pet_size varchar(12),
	CONSTRAINT PK_pet_sizes PRIMARY KEY (pet_size_id)
);

CREATE SEQUENCE seq_play_date_id
  INCREMENT BY 1
  START WITH 9001
  NO MAXVALUE;
	
CREATE TABLE play_date (
	play_date_id int NOT NULL DEFAULT nextval('seq_play_date_id'),
	play_date_time timestamp NOT NULL DEFAULT now(),
	location_id int NOT NULL,
	first_pet_id int NOT NULL,
	second_pet_id int NOT NULL,
	status_id int NOT NULL DEFAULT 5003,
	CONSTRAINT PK_play_date PRIMARY KEY (play_date_id),
	CONSTRAINT FK_play_date_location FOREIGN KEY (location_id) REFERENCES locations (location_id),
	CONSTRAINT FK_play_date_status FOREIGN KEY (status_id) REFERENCES statuses (status_id),
	CONSTRAINT FK_play_date_first_pet FOREIGN KEY (first_pet_id) REFERENCES pets (pet_id),
	CONSTRAINT FK_play_date_second_pet FOREIGN KEY (second_pet_id) REFERENCES pets (pet_id)
);

CREATE TABLE pet_personality (
	pet_id int NOT NULL,
	personality_id int NOT NULL,
	CONSTRAINT PK_pet_personality PRIMARY KEY (pet_id, personality_id)
);
	
COMMIT TRANSACTION;
