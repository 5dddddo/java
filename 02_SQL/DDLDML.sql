SELECT * FROM TAB;

CREATE TABLE BOOK(
	BOOKNO NUMBER(5),
	TITLE VARCHAR2(12),
	AUTHOR VARCHAR2(12),
	PUBDATE DATE
);

DROP TABLE BOOK;
--AOUTO COMMIT 때문에 복구되지 않는다.
ROLLBACK;

CREATE TABLE BOOK(
	BOOKNO NUMBER(5),
	TITLE VARCHAR2(12),
	AUTHOR VARCHAR2(12),
	PUBDATE DATE DEFAULT SYSDATE
);

SELECT * FROM BOOK;

--COMMIT 을 하기 전까진 다른 유저에게 보이지 않는다.
--DML은 AUTO COMMIT 이 아니다.
--ECLIPSE는 AUTO COMMIT 이다.
INSERT INTO BOOK VALUES(1, '해리포터', 'IM', SYSDATE);
INSERT INTO BOOK VALUES(0002, 'JAVA', 'KIM', SYSDATE);
INSERT INTO BOOK VALUES(0003, 'SQL', 'PARK', SYSDATE);
INSERT INTO BOOK VALUES(0004, 'SQL', NULL, SYSDATE);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PUBDATE) 
	VALUES(0005, 'KINGOD', 'GODKING', '10/11/30');

INSERT INTO BOOK(BOOKNO, TITLE,  PUBDATE) 
	VALUES(0006, 'HTML',  '11/11/30');

DELETE FROM BOOK;

DESC BOOK;

--DDL은 AUTO COMMIT
ALTER TABLE BOOK ADD (PRICE NUMBER(7));

INSERT INTO BOOK VALUES(0007, 'SQLD', DEFAULT, SYSDATE, DEFAULT);

UPDATE BOOK SET PRICE = 0;

UPDATE BOOK SET PRICE = 10, TITLE = 'JSP' WHERE BOOKNO = 1;

UPDATE BOOK SET PRICE = NULL;

ALTER TABLE BOOK MODIFY(PRICE NUMBER(7, 2));

ALTER TABLE BOOK DROP COLUMN PRICE;

RENAME BOOK TO BOOK2;

RENAME BOOK2 TO BOOK;

--ROLLBACK 가능
DELETE FROM BOOK;

ROLLBACK;

TRUNCATE TABLE BOOK;

DROP TABLE BOOK;

SELECT * FROM EMP;
SELECT * FROM DEPT;

CREATE TABLE EMP2
	AS SELECT * FROM EMP;
	
SELECT * FROM EMP2;

CREATE TABLE EMP3
	AS SELECT * FROM EMP WHERE DEPTNO = 10;

SELECT * FROM EMP3;

CREATE TABLE DEPT2 
	AS SELECT * FROM DEPT;
	
SELECT * FROM DEPT2;

INSERT INTO DEPT2(DEPTNO, DNAME, LOC)
	VALUES (50, 'EDU', 'SEOUL');
--ERROR
INSERT INTO DEPT(DEPTNO, DNAME, LOC)
	VALUES (10, 'EDU', 'SEOUL');
--NOT ERROR
INSERT INTO DEPT2(DEPTNO, DNAME, LOC)
	VALUES (10, 'EDU', 'SEOUL');

CREATE TABLE BOOK(
	BOOKNO NUMBER(5) CONSTRAINT SCOTT_BOOK_PK PRIMARY KEY,
	TITLE VARCHAR2(12) CONSTRAINT BOOK_TITLE_UNIQUE UNIQUE,
	AUTHOR VARCHAR2(12),
	PRICE NUMBER(5) CONSTRAINT BOOK_PRICE_CHECK 
		CHECK(PRICE >= 0),
	PUBDATE DATE DEFAULT SYSDATE
);

SELECT * FROM BOOK;

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE) 
	VALUES(1, 'JAVA 1', 'KIM', 9000, SYSDATE);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE) 
	VALUES(1, 'JAVA 2', 'KIM', 9000, SYSDATE);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE) 
	VALUES(1, 'JAVA 2', 'KIM', 9000, SYSDATE);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE) 
	VALUES(2, NULL, 'KIM', 9000, SYSDATE);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE) 
	VALUES(3, 'HARRY POTER', 'KIM', 9000, DEFAULT);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE) 
	VALUES(4, 'LOAD', 'KIM', 9000, DEFAULT);

SELECT * FROM BOOK;

SELECT CONSTRAINT_NAME FROM USER_CONS_COLUMNS;

SELECT CONSTRAINT_NAME 
FROM USER_CONS_COLUMNS
WHERE TABLE_NAME = BOOK;

DROP TABLE BOOK;

SELECT CONSTRAINT_NAME FROM USER_CONS_COLUMNS;
--휴지통 비우기
PURGE RECYCLEBIN;

DROP TABLE BOOK CASCADE CONSTRAINT;

DROP TABLE BOOK;
CREATE TABLE BOOK(
	BOOKNO NUMBER(5) CONSTRAINT SCOTT_BOOK_PK PRIMARY KEY,
	TITLE VARCHAR2(12) CONSTRAINT BOOK_TITLE_UNIQUE UNIQUE,
	AUTHOR VARCHAR2(12),
	PRICE NUMBER(5) CONSTRAINT BOOK_PRICE_CHECK 
		CHECK(PRICE >= 0),
	PUBDATE DATE DEFAULT SYSDATE
);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE)
	VALUES(1, 'HARRY', 'POTER', 30000, DEFAULT);

INSERT INTO BOOK(BOOKNO, TITLE, AUTHOR, PRICE, PUBDATE)
	VALUES(2, 'C', 'DENNIS', 20000, '10/10/10');

SELECT * FROM BOOK;

DROP TABLE DEPT2;

CREATE TABLE DEPT2
	AS SELECT * FROM DEPT;

DROP TABLE EMP2;
	
CREATE TABLE EMP2
	AS SELECT * FROM EMP;
	
CREATE TABLE EMP1
	AS SELECT * FROM EMP;

ALTER TABLE DEPT2 ADD 
	CONSTRAINT DEPT2_DPTNO_PK PRIMARY KEY(DEPTNO);

ALTER TABLE EMP2 ADD 
	CONSTRAINT EMP2_EMPNO_PK PRIMARY KEY(EMPNO);

ALTER TABLE EMP1 ADD 
	CONSTRAINT EMP1_EMPNO_PK PRIMARY KEY(EMPNO);

ALTER TABLE EMP2 ADD 
	CONSTRAINT EMP2_FK FOREIGN KEY(MGR) REFERENCES EMP1;
	
ALTER TABLE EMP2 ADD 
CONSTRAINT EMP2_FK_DEPTNO FOREIGN KEY(DEPTNO) REFERENCES DEPT;

SELECT * FROM EMP2;

SELECT E.ENAME, E.SAL, S.GRADE
FROM EMP E JOIN SALGRADE S
ON E.SAL BETWEEN S.LOSAL AND S.HISAL;

CREATE OR REPLACE VIEW EMP_DETAIL
	AS SELECT E.ENAME, E.SAL, S.GRADE
		FROM EMP E JOIN SALGRADE S
		ON E.SAL BETWEEN S.LOSAL AND S.HISAL;

SELECT * FROM EMP_DETAIL;
--조인이 걸려있는 VIEW는 INSERT, DELETE 가 불가하다.
--VIEW는 보안 측면에서 유리

CREATE OR REPLACE VIEW KINGOD
 	AS  SELECT E.ENAME 사원이름, M.ENAME 상사이름
	FROM EMP E, EMP M
	WHERE E.MGR = M.EMPNO(+);
	
SELECT * FROM KINGOD;

DROP VIEW KINGOD;

SELECT * FROM EMP;

CREATE TABLE NEWEMP(
	EMPNO NUMBER(7),
	ENAME VARCHAR2(12),
	JOB VARCHAR2(12),
	MGR VARCHAR2(12),
	HIREDATE DATE,
	SAL NUMBER(8),
	COMM NUMBER(8),
	DEPTNO NUMBER(3)
);

INSERT INTO NEWEMP 
	SELECT * FROM EMP WHERE DEPTNO = 10;

SELECT * FROM NEWEMP;

UPDATE EMP SET SAL = SAL * 1.1 WHERE DEPTNO = 10;

SELECT * FROM EMP;

SELECT * FROM BOOK;

CREATE SEQUENCE BOOKNO;

ALTER TABLE BOOK ADD(PRICE NUMBER(7));

INSERT INTO BOOK(BOOKNO, TITLE, PRICE)
	VALUES(BOOKNO.NEXTVAL,  'KINGGOD', 7000);

	
INSERT INTO BOOK(BOOKNO, TITLE, PRICE)
	VALUES( (SELECT 
				NVL(MAX(BOOKNO), 0)+1 FROM BOOK
			),  
	'KINGGOD3', 7000);

SELECT * FROM BOOK;

SELECT * FROM BOOK WHERE BOOKNO = 3;

SELECT * FROM BOOK WHERE TITLE = 'C';

SET AUTOTRACE ON;

ALTER TABLE BOOK DROP CONSTRAINT BOOK_TITLE_UNIQUE;

CREATE INDEX BOOK_TITLE_IDX ON BOOK(TITLE);
























