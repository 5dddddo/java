package edu03.oop.encapsulation.dto;

import java.util.ArrayList;

/**
 * <pre>
 * TODO : Employee 도메인 클래스에 대한 테스트 클래스
 * - 직원 정보는 제공한 엑셀정보를 참고하세요.
 * - 제공된 Utility 클래스의 메서드를 활용하세요.
 * 
 * EMPNO	EMPNAME	JOB	   		MGRNO	HIREDATE	SALARY	COMMISSION	DEPTNO
 * 7369		SMITH	CLERK		7902	1980-12-17	800					20
 * 7499		ALLEN	SALESMAN	7698	1981-02-20	1600	300			30
 * 7521		WARD	SALESMAN	7698	1981-02-22	1250	500			30
 * 7844		TURNER	SALESMAN	7698	1981-09-08	1500	0			30
 * </pre>
 * @author Administrator
 *
 */
public class EmployeeTest {

	public static void main(String[] args) {
		Employee[] emps = {
			new Employee(7369,"SMITH","CLERK", 7902, Utility.toDate("1980-12-17"), 800, 20),
			new Employee(7499, "ALLEN", "SALESMAN", 7698, Utility.toDate("1981-02-20"), 1600, 300, 30),
			new Employee(7521, "WARD", "SALESMAN", 7698, Utility.toDate("1981-02-22"), 1250  , 500, 30),
			new Employee(7844, "TURNER" , "SALESMAN", 7698, Utility.toDate("1981-09-08"), 1500, 0, 30),
		};
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		for (Employee emp : emps) {
			employees.add(emp);
		}
		
		System.out.println(">>> 직원 정보 (현재날짜: " + Utility.getCurrentDateToString() + ") <<<");
		for (Employee emp : employees) {
			System.out.println(emp);
		}
	}

}
