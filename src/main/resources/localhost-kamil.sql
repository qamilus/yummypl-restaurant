--create table RESTAURANTS(
--id number,
--restaurantName VARCHAR2(100 CHAR),
--menu VARCHAR2(100 CHAR),
--location VARCHAR2(100 CHAR),
--information VARCHAR2(100 CHAR)
--);

CREATE TABLE RESTAURANTS
(
    ID          NUMBER NOT NULL,
    NAME        VARCHAR2(100 CHAR),
    MENU        VARCHAR2(100 CHAR),
    LOCATION    VARCHAR2(100 CHAR),
    INFORMATION VARCHAR2(100 CHAR),
    CONSTRAINT RESTAURANTS_ID_PK PRIMARY KEY (ID)
);
CREATE TABLE RESTAURANT_INFORMATION
(
    ID            NUMBER NOT NULL,
    OPENING_HOURS VARCHAR(255),
    RESTAURANT_ID NUMBER,
     CONSTRAINT RESTAURANT_INFORMATION_ID_PK PRIMARY KEY (ID),
     CONSTRAINT RESTAURANT_ID_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANTS(ID)
);

select * 
from restaurants;

select * 
from restaurant_information;

INSERT INTO RESTAURANTS(ID,NAME,MENU,LOCATION,INFORMATION)
VALUES (1,'Trattoria Rucola','Bogate',
'Marszakowska 22, Warszawa','Szeroki wybór dań');

INSERT INTO RESTAURANTS(ID,NAME,MENU,LOCATION,INFORMATION)
VALUES (2,'U Gruzina','Bogate',
'Marszakowska 23, Warszawa','Szeroki wybór przystawek');

INSERT INTO RESTAURANTS(ID,NAME,MENU,LOCATION,INFORMATION)
VALUES (3,'Nam Ninh','Bogate',
'Marszakowska 24, Warszawa','Napój gratis!');

INSERT INTO restaurant_information(ID,opening_hours,restaurant_id)
VALUES (1, '11-22', 1);

INSERT INTO restaurant_information(ID,opening_hours,restaurant_id)
VALUES (2, '11-22', 4);

--update RESTAURANTS
--set id = 3
--where restaurantName = 'Nam Ninh';

SELECT 
--*
R.ID AS R_ID, R.NAME, RI.ID AS RI_ID, RI.OPENING_HOURS--, 'kolumna'
--R.ID, R.NAME, RI.ID, RI.OPENING_HOURS--, 'kolumna'
FROM RESTAURANTS R
--LEFT 
--FULL OUTER
JOIN RESTAURANT_INFORMATION RI
ON R.ID = RI.RESTAURANT_ID;

--SELECT 
--NAME
----R.NAME, RI.OPENING_HOURS
--FROM RESTAURANTS
--JOIN RESTAURANT_INFORMATION
--ON ID = RESTAURANT_ID;

CREATE TABLE ORDERS
(
    ID              NUMBER NOT NULL,
    ORDER_STATUS_ID NUMBER,
    LOCATION_ID     NUMBER,
    CONSTRAINT ORDERS_ID_PK PRIMARY KEY (ID),
    CONSTRAINT LOCATIONS_ID_FK FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS (ID)
);

CREATE TABLE ORDER_STATUS
(
    ID   NUMBER NOT NULL,
    NAME VARCHAR2(50),
    CONSTRAINT ORDER_STATUS_ID_PK PRIMARY KEY (ID)
);

CREATE TABLE MENU
(
    ID   NUMBER NOT NULL,
    NAME VARCHAR2(100),
    RESTAURANT_ID NUMBER,
    CONSTRAINT MENU_ID_PK PRIMARY KEY (ID),
    CONSTRAINT RESTAURANTS_ID_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANTS(ID)
);

CREATE TABLE MENU_ITEMS
(
    ID NUMBER NOT NULL ,
    NAME VARCHAR2(100) ,
    PRICE DECIMAL(6,2) ,
    QUANTITY NUMBER,
    MENU_ID NUMBER,
    CONSTRAINT MENU_ITEMS_ID_PK PRIMARY KEY (ID) ,
    CONSTRAINT MENU_ID_FK FOREIGN KEY (MENU_ID) REFERENCES MENU(ID)
);


-----------------------------------------------------------------------------------------

SELECT *
FROM RESTAURANTS;

SELECT *
FROM RESTAURANTS_INFORMATION;

CREATE SEQUENCE LOCATIONS_TYPE_ID_SEQ;

INSERT INTO LOCATIONS_TYPE(ID, NAME) VALUES (LOCATIONS_TYPE_ID_SEQ.NEXTVAL, 'RESTAURANT');

INSERT INTO LOCATIONS_TYPE(ID, NAME) VALUES (LOCATIONS_TYPE_ID_SEQ.NEXTVAL, 'ORDER');

SELECT * FROM LOCATIONS_TYPE;

CREATE SEQUENCE LOCATIONS_ID_SEQ;

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Warszawa', 'Marszalkowska', '22b', 1);

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Warszawa', 'Mokotowska', '23b', 1);

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Warszawa', 'Piękna', '21b', 1);

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Poznań', 'Sosnkowskiego', '141', 2);

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Toruń', 'Piernikowa', '33', 1);

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Gdańsk', 'Nadmorska', '4b', 2);

INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Orzysz', 'Górska', '15b', 1);

UPDATE LOCATIONS
SET LOCATION_TYPE_ID = 1
WHERE ID IN(22,23);  --WHERE ID = 22 AND ID = 23;

SELECT * FROM LOCATIONS;

INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'Trattoria Rucola', 1);

INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'U Gruzina', 2);

INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'Roma', 3);

INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'Pizza Hut', 21);

INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'Fishlandia', 22);

INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'Koziolek', 23);

SELECT * FROM RESTAURANTS;

CREATE SEQUENCE RESTAURANTS_ID_SEQ;

CREATE SEQUENCE RESTAURANTS_INFORMATION_ID_SEQ;

INSERT INTO RESTAURANTS_INFORMATION(ID, OPENING_HOURS, RESTAURANT_ID) VALUES (RESTAURANTS_INFORMATION_ID_SEQ.nextval, '11-22', 3);

INSERT INTO RESTAURANTS_INFORMATION(ID, OPENING_HOURS, RESTAURANT_ID) VALUES (RESTAURANTS_INFORMATION_ID_SEQ.nextval, '12-23', 9);
INSERT INTO RESTAURANTS_INFORMATION(ID, OPENING_HOURS, RESTAURANT_ID) VALUES (RESTAURANTS_INFORMATION_ID_SEQ.nextval, '11-24', 10);

CREATE SEQUENCE ORDERS_ID_SEQ;
INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Kraków', 'Malopolska', '125b', 2);
INSERT INTO LOCATIONS(ID, CITY, STREET, HOUSE_NUMBER, LOCATION_TYPE_ID) VALUES (LOCATIONS_ID_SEQ.NEXTVAL, 'Kraków', 'Podwale', '14b', 1);
INSERT INTO RESTAURANTS(ID, NAME, LOCATION_ID) VALUES (RESTAURANTS_ID_SEQ.NEXTVAL, 'Smocza', 27);
SELECT * FROM orders_status;
CREATE SEQUENCE ORDERS_STATUS_ID_SEQ;
INSERT INTO ORDERS_STATUS(ID, NAME) VALUES (ORDERS_STATUS_ID_SEQ.NEXTVAL, 'New');
INSERT INTO ORDERS_STATUS(ID, NAME) VALUES (ORDERS_STATUS_ID_SEQ.NEXTVAL, 'In progress');
INSERT INTO ORDERS_STATUS(ID, NAME) VALUES (ORDERS_STATUS_ID_SEQ.NEXTVAL, 'Done');
INSERT INTO ORDERS_STATUS(ID, NAME) VALUES (ORDERS_STATUS_ID_SEQ.NEXTVAL, 'Canceled');
INSERT INTO ORDERS(id, location_id, order_status_id, restaurant_id) VALUES (ORDERS_ID_SEQ.NEXTVAL, 27, 1, 24);
SELECT * FROM ORDERS;
SELECT * FROM orders_items;

CREATE SEQUENCE ORDERS_ITEMS_ID_SEQ;
INSERT INTO ORDERS_ITEMS(ID, NAME, PRICE, QUANTITY, ORDER_ID) VALUES (ORDERS_ITEMS_ID_SEQ.nextval, 'Rumsztyk Staropolski', 26.99, 1, 1);
INSERT INTO ORDERS_ITEMS(ID, NAME, PRICE, QUANTITY, ORDER_ID) VALUES (ORDERS_ITEMS_ID_SEQ.nextval, 'Spaghetti', 22.99, 1, 1);
INSERT INTO ORDERS_ITEMS(ID, NAME, PRICE, QUANTITY, ORDER_ID) VALUES (ORDERS_ITEMS_ID_SEQ.nextval, 'Cola Zero', 6.99, 2, 1);

SELECT *
FROM MENU;

CREATE SEQUENCE MENU_ID_SEQ;

INSERT INTO MENU(ID, NAME, RESTAURANT_ID) VALUES (MENU_ID_SEQ.nextval, 'Karta Dań', 24);
INSERT INTO MENU(ID, NAME, RESTAURANT_ID) VALUES (MENU_ID_SEQ.nextval, 'Karta Win', 24);

select *
from MENU_ITEMS;

CREATE SEQUENCE MENU_ITEMS_ID_SEQ;

INSERT INTO MENU_ITEMS(ID, NAME, PRICE, QUANTITY, MENU_ID) VALUES (MENU_ITEMS_ID_SEQ.nextval, 'Rumsztyk Staropolski', 26.99, '200g', 1);
INSERT INTO MENU_ITEMS(ID, NAME, PRICE, QUANTITY, MENU_ID) VALUES (MENU_ITEMS_ID_SEQ.nextval, 'Spaghetti', 22.99, '300g', 1);
INSERT INTO MENU_ITEMS(ID, NAME, PRICE, QUANTITY, MENU_ID) VALUES (MENU_ITEMS_ID_SEQ.nextval, 'Cola Zero', 6.99, '330ml', 1);
INSERT INTO MENU_ITEMS(ID, NAME, PRICE, QUANTITY, MENU_ID) VALUES (MENU_ITEMS_ID_SEQ.nextval, 'Sałatka Grecka', 16.99, '300g', 1);
INSERT INTO MENU_ITEMS(ID, NAME, PRICE, QUANTITY, MENU_ID) VALUES (MENU_ITEMS_ID_SEQ.nextval, 'Pizza Wawelska', 46.99, '700g', 1);

















SELECT R.NAME, L.CITY, L.STREET, L.HOUSE_NUMBER, LT.NAME AS LOCATION_TYPE
FROM RESTAURANTS R
JOIN LOCATIONS L
    ON R.LOCATION_ID = L.ID
JOIN LOCATIONS_TYPE LT
    ON L.LOCATION_TYPE_ID = LT.ID;
    
      SELECT R.ID AS R_ID, R.NAME, L.ID AS L_ID, L.CITY, L.STREET, L.HOUSE_NUMBER, LT.NAME AS LOCATION_TYPE, RI.ID AS RI_ID, RI.OPENING_HOURS  
        FROM RESTAURANTS R  
        LEFT JOIN RESTAURANTS_INFORMATION RI  
            ON R.ID = RI.RESTAURANT_ID  
        LEFT JOIN LOCATIONS L  
            ON R.LOCATION_ID = L.ID  
        LEFT JOIN LOCATIONS_TYPE LT  
            ON L.LOCATION_TYPE_ID = LT.ID
        WHERE LT.ID = 1
        ORDER BY R_ID ASC;
        
SELECT O.ID AS O_ID, R.ID AS R_ID, R.NAME, OI.QUANTITY, OS.NAME AS ORDERS_STATUS,L.ID AS L_ID, L.CITY, L.STREET, L.HOUSE_NUMBER
FROM ORDERS O
LEFT JOIN RESTAURANTS R
    ON o.restaurant_id = r.ID
LEFT JOIN locations L
    ON o.location_id = l.id
LEFT JOIN locations_type LT
    ON l.location_type_id = lt.id
LEFT JOIN ORDERS_STATUS OS
    ON o.order_status_id = os.id
LEFT JOIN ORDERS_ITEMS OI
    ON oi.ORDER_ID = o.ID
WHERE LT.ID = 2
;

SELECT *
FROM ORDERS O
LEFT JOIN ORDERS_ITEMS OI
    ON O.ID = OI.ORDER_ID;

SELECT *
FROM ORDERS_ITEMS OI
LEFT JOIN ORDERS O
    ON OI.ORDER_ID = O.ID;

SELECT *
FROM MENU M
LEFT JOIN MENU_ITEMS MI
    ON M.ID = MI.MENU_ID;

SELECT *
FROM MENU_ITEMS MI
LEFT JOIN MENU M
    ON MI.MENU_ID = M.ID;