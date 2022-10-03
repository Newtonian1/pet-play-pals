BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO locations (location_name,address_1,address_2,city,state_abbreviation,zip_code,latitude,longitude) VALUES
('Garfield''s Place','135 Garfield Place','','Cincinnati','OH','45202',39.103830,-84.516410),
('Bark Park','1 W Corry St','','Cincinnati','OH','45219',39.128870,-84.509940),
('Bob''s Boxes for Cats to Sit In','62 S Washington St','','Tiffin','OH','44883',41.115772,-83.176672);

INSERT INTO pets (pet_name,owner_id,pet_type_id,breed,gender_id,size_id,is_fixed,is_up_to_date_with_vaccinations,pet_description) VALUES
('Minnie',1003,6001,'Who knows',7001,8003,true,true,'Chaos incarnate'),
('Bob',1003,6002,'He''s a cat!',7002,8002,true,true,'Eats plants. Is a menace'),
('DOGGO',1005,6001,'DOG',7002,8004,true,true,'test description');

COMMIT TRANSACTION;
