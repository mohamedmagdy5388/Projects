CREATE TABLE IF NOT EXISTS CAR (
	ID INT NOT NULL, 
    BRAND VARCHAR(50) NOT NULL, 
    MODEL VARCHAR(50) NOT NULL, 
    PRODUCTION_YEAR INT, 
    PRIMARY KEY (ID)
);

DELETE FROM CAR;

insert into CAR
values(1,'Toyota', 'Corolla',2010);

insert into CAR
values(2,'Mercedes-Benz', 'Aclass',2012);

insert into CAR
values(3,'BMW', '7-Series',2016);