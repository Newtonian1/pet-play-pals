BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES 
('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES 
('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

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

-- Testing data added below this line
-- User Test Data
INSERT INTO users (username,password_hash,role) VALUES 
('Kat','$2a$10$Ja74X4I5od5Svp1uQAeed.OKYzGJRNB0HQPX3w8icyFlIrqTtmZe2','ROLE_USER'),
('Abbie','$2a$10$0x1LwQn7c6Ni43km1Us2m.e4lE8zLHkeZsngseTBKGJbcvSq9UMJG','ROLE_USER'),
('Aaron','$2a$10$AuGB4a/7W6VqKz5vBbtBQOGsfxIbh/8yDeb1HL7MM3msYjeKdQK/C','ROLE_USER'),
('Joe','$2a$10$H5GRgvtQeiJAtQlopW4mY.1A3ebpjtO6RUjn1UBWAj5TqzTwsRAVO','ROLE_USER');

-- Location Test Data
INSERT INTO locations (address_1,address_2,city,state_abbreviation,zip_code,latitude,longitude) VALUES
('135 Garfield Place','','Cincinnati','OH','45202',39.103830,-84.516410),
('1 W Corry St','','Cincinnati','OH','45219',39.128870,-84.509940),
('62 S Washington St','','Tiffin','OH','44883',41.115772,-83.176672),
('200 Crossroads Blvd','','Bucyrus','OH','44820',40.832327,-82.961928),
('8176 Brandywine Rd','','Northfield','OH','44067',41.277472,-81.537057),
('1275 Kinnear Rd #121','','Columbus','OH','43212',40.010434,-83.037015),
('440 Burroughs St #316','','Detroit','MI','48202',42.379873,-83.074919),
('1776 Mentor Ave','Suite 355','Cincinnati','OH','45212',39.176383,-84.463375);

-- Pet & personality Test Data
INSERT INTO pets (pet_name,owner_id,pet_type_id,breed,gender_id,size_id,is_fixed,is_up_to_date_with_vaccinations,pet_description) VALUES
('Minnie',1003,6001,'Who knows',7001,8003,true,true,'Chaos incarnate'),
('Bob',1003,6002,'He''s a cat!',7002,8002,true,true,'Eats plants. Is a menace'),
('DOGGO',1005,6001,'DOG',7002,8004,true,true,'test description');

INSERT INTO pet_personality (pet_id,personality_id) VALUES
(2001,3001),
(2001,3002),
(2002,3003),
(2002,3005),
(2003,3007),
(2003,3008);

-- Play Date Test Data
INSERT INTO play_date (play_date_time,location_id,first_pet_id,second_pet_id,status_id) VALUES
(now()+'3 days',4001,2001,2003,5001),
(now()+'10 days',4005,2001,2002,5002),
(now()+'20 days',4002,2002,2003,5003);

COMMIT TRANSACTION;
