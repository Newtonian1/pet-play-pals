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
INSERT INTO locations (location_name,address_1,address_2,city,state_abbreviation,zip_code,latitude,longitude) VALUES
('Garfield''s Place','135 Garfield Place','','Cincinnati','OH','45202',39.103830,-84.516410),
('Bark Park','1 W Corry St','','Cincinnati','OH','45219',39.128870,-84.509940),
('Bob''s Boxes for Cats to Sit In','62 S Washington St','','Tiffin','OH','44883',41.115772,-83.176672),
('Cats at the Crossroads Cat Park','200 Crossroads Blvd','','Bucyrus','OH','44820',40.832327,-82.961928),
('Max''s Water Park for Heckin'' Good Boys','8176 Brandywine Rd','','Northfield','OH','44067',41.277472,-81.537057),
('Pet Elevator','1275 Kinnear Rd #121','','Columbus','OH','43212',40.010434,-83.037015),
('Detroit Doggo Beach','440 Burroughs St #316','','Detroit','MI','48202',42.379873,-83.074919),
('Joe''s Cat Cafe','1776 Mentor Ave','Suite 355','Cincinnati','OH','45212',39.176383,-84.463375),
('Elder Street Home for Elder Pets','114 W Elder St','','Cincinnati','OH','45202',39.11553,-84.51896),
('Parrot''s Pet Pavillion','156 Pavilion Pkwy','','Newport','KY','41071',39.08956,-84.47604),
('The Cat Park','1660 Marion Rd','','Bucyrus','OH','44820',40.78797,-82.98641),
('Pike''s Place Pet Play Place','1840 Columbus Pike','','Delaware','OH','43015',40.264469,-83.06974),
('George''s Cat Village','200 Georgesville Rd','','Columbus','OH','43228',39.95052,-83.110603),
('Fish Market','203 Market Street','','Westlake','OH','44145',41.457481,-81.95108),
('Canadian Cat Cafe','2401 Ontario St','','Cleveland','OH','44115',41.49493,-81.685257),
('Detroit Bark City','35830 Detroit Rd','','Avon','OH','44011',41.458649,-82.016342),
('Bark Force One','420 Presidential Dr','','Marion','OH','43302',40.58028,-83.12143),
('Vlad''s Puppy Place','456 Belmonte Park N','','Dayton','OH','45405',39.76557,-84.2018),
('Great Northern Dog Park','4779 Great Northern Blvd','','North Olmsted','OH','44070',41.41958,-81.9011),
('Cat Facts Corporate Headquarters','5075 Montgomery Rd','','Norwood','OH','45212',39.167122,-84.453583),
('The Royal Dog Park','6300 Kings Island Dr','','Mason','OH','45040',39.341419,-84.27784),
('Doggy Dance Studio','8715 Hamilton Cleves Pike Rd','','Cleves','OH','45002',39.22327,-84.72722);

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
INSERT INTO play_date (play_date_time,location_id,host_pet_id,status_id) VALUES
(now(),4001,2001,5001),
(now(),4002,2001,5002),
(now(),4003,2002,5003),
(now()+'3 days',4004,2001,5001),
(now()+'10 days',4005,2001,5002),
(now()+'20 days',4006,2002,5003),
(now()+'30 days',4007,2003,5001),
(now()+'31 days',4008,2003,5002),
(now()+'32 days',4009,2002,5003),

(now()+'33 days',4010,2001,5001),
(now()+'40 days',4011,2001,5002),
(now()+'50 days',4012,2002,5003),
(now()+'2 days',4013,2001,5001),
(now()+'1 days',4014,2001,5002),
(now()+'3 days',4015,2002,5003),
(now()+'2 days',4016,2001,5001),
(now()+'1 days',4017,2001,5002),
(now()+'3 days',4018,2002,5003),
(now()+'2 days',4019,2001,5001),
(now()+'1 days',4020,2003,5002),
(now()+'3 days',4021,2002,5003),
(now()+'2 days',4022,2001,5001),
(now()+'1 days',4001,2001,5002),
(now()+'3 days',4002,2002,5003),
(now()+'3 days',4003,2002,5003);

INSERT INTO play_date_pet (play_date_id, pet_id) VALUES
(9001,2002),
(9001,2003),
(9002,2002),
(9003,2001),
(9003,2002),
(9010,2002),
(9010,2003),
(9011,2002),
(9012,2001),
(9012,2002),
(9013,2001),
(9013,2003),
(9014,2002),
(9014,2003),
(9015,2001);

COMMIT TRANSACTION;
