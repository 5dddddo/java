// 문제 1
Select e.employee_id, e.first_name, e.department_id, m.first_name
from employees e, employees m
where e.manager_id =  m.employee_id ;

// 문제 2
Select e.last_name, e.salary
from employees e , employees m
where e.manager_id =  m.employee_id  and m.salary < e.salary; 

// 문제 3
Select first_name, last_name , salary
From employees e join jobs j 
on e.job_id = j.job_id
where job_title = 'Sales Representative' and
e.salary between 9000 and 10000; 

// 문제 4
Select e.employee_id,e.last_name,e.hire_date,m.hire_date
From employees e join employees m
On e.manager_id = m.employee_id
Where e.hire_date < m.hire_date;

// 문제 5
Select distinct job_title,department_name
From employees  e
Join jobs j
On j.job_id = e.job_id
join departments d
on e.department_id = d.department_id
order by job_title;

//문제 6
Select to_char(hire_date,'MM') 월, count(to_char(hire_date,'MM'))입사자수
from employees
group by to_char(hire_date,'MM')
order by 월;

//문제 7
select e.first_name, e.hire_date,m.employee_id,m.first_name
from employees e join employees m
on e.manager_id(+) = m.employee_id
where e.hire_date like '08%';

//문재 8
Select e.first_name,e.salary, d.department_name
From employees e join jobs j
On e.job_id = j.job_id
join departments d
on e.department_id = d.department_id
where job_title like 'Sales%';

//문제 9
select e.employee_id,e.first_name,e.last_name,
nvl(to_char(d.department_name),'<NOT ASSIGNED>')
from employees e join departments d
on e.department_id = d.department_id
where e.hire_date like '07%'
order by employee_id;

//문제 10
select e.first_name,e.hire_date,m.employee_id,m.first_name
from employees e join employees m
on e.manager_id(+) = m.employee_id
where e.hire_date like '05%'
order by employee_id;

