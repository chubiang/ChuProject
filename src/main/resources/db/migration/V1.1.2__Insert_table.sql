INSERT INTO petkind (kindname)
VALUES ('pomeranian');

INSERT INTO person (username,personage,email,password,personimg,petfamilyno,etc,makedate,updatedate)
VALUES ('nanaMom', 25, 'nana@gmail.com', '1234', null, 1, 'hello', current_timestamp, current_timestamp);

INSERT INTO  pet (petfamilyno, personno, petname, kindno, favorites, petimg, birth, adotdate, petage, etc, makedate, updatedate)
VALUES (1,1, 'nana', 1, 'walking', null, '2012-04-21 00:00', '2012-06-11 00:00', 5, null, current_timestamp, current_timestamp);

INSERT INTO petfamily (petfamilyno, petno)
VALUES (1,1);
