/* person table */
CREATE TABLE PERSON (
    personNo SERIAL ,
    username VARCHAR(50) NOT NULL ,
    personAge SMALLSERIAL NOT NULL ,
    email VARCHAR(100) UNIQUE ,
    password  VARCHAR(100) NOT NULL ,
    personImg VARCHAR(500) ,
    petFamilyNo SERIAL ,
    etc  VARCHAR(300) ,
    makeDate TIMESTAMP ,
    updateDate TIMESTAMP ,
    PRIMARY KEY (personNo)
);

CREATE TABLE PET (
  petNo SERIAL ,
  petFamilyNo  SERIAL ,
  personNo SERIAL ,
  petName VARCHAR(50) ,
  kindNo SERIAL ,
  favorites VARCHAR(300) ,
  petImg VARCHAR(500) ,
  birth TIMESTAMP ,
  adotDate TIMESTAMP ,
  petAge SMALLSERIAL ,
  etc VARCHAR(300) ,
  makeDate TIMESTAMP ,
  updateDate TIMESTAMP ,
  PRIMARY KEY (petNo)
);

CREATE TABLE PETFAMILY (
  petFamilyNo SERIAL NOT NULL,
  petNo SERIAL UNIQUE NOT NULL
);

CREATE TABLE PETKIND (
  kindNo SERIAL ,
  kindName VARCHAR(100) ,
  PRIMARY KEY (kindNo)
);

ALTER  TABLE  PET
  ADD CONSTRAINT FK_PET_PERSON_NO FOREIGN KEY (personNo) REFERENCES PERSON(personNo);
ALTER  TABLE  PET
  ADD CONSTRAINT FK_PET_KIND_NO FOREIGN KEY (kindNo) REFERENCES PETKIND(kindNo);