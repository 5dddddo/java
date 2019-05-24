select
from
where
order by

/*Single row method*/
select * from emp;
// to_char 와 substr로 yy 추출
SELECT ename,hiredate,to_char(hiredate,'YY') from emp;
SELECT ename,hiredate, substr(hiredate,4,5) from emp;

//round 반올림 trunc 버림
select round(55.55),trunc(55.4650) from dual ;

select * from emp order by deptno;
select ename,deptno,sal,sal*1.1 from emp;

select ename,deptno,sal,sal*1.1
from emp WHERE deptno =10 ;
select ename,deptno,sal,sal*1.2
from emp WHERE deptno =20 ;
select ename,deptno,sal,sal*1.3
from emp WHERE deptno =30 ;

//Decode 함수로 if처럼 여러 가지 조건 처리
select ename,deptno,sal,decode(deptno,10,sal*1.1,
20,sal*1.2,30,0) as total
from emp order by deptno;


//null 처리
select ename,mgr from emp;
select ename,nvl(mgr,0) from emp;
select ename,nvl(mgr,'X') from emp; //ERROR -> 자동 형변환 X , to_char 필요
select ename,nvl(to_char(mgr),'X') from emp;
//mgr이 null 이면 O, 아니면 X -> 자동 형변환 o
select ename,nvl2(mgr,'O','X') from emp;


select * from emp;
// 모든 경우의 수 emp * dept
select * from emp ,dept;

//부서가 같은 경우 찾기
select * from emp ,dept
where emp.deptno = dept.deptno;

// join
// emp.deptno는 emp의 primary key로 foreign key인 dept.deptno와 엮어줌
select ename,job, dname, loc from emp ,dept
where emp.deptno = dept.deptno;

//deptno는 두 table에 다 있기 때문에 헷갈림 - ERROR
select ename,job, dname,deptno, loc from emp ,dept
where emp.deptno = dept.deptno;

select ename,job, dname,emp.deptno, loc from emp ,dept
where emp.deptno = dept.deptno;

select ename,job, dname,e.deptno, loc 
from emp e ,dept d
where e.deptno = d.deptno;

//inner join,안시 join 표준
	, -> join
	where -> on 으로 바꿈
		   
select ename,job, dname,e.deptno, loc 
from emp e join dept d
on e.deptno = d.deptno;
----------------------------
//안시 표준
select ename,job,dname
from emp e 
inner join dept d
on e.deptno = d.deptno
where sal>=2000 ;

//원래 썼던 문법 where 조건을 and로 연결
select ename,job, dname,sal
from emp e ,dept d
where e.deptno = d.deptno and sal>=2000 ;
-------------------------------------------------
// outer join
select ename,job, dname,sal
from emp e ,dept d
where e.deptno(+) = d.deptno (추가 조건 필요시 and ----);

//안시 join
select ename,job, dname,sal
from emp e right outer join dept d
on e.deptno = d.deptno ;
(추가 조건 필요시 where) ---
------------------------------------------------

select * FROM SALGRADE;

select ename,sal,grade
from emp,SALGRADE
where sal between losal and hisal;
//안시조인
select ename,sal,grade
from emp join SALGRADE
on sal between losal and hisal;

//self join -> 13명 중 12명만 나옴 (outer join)
select e.ename 사원이름, m.ename 상사이름
from emp e,emp m 
where m.empno = e.mgr;


//왼쪽을 전체 찍고 싶을 때
select e.ename 사원이름, m.ename 상사이름
from emp e,emp m 
where m.empno(+) = e.mgr;

select e.ename 사원이름,nvl(to_char(m.ename),'CEO') 상사이름
from emp e left join emp m 
on m.empno = e.mgr;

-----------------------------------
// 입사일자가 상사보다 빠른 사원 찾기
select e.ename 사원이름,e.hiredate 사원 ,m.hiredate 상사
from emp e left join emp m 
on m.empno = e.mgr
where e.hiredate>m.hiredate;

--------------------------------
// 급여가 상사보다 많은 사원
//ansi join
select e.ename 사원이름,e.sal 사원 ,m.sal 상사
from emp e left join emp m 
on m.empno = e.mgr
where e.sal<m.sal;

//oracle join
select e.ename 사원이름,e.sal 사원 ,m.sal 상사
from emp e , emp m 
where m.empno = e.mgr and e.sal<m.sal;

// singlerow method : 반올림
select round(sal) from emp;
//집계함수 - 평균 : 
select avg(sal) from emp;


select ename,round(sal) from emp;

select ename,avg(sal) from emp; // error
select count(sal),count(comm),count(*),count(mgr) from emp;

select count(job) from emp;
select count(distinct job) from emp;

select sum(sal),count(*),round(sum(sal)/count(*),2) 평균, round(avg(sal),3) from emp;

----------------------------------------

select sum(sal),count(*),round(sum(sal)/count(*),2) 평균,
round(avg(sal),3) from emp
where deptno =10;

select sum(sal),count(*),round(sum(sal)/count(*),2) 평균,
round(avg(sal),3) from emp
where deptno =20;

select sum(sal),count(*),round(sum(sal)/count(*),2) 평균,
round(avg(sal),3) from emp
where deptno =30;


select max(sal),min(sal),count(*),round(sum(sal)/count(*),2) 평균
from emp
group by deptno;

select e.deptno, dname,avg(sal)
from emp e, dept d
group by e.deptno,dname
order by e.deptno;


//사원들의 평균 급여
select e.deptno, dname,avg(sal) 평균
from emp e, dept d
where e.deptno= d.deptno
group by e.deptno,dname
order by 평균;

//급여(sal)가 2000이상인 사원들의 평균 급여
select e.deptno, dname,count(*),avg(sal) 평균
from emp e, dept d
where e.deptno= d.deptno and sal>2000
group by e.deptno,dname
order by 평균;


//평균 급여(avg)가 2000이상인 부서정보 출력
//group func avg는 where 절에 쓸 수 없음 -> having절에 써야함

select e.deptno, dname,count(*),avg(sal) 평균
from emp e, dept d
where e.deptno= d.deptno 
group by e.deptno,dname
having avg(sal) >=2000
order by 평균;

select e.deptno, dname,count(*),avg(sal) 평균
from emp e, dept d
where e.deptno= d.deptno 
having avg(sal) >=2000
group by e.deptno,dname
order by 평균;
-----------------------------------------------
// 3가지 table join p.265
//oracle join
select ename,dname,sal,grade
from emp e, dept d,salgrade s
where  e.deptno = d.deptno
and sal between losal and hisal;

//ansi join
select ename,dname,sal,grade
from emp e 
join dept d 
on e.deptno = d.deptno
join salgrade
on sal between losal and hisal;


//p.168 서브쿼리

select sal from emp where ename = 'FORD';
select * from emp where sal>3000;
//서브쿼리로 한번에 처뤼
select * from emp 
where sal>(select sal from emp where ename = 'FORD');

//평균 급여보다 적게 받는 사람들
select ename,sal from emp 
where sal<(select round(avg(sal)) from emp );

//급여 꼴찌
select ename,sal from emp 
where sal = (select min(sal) from emp) ;
-------------------------------------------------
select min(sal) from emp group by deptno;

select ename,sal from emp 
where sal = (select min(sal) from emp group by deptno);
// groub by 를 한 sub query의 반환값이 3개여서 sal과 비교할 수 없음 - error
// = 을 in 으로 바꾸기!
// in 는 or 처럼 여러 개 처리
select ename,sal from emp 
where sal in (select min(sal) from emp group by deptno);

--------------------------------------------------

자신이 속한 부서의 평균보다 급여가 적은 사람 리스트
//서브쿼리 내에서 쓰기 위해 alias 해서 사용
select *
from emp outer
where sal < (select avg(sal) from emp where deptno = outer.deptno);

----------------------------------------------------------
//행 넘버
select rownum ,ename,job,sal
from emp;

//행 넘버가 붙은 후 소트되어 행 넘버도 소트됨
select rownum ,ename,job,sal
from emp
order by sal;

//top 3 구하기
select rownum ,ename,job,sal
from (select * from emp order by sal desc)
where rownum<4;


/// 외않되??????????????????????????????????????????????????????????????
select rownum ,ename,job,sal
from (select * from emp order by sal)
where rownum > 4;

// 각 부서별 최고 급여 받는 사람 리스트
select ename,sal,deptno
from emp outer
where sal in (select max(sal) from emp where deptno = outer.deptno)
order by sal desc;

// 정확한 데이터 추출 어려움, 조건 2개 필요 - 문제!!!!
select ename,sal,deptno
from emp outer
where sal in (select max(sal) from emp group by deptno)
order by sal desc;

//각 부서별 최고 급여 받는 사람 리스트 추출
select * from emp where (deptno,sal) 
in (select deptno, max(sal) from emp group by deptno);


select deptno, max(sal) from emp group by deptno;



---------------------------------------------------------------------
//sub quary
//정렬된 후 테이블에 행 번호 달기
select rownum,ename,job,sal
from (select * from emp order by sal desc);

//top3
select rownum,ename,job,sal
from (select * from emp order by sal desc)
where rownum < 4;

//rownum이 1부터 차례대로 생성되기 때문에
//rownum > 4의 조건을 만족할 수 없어서 결과 안 나옴
select rownum,ename,job,sal
from (select * from emp order by sal desc)
where rownum between 1 and 3;

//mySQL의 limit 함수 기능
//rownum 중간부터 가져오기
select row# 행,ename,job,sal
from 
(
	select rownum row#,ename,job,sal
	from (select * from emp order by sal desc)
) where row# between 4 and 7;



//81년도에 입사한 사람 중 월급 상위 3명
select ename, hiredate
from
(
	select rownum row#,ename, hiredate
	from (
		select * from emp order by sal desc)
) where hiredate like '81%' and row# between 1 and 3;








