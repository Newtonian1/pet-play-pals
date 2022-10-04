BEGIN TRANSACTION;



INSERT INTO users (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO locations (location_name,address_1,address_2,city,state_abbreviation,zip_code,latitude,longitude) VALUES
('Garfield''s Place','135 Garfield Place','','Cincinnati','OH','45202',39.103830,-84.516410),
('Bark Park','1 W Corry St','','Cincinnati','OH','45219',39.128870,-84.509940),
('Bob''s Boxes for Cats to Sit In','62 S Washington St','','Tiffin','OH','44883',41.115772,-83.176672);

INSERT INTO personalities (personality_type) VALUES
('friendly'),
('hyper'),
('serious'),
('shy'),
('calm'),
('loner'),
('ball'),
('playful');

INSERT INTO statuses (status) VALUES
('approved'),
('rejected'),
('pending');

INSERT INTO pet_types (pet_type) VALUES
('dog'),
('cat'),
('bird'),
('fish'),
('reptile'),
('other');

INSERT INTO pet_genders (gender) VALUES
('female'),
('male');

INSERT INTO pet_sizes (pet_size) VALUES
('extra-small'),
('small'),
('medium'),
('large'),
('extra-large');


INSERT INTO pets (pet_name,owner_id,pet_type_id,breed,gender_id,size_id,is_fixed,is_up_to_date_with_vaccinations,pet_description) VALUES
('Minnie',1003,6001,'Who knows',7001,8003,true,true,'Chaos incarnate'),
('Bob',1003,6002,'He''s a cat!',7002,8002,true,true,'Eats plants. Is a menace'),
('DOGGO',1003,6001,'DOG',7002,8004,true,true,'test description');

-- Play Date Test Data
INSERT INTO play_date (play_date_time,location_id,host_pet_id,status_id) VALUES
(TO_TIMESTAMP(
     '2022-10-31 9:30:20',
     'YYYY-MM-DD HH:MI:SS'
 )
,4001,2001,5001),
((TO_TIMESTAMP ('2022-10-31 09:30:20', 'YYYY-MM-DD HH:MI:SS'),4002,2001,5002),
((TO_TIMESTAMP ('2022-10-31 09:30:20', 'YYYY-MM-DD HH:MI:SS'),4003,2002,5003);

INSERT INTO play_date_pet (play_date_id, pet_id) VALUES
(9001,2003),
(9003,2003),
(9002,2002);



COMMIT TRANSACTION;
