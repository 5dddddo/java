select * from tab;
drop table book;
create table book(
	bookno number(5),
	title varchar2(12),
	author varchar2(12),
	pubdate date
);

select * from book;
insert into book  values (1,'java','kim',sysdate);
insert into book  values (1,'java','kim',sysdate);
insert into book  values (2,null,null,'19/05/15');
insert into book (bookno,title,author,pubdate)
	values (3,'sql','ooo',null);
insert into book (bookno,title,author)
	values (4,'db','java01');
commit;
rollback;

delete from book
where title is null;
delete from book
where author='kim';

//col 추가 - DDL
alter table book add (price number(7));
insert into book values (5,default,default,default,default);

update book set price = 0;
update book set price = 10,title='jsp' where bookno=3;

//number(7,2) -> 전체 7자리 소숫점 2자리
//바꾸려는 변수값이 비어있어야 수정 가능
update book set price = null;
alter table book modify (price number(7,2));


//col 삭제
alter table book drop column price;

//table 이름 수정
rename book to book2;
rename book2 to book;

delete from book;
//DDL : Auto commit
truncate table book;
----------------------------------------------------

select * from emp;
select * from dept; 

create table emp3 
as select * from emp
where deptno = 10;


create table dept2 as select * from dept;

insert into dept values(50, 'IT','SEOUL');
insert into dept2 values(50, 'IT','SEOUL');

commit;

insert into dept values(10, 'IT','SEOUL'); // pk는 unique 해서 error
insert into dept2 values(10, 'IT','SEOUL'); 
//error 안남 -> 제약조건 빠져서 복사 됨

rollback;

select * from book;


create table book(
	bookno number(5) CONSTRAINT scott_book_pk PRIMARY KEY,
	title varchar2(12) constraint book_title unique,
	author varchar2(12) constraint author_notnull not null,
	price number(5) constraint book_price check(price>0),
	pubdate date default sysdate
);
commit;
rollback;


insert into book values (1,'java','a',10000,sysdate);
insert into book (bookno,title,author,price,pubdate)
values (2,'c','kim',2000,sysdate);
insert into book (bookno,title,author,price,pubdate)
values (3,null,'kim',2000,sysdate);
insert into book (bookno,title,author,price)
values (4,'java 4','kim1',20000);
insert into book (bookno,title,price)
values (5,'java 4',20000);


select CONSTRAINT_name from user_cons_columns;

select CONSTRAINT_name
from user_cons_columns
where table_name='BOOK';

drop table book;
// 휴지통 비우기
purge recyclebin;

//강제 삭제
drop table book cascade CONSTRAINT;

create table book(
	bookno number(5) PRIMARY KEY,
	title varchar2(12) unique,
	author varchar2(12),
	price number(5) check(price>0),
	pubdate date default sysdate
);

insert into book (bookno,title,price)
values (5,'java 4',5000);


create table book(
	bookno number(5),
	title varchar2(12) unique,
	author varchar2(12),
	price number(5) check(price>0),
	pubdate date default sysdate
);


alter table book 
add CONSTRAINT book_bookno_pk primary key(bookno);

drop table dept2;

create table dept2 as select * from dept;
// dept2 테이블에서 deptno 컬럼에 pk설정
alter table dept2 add constraint dept2_deptno_pk primary key(deptno);                        
alter table emp2 drop constraint book_bookno_pk;


create table emp2 as select * from emp;
// emp2 테이블에서 empno 컬럼에 pk설정
alter table emp2 add constraint emp2_empno_pk primary key(empno);

//emp2 테이블에 mgr 컬럼에 fk설정

alter table emp2 add foreign key(deptno) references dept2;

------------------------------------
//VIEW : 가상 테이블 - join이 걸려 있는 view는 R/O
// 보안 강화 

create or replace view emp_detail
as select ename,dname,sal,grade
from emp e, dept d,salgrade s
where  e.deptno = d.deptno
and sal between losal and hisal;

select * from emp_detail;

create or replace view dept_name
as select dname, ename
from dept d, emp e
where d.deptno = e.deptno;

drop view emp_detail;
drop view dept_name;

insert into emp3 
select * from emp 
where deptno = 30;

update emp set sal = sal * 1.1 where deptno =10;


drop table book;
create table book(
	bookno number(5),
	title varchar2(12),
	author varchar2(12),
	price number(5) check(price>0),
	pubdate date default sysdate
);

alter table book 
add CONSTRAINT book_bookno_pk primary key(bookno);
alter table book drop constraint book_bookno_pk;


// sequence 번호 자동 생성
// .nextvar / .currval
create sequence bookno;

insert into book (bookno,title,price)
values (bookno.nextval,'java 4',5000);

insert into book (bookno,title,price)
values (bookno.nextval,'c',5000);

select bookno.currval from dual;

drop sequence bookno;

insert into book (bookno,title,price)
values ( (select nvl(max(bookno),0) + 1 from book)
,'c',5000);


select nvl(max(bookno),0) + 1 from book;


set autotrace on;

//title을 기준으로 index 생성 
-> index 생성하면 search 등에서 성능 향상




create index book_title on book(title);
drop index book_title;




create table book(
	bookno number(5),
	title varchar2(12),
	author varchar2(12),
	price number(5) check(price>0),
	pubdate date default sysdate
);

