insert into college values ('Stanford','CA',15000);
insert into college values ('Berkeley','CA',36000);
insert into college values ('MIT','MA',10000);
insert into college values ('Corneli','NY',21000);

select * from college;

create table STUDENT(
SID INT,
SNAME VARCHAR(255),
GPA FLOAT,
SIZEHS INT,
PRIMARY KEY (SID)
);

create table APPLY(
SID INT,
CNAME VARCHAR(255),
MAJOR VARCHAR(255),
DECISION CHAR(1)
);


insert into STUDENT values ('123','AMY',3.9,1000);
insert into STUDENT values ('234','BOB',3.6,1500);
insert into STUDENT values ('345','CRAIG',3.5,500);
insert into STUDENT values ('456','DORIS',3.9,1000);
insert into STUDENT values ('567','EDWARD',2.9,2000);
insert into STUDENT values ('678','FAY',3.2,200);
insert into STUDENT values ('789','GARY',3.4,800);
insert into STUDENT values ('987','HELEN',3.7,800);
insert into STUDENT values ('876','IRENE',3.9,400);
insert into STUDENT values ('765','JAY',2.9,1500);
insert into STUDENT values ('654','AMY',3.9,1000);
insert into STUDENT values ('543','CRAIG',3.4,2000);

SELECT * FROM STUDENT;
SELECT * FROM APPLY;
DELETE FROM APPLY;
DELETE FROM STUDENT;
DROP TABLE STUDENT;
DROP TABLE APPLY;

insert into APPLY values (123,'Stanford','CS','Y');
insert into APPLY values (123,'Stanford','EE','N');
insert into APPLY values (123,'Berkeley','CS','Y');
insert into APPLY values (123,'Corneli','EE','Y');
insert into APPLY values (234,'Berkeley','BIO','N');
insert into APPLY values (345,'MIT','BIO-ENG','Y');
insert into APPLY values (345,'Corneli','BIO-ENG','N');
insert into APPLY values (345,'Corneli','CS','Y');
insert into APPLY values (345,'Corneli','EE','N');
insert into APPLY values (678,'Stanford','HISTORY','Y');
insert into APPLY values (987,'Stanford','CS','Y');
insert into APPLY values (987,'Berkeley','CS','Y');
insert into APPLY values (876,'Stanford','CS','N');
insert into APPLY values (876,'MIT','BIO','Y');
insert into APPLY values (876,'MIT','BIO-ENG','N');
insert into APPLY values (765,'Stanford','HISTORY','Y');
insert into APPLY values (765,'Corneli','HISTORY','N');
insert into APPLY values (765,'Corneli','PHYSIO','Y');
insert into APPLY values (543,'MIT','CS','N');
insert into APPLY values (321,'MIT','HISTORY','Y');
insert into APPLY values (321,'MIT','PHYSIO','N');



