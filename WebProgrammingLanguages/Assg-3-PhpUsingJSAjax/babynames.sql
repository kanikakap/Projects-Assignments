CREATE DATABASE  IF NOT EXISTS 'WPL_Assg3';

USE 'WPL_Assg3';

CREATE TABLE `BabyNames` (
  'name' varchar(255) DEFAULT NULL,
  'year' int(4),
  'ranking' int(1),
  'gender' varchar(10),
  PRIMARY KEY ('name','year','ranking')
) 


INSERT INTO BabyNames
VALUES ('Jacob',2004,1,'m');

INSERT INTO BabyNames
VALUES ('Michael',2004,2,'m');

INSERT INTO BabyNames
VALUES ('Joshua',2004,3,'m');

INSERT INTO BabyNames
VALUES ('Matthew',2004,4,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2004,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2005,1,'m');

INSERT INTO BabyNames
VALUES ('Michael',2005,2,'m');

INSERT INTO BabyNames
VALUES ('Joshua',2005,3,'m');

INSERT INTO BabyNames
VALUES ('Matthew',2005,4,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2005,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2006,1,'m');

INSERT INTO BabyNames
VALUES ('Michael',2006,2,'m');

INSERT INTO BabyNames
VALUES ('Joshua',2006,3,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2006,4,'m');

INSERT INTO BabyNames
VALUES ('Matthew',2006,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2007,1,'m');

INSERT INTO BabyNames
VALUES ('Michael',2007,2,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2007,3,'m');

INSERT INTO BabyNames
VALUES ('Joshua',2007,4,'m');

INSERT INTO BabyNames
VALUES ('Daniel',2007,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2008,1,'m');

INSERT INTO BabyNames
VALUES ('Michael',2008,2,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2008,3,'m');

INSERT INTO BabyNames
VALUES ('Joshua',2008,4,'m');

INSERT INTO BabyNames
VALUES ('Daniel',2008,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2009,1,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2009,2,'m');

INSERT INTO BabyNames
VALUES ('Michael',2009,3,'m');

INSERT INTO BabyNames
VALUES ('Alexander',2009,4,'m');

INSERT INTO BabyNames
VALUES ('William',2009,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2010,1,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2010,2,'m');

INSERT INTO BabyNames
VALUES ('Michael',2010,3,'m');

INSERT INTO BabyNames
VALUES ('Jayden',2010,4,'m');

INSERT INTO BabyNames
VALUES ('William',2010,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2011,1,'m');

INSERT INTO BabyNames
VALUES ('Mason',2011,2,'m');

INSERT INTO BabyNames
VALUES ('William',2011,3,'m');

INSERT INTO BabyNames
VALUES ('Jayden',2011,4,'m');

INSERT INTO BabyNames
VALUES ('Noah',2011,5,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2012,1,'m');

INSERT INTO BabyNames
VALUES ('Mason',2012,2,'m');

INSERT INTO BabyNames
VALUES ('Ethan',2012,3,'m');

INSERT INTO BabyNames
VALUES ('Noah',2012,4,'m');

INSERT INTO BabyNames
VALUES ('William',2012,5,'m');

INSERT INTO BabyNames
VALUES ('Noah',2013,1,'m');

INSERT INTO BabyNames
VALUES ('Liam',2013,2,'m');

INSERT INTO BabyNames
VALUES ('Jacob',2013,3,'m');

INSERT INTO BabyNames
VALUES ('Mason',2013,4,'m');

INSERT INTO BabyNames
VALUES ('William',2013,5,'m');

INSERT INTO BabyNames
VALUES ('Emily',2004,1,'f');

INSERT INTO BabyNames
VALUES ('Emma',2004,2,'f');

INSERT INTO BabyNames
VALUES ('Madison',2004,3,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2004,4,'f');

INSERT INTO BabyNames
VALUES ('Hannah',2004,5,'f');

INSERT INTO BabyNames
VALUES ('Emily',2005,1,'f');

INSERT INTO BabyNames
VALUES ('Emma',2005,2,'f');

INSERT INTO BabyNames
VALUES ('Madison',2005,3,'f');

INSERT INTO BabyNames
VALUES ('Abigail',2005,4,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2005,5,'f');

INSERT INTO BabyNames
VALUES ('Emily',2006,1,'f');

INSERT INTO BabyNames
VALUES ('Emma',2006,2,'f');

INSERT INTO BabyNames
VALUES ('Madison',2006,3,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2006,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2006,5,'f');

INSERT INTO BabyNames
VALUES ('Emily',2007,1,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2007,2,'f');

INSERT INTO BabyNames
VALUES ('Emma',2007,3,'f');

INSERT INTO BabyNames
VALUES ('Ava',2007,4,'f');

INSERT INTO BabyNames
VALUES ('Madison',2007,5,'f');

INSERT INTO BabyNames
VALUES ('Emma',2008,1,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2008,2,'f');

INSERT INTO BabyNames
VALUES ('Emily',2008,3,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2008,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2008,5,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2009,1,'f');

INSERT INTO BabyNames
VALUES ('Emma',2009,2,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2009,3,'f');

INSERT INTO BabyNames
VALUES ('Sophia',2009,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2009,5,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2010,1,'f');

INSERT INTO BabyNames
VALUES ('Sophia',2010,2,'f');

INSERT INTO BabyNames
VALUES ('Emma',2010,3,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2010,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2010,5,'f');

INSERT INTO BabyNames
VALUES ('Sophia',2011,1,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2011,2,'f');

INSERT INTO BabyNames
VALUES ('Emma',2011,3,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2011,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2011,5,'f');

INSERT INTO BabyNames
VALUES ('Sophia',2012,1,'f');

INSERT INTO BabyNames
VALUES ('Emma',2012,2,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2012,3,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2012,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2012,5,'f');

INSERT INTO BabyNames
VALUES ('Sophia',2013,1,'f');

INSERT INTO BabyNames
VALUES ('Emma',2013,2,'f');

INSERT INTO BabyNames
VALUES ('Olivia',2013,3,'f');

INSERT INTO BabyNames
VALUES ('Isabella',2013,4,'f');

INSERT INTO BabyNames
VALUES ('Ava',2013,5,'f');