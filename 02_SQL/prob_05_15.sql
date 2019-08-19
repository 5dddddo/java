CREATE TABLE users (
    id varchar2(12) CONSTRAINT user_id_pk primary key,           
    password varchar(12) CONSTRAINT user_id_notnull NOT NULL,   
    name varchar(12),
    role varchar(12)
);

CREATE TABLE board(
    seq number(5),        
    title varchar(12),       
    content varchar(12),      
    regdate date, 
    cnt number(5),      
    id varchar(12) constraint board_id_pk PRIMARY KEY references users 
);


insert into users values()