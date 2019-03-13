
DROP SCHEMA IF EXISTS air_ticket_booking_system;

CREATE SCHEMA IF NOT EXISTS air_ticket_booking_system;

DROP TABLE IF EXISTS air_ticket_booking_system.users;
CREATE TABLE IF NOT EXISTS air_ticket_booking_system.users (
  ID SERIAL PRIMARY KEY,
  EMAIL VARCHAR(30) UNIQUE NOT NULL,
  PASSWORD VARCHAR(30) NOT NULL,
  NAME VARCHAR(30) NOT NULL,
  SURNAME VARCHAR(30) NOT NULL,
  PASSPORT_NUMBER VARCHAR(30) UNIQUE NOT NULL,
  DOB DATE NOT NULL,
  SEX VARCHAR(10) NOT NULL,
  PHONE_NUMBER VARCHAR(30) UNIQUE NOT NULL,
  ROLE VARCHAR(10) NOT NULL,
  BALANCE DOUBLE PRECISION NOT NULL
);

DROP TABLE IF EXISTS air_ticket_booking_system.airlines;
CREATE TABLE IF NOT EXISTS air_ticket_booking_system.airlines(
  ID SERIAL,
  CODE_AIRLINE VARCHAR(50) NOT NULL,
  NAME_AIRLINE VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS air_ticket_booking_system.airports;
CREATE TABLE IF NOT EXISTS air_ticket_booking_system.airports(
  ID SERIAL,
  CODE VARCHAR(50) NOT NULL,
  NAME VARCHAR(100) NOT NULL,
  COUNTRY VARCHAR(50) NOT NULL,
  CITY VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS air_ticket_booking_system.airplanes;
CREATE TABLE IF NOT EXISTS air_ticket_booking_system.airplanes(
  ID SERIAL,
  AIRPLANE_MODEL VARCHAR(50) NOT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE IF EXISTS air_ticket_booking_system.flight_details;
CREATE TABLE IF NOT EXISTS air_ticket_booking_system.flight_details (

  ID SERIAL PRIMARY KEY,
  ID_AIRPLANE INT NOT NULL,
  HAS_TRANSFER_POINT BOOLEAN NOT NULL,
  DEPARTURE_TIME TIMESTAMP NOT NULL,
  ARRIVAL_TIME TIMESTAMP NOT NULL,

  FOREIGN KEY (ID_AIRPLANE) REFERENCES air_ticket_booking_system.airplanes (id)

);

DROP TABLE IF EXISTS air_ticket_booking_system.orders;
CREATE TABLE IF NOT EXISTS air_ticket_booking_system.orders(
  ID SERIAL PRIMARY KEY,
  ID_USER INT NOT NULL,
  NUMBER_PASSENGERS INT NOT NULL,
  ID_DEPARTURE_AIRPORT INT NOT NULL,
  ID_ARRIVAL_AIRPORT INT NOT NULL,
  ID_AIRLINE INT NOT NULL,
  ID_FLIGHT_DETAILS INT NOT NULL,
  FARE DOUBLE PRECISION NOT NULL,
  IS_PAID BOOLEAN NOT NULL,
  ORDER_STATUS VARCHAR(20) NOT NULL,

  FOREIGN KEY (ID_USER) REFERENCES air_ticket_booking_system.users (ID),
  FOREIGN KEY (ID_DEPARTURE_AIRPORT) REFERENCES air_ticket_booking_system.airports (ID),
  FOREIGN KEY (ID_ARRIVAL_AIRPORT) REFERENCES air_ticket_booking_system.airports (ID),
  FOREIGN KEY (ID_AIRLINE) REFERENCES air_ticket_booking_system.airlines (ID),
  FOREIGN KEY (ID_FLIGHT_DETAILS) REFERENCES air_ticket_booking_system.flight_details (ID)

);

START TRANSACTION;
INSERT INTO air_ticket_booking_system.users (EMAIL, PASSWORD, NAME, SURNAME, PASSPORT_NUMBER, dob, SEX, PHONE_NUMBER, role, BALANCE) VALUES ('admin@admin.adm', 'Admin1', 'Admin', 'Admin', 'AD127HHAD', '1955-05-05', 'M', '+375(29)709-43-84', 'ADMIN', 0);
COMMIT;

START TRANSACTION;
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('123HKI78', 'American Airlines Group');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('7q89KHJ77', 'Deutsche Lufthansa');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('ADS78HHD', 'LATAM Airlines');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('617GB99', 'Delta Air Lines');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('87AHD07', 'Air France-KLM');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('56AJS89', 'International Airlines Group');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('77GDB6N', 'Emirates');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('89JA7CJ', 'China Southern Airlines');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('71HCI8X', 'Ryanair');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('40KC6KM8', 'Aeroflot');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('123HKI78', 'Turkish Airlines');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('173KDACI', 'Wizz Air');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('JAS71JKQ', 'Alitalia');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('1389LADSK', 'Air Europa');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('73KASD9A', 'Belavia');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('61KJDA80', 'All Nippon Airways');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('AO810ND', 'easyJet');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('29OAICW0', 'British Airways');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('PQO0213S', 'Qatar Airways');
INSERT INTO air_ticket_booking_system.airlines (CODE_AIRLINE, NAME_AIRLINE) VALUES ('82AISUA12', 'Egyptair');
COMMIT;

START TRANSACTION;
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('176HHH','Hartsfield-Jackson','USA','Atlanta');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('81OO7','Beijing Capital','China','Beijing');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('17BNM00','Dubai International','UAE','Dubai');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('172HB9','Tokyo International','Japan','Tokyo');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('54KJA8','Los Angeles International','USA','Los Angeles');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('781NHJ7','O’Hare International Airport','USA','Chicago');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('562BJ8','Hong Kong International Airport','China','Hong Kong');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('678KJ8','Paris Charles de Gaulle Airport','France','Roissy-en-France');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('82NKL9','Heathrow Airport','UK','London');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('671KJ78','Shanghai Pudong International Airport','(China','Shanghai');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('1784KLA0','Amsterdam Airport Schiphol','The Netherlands','Haarlemmermeer');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('92J9C9','Dallas/Fort Worth International Airport','USA','Dallas-Fort Worth');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('67JSJS9',' Guangzhou Baiyun International Airport','China','Guangzhou');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('JUF89SLE','Frankfurt Airport','Germany','Frankfurt');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('183LSP90','Istanbul Ataturk Airport','Turkey','Istanbul');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('37ALDUI90','Indira Gandhi International Airport','India','Delhi');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('039JDS7E','Singapore Changi Airport','Singapore','Changi');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('KD892JEV','John F. Kennedy International Airport','USA','New York');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('17KJA9E','Madrid Barajas Airport','Spain','Madrid');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('8LA90W2E','Toronto Pearson International Airport','Canada','Mississauga');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('190LSED0','Benito Juarez International Airport','Mexico','Mexico City');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('84LS90SS','Miami International Airport','USA','Miami');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('82LDKJ90','Sydney Kingsford-Smith Airport','Australia','Sydney');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('JX782JLA','Leonardo da Vinci–Fiumicino Airport','USA','Fiumicino-Lazio');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('372LADJ9','George Bush Intercontinental Airport','USA','Houston');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('92LAMKDC','Narita International Airport','Japan','Narita');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('WUD82KJD9','Sheremetyevo International Airport','Russia','Moscow');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('AOI8221LD','Minsk National Airport','Belarus','Minsk');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('21LAS9SW','Soekarno-Hatta International Airport','Indonesia','Tangerang');
INSERT INTO air_ticket_booking_system.airports (CODE, NAME, COUNTRY, CITY) VALUES ('AS2892LA','Suvarnabhumi Airport','Thailand','Bang Phli');
COMMIT;

START TRANSACTION;
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Airbus A220');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Airbus A350');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Boeing-737');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Boeing-777');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Boeing-787');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Embraer ERJ');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Bombardier Dash 8');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Superjet-100');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Ту-204');
INSERT INTO air_ticket_booking_system.airplanes (AIRPLANE_MODEL) VALUE ('Ил-114');
COMMIT;


START TRANSACTION;
INSERT INTO air_ticket_booking_system.flight_details(ID_AIRPLANE, HAS_TRANSFER_POINT, DEPARTURE_TIME, ARRIVAL_TIME) VALUES (1, 'TRUE', '2018-10-30 12:45:00', '2018-10-30 16:30:00');
INSERT INTO air_ticket_booking_system.flight_details(ID_AIRPLANE, HAS_TRANSFER_POINT, DEPARTURE_TIME, ARRIVAL_TIME) VALUES (2, 'FALSE', '2018-12-01 11:00:00', '2018-12-01 13:30:00');
INSERT INTO air_ticket_booking_system.flight_details(ID_AIRPLANE, HAS_TRANSFER_POINT, DEPARTURE_TIME, ARRIVAL_TIME) VALUES (3, 'FALSE', '2018-11-15 23:45:00', '2018-11-16 03:15:00');
INSERT INTO air_ticket_booking_system.flight_details(ID_AIRPLANE, HAS_TRANSFER_POINT, DEPARTURE_TIME, ARRIVAL_TIME) VALUES (4, 'TRUE', '2018-10-22 18:05:00', '2018-10-23 00:45:00');
INSERT INTO air_ticket_booking_system.flight_details(ID_AIRPLANE, HAS_TRANSFER_POINT, DEPARTURE_TIME, ARRIVAL_TIME) VALUES (5, 'FALSE', '2019-01-07 17:30:00', '2019-01-07 22:50:00');
COMMIT;

