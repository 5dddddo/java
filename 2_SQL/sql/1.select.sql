select * from DEPT;
select empno,ename,jo,deptno from emp;
select ename as 사원명,job as 업무,sal as 급여,comm as 수당 from emp;
select ename as 사원명,job as 업무,sal as "급 여",comm as 수당 from emp;

select distinct job from emp;
select ename , sal,sal*12 from emp;
select ename , sal,comm,sal+comm from emp;
select ename , sal,comm,nvl(comm,0) from emp;

select ename , sal,comm, sal+nvl(comm,0) from emp;
select ename,nvl(mgr,'없음') from emp;  // error : nvl 매개변수 타입 일치해야 함
select ename,nvl(to_char(mgr),'없음') as 매니저 from emp; 
select ename , sal,'sql' from emp; // sql이 모든 row에 박힘

select ename , sal,comm, sal+nvl(comm,0) as 지급액 from emp;
select ename , sal,comm, sal+nvl(comm,0) as 지급액,'원'from emp;
select ename , sal,comm, sal+nvl(comm,0)||'원' as 지급액 from emp;  // || : 연결 연선자
select ename,job,hiredate, deptno from emp;
select ename||job||hiredate, deptno from emp;


select sysdate from emp;
select sysdate from edpt;
select sysdate from dual;
select sysdate,3+7+8, from dual; //table 두 개 만들기


select * from emp;
select * from emp where sal>3000;
select * from emp where job='MANAGER';
select * from emp where job='manager'; //error : 대소문자 구별 
select * from emp where job=upper('manager'); //job도 upper로
select * from emp where upper(job)=upper('manager'); //job도 upper로
select * from emp where upper(job)!=upper('manager'); //job도 upper로


select * from emp where sal >= 2000 and sal <=3000;
select * from emp where sal >=3000and sal <=5000;
select * from emp where sal between 3000 and 5000;
select * from emp where sal not between 3000 and 5000;
select * from emp where sal between 5000 and 3000;
//error : between a and b 연산시 a<=b 여야 수행됨
select * from emp where sal between 3000 and 3000;



select * from emp where deptno = 10;
select * from emp where deptno = 10 or deptno =20;
select * from emp where deptno in(10,20);
// in (a,b) :a거나 b일때
select * from emp where deptno != 10 or deptno !=20;
select * from emp where deptno != 10 and deptno !=20; //10번 부서도 아니고 20번 부서도 아닌 사람
select * from emp where deptno not in(10,20);
select * from emp where deptno <> all(10,20); //<>all 은  not in과 의미
select * from emp where deptno all(10,20);  // all 단독 사용 x
select * from emp where deptno <>(10,20); // <> 단독 사용 x


select * from dept;
select * from dept where deptno = 20 and loc = 'DALLAS';
select * from dept where deptno = 30 and loc = 'CHICAGO';

select * from dept where( deptno = 20 and loc = 'DALLAS')
or (deptno = 30 and loc = 'CHICAGO');
// 위아래 같은 명령문
select * from dept where(deptno,loc) in  ((20,'DALLAS'), (30 ,'CHICAGO'));
// deptno,loc 는 and 관계 in 뒤의 조건들은 or 관계


select * from emp where ename = 'KING';
select * from emp where job = 'S%'; //똑같은 문자열 찾음
select * from emp where job like 'S%'; //S로 시작하는 job 찾음 // % : 모든
select * from emp where ename like '%m%'; // 중간에 m이 들어간 이름 찾기
select * from emp where ename like '%M%'; // 중간에 M이 들어간 이름 찾기
select * from emp where upper(ename) like '%'||upper('m')||'%'; // m과 M 구분없이 이름 찾기


select * from emp where hiredate like '81%';
select * from emp where hiredate between '82/01/01' and  '82/12/31'; //82년도 입사자 전체

select * from emp where comm is null;
// null 인지 비교할 때 is 명령어 : comm이 null인 사람 찾아와 
select * from emp where comm is not null;
// null이 아닌 애들 찾응ㄹ 때 is not 명령어 : comm이 null이 아닌 사람 찾아와 

//정렬 : order by
 
select * from emp order by deptno asc; // 오름차순 정렬
select * from emp order by deptno desc; // 내림차순

select * from emp order by 1; // col번호를 기준으로 정렬
// 조건 같은 사람이 있을 때 처리하려면 ,로 2차 기준 추가하면 됨
select * from emp order by 5,deptno;


//where 절에는 alias된 이름(as로 정의한 이름) 못씀 (왜용 ????????????????????????????????)
-> 연산식 그대로 써야함 
select ename , sal,comm,nvl(comm,0) as total from emp;
select ename , sal,comm,sal + nvl(comm,0) as total from emp where total>3000; //error
select ename , sal,comm,sal + nvl(comm,0) as total from emp where sal + nvl(comm,0) >1000 ;
select ename , sal,comm,sal + nvl(comm,0) as total
from emp
where sal + nvl(comm,0) >1000
order by total; //  order by에서는 alias 사용 가능


select sysdate from dual;
select sysdate,to_char(sysdate,'MM') from dual; // 월만 출력
select sysdate,to_char(sysdate,'YYYY') from dual; 

select to_char(hiredate,'DAY') from emp; //요일 출력
select to_char(hiredate,'DD') from emp; //날짜 출력

INSERT INTO EMP VALUES
(7934,'MILLER','CLERK',7782,to_date('23-1-1982','dd-mm-yyyy'),1300,NULL,10);

insert into emp values(9999,'java01','CLERK',7782,sysdate,900,NULL,10);

insert into emp values(9999,'java01','CLERK',7782,sysdate,900,NULL,10);
//primary value인 사원번호가 중복되므로 error

select * from emp;
rollback; // java01 사원 등록 취소


insert into emp values(9999,'java01','CLERK',7782,sysdate,900,NULL,70);
// 부모에 없는 부서 번호 70 써서 error

insert into emp values(9999,'java01','CLERK',7782,'19/05/13',900,NULL,10);

insert into emp values(9999,'java01','CLERK',7782,'05/13/19',900,NULL,10);
//날짜 형식 안맞아서 안들어감 error


insert into emp values(9999,'java01','CLERK',7782,to_date('05/13/19','mm/dd/yy'),900,NULL,10);
//to_char 함수로 날짜 형식을 지정





