package edu02.oop.domain;

/**
 * <pre>
 * TODO : Employee 도메인 클래스에 대한 테스트 클래스
 * - 직원 정보는 제공한 엑셀정보를 참고하세요.
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
		
		System.out.println(">>> 직원 정보 (현재날짜: " + Utility.getCurrentDateToString() + ") <<<");
		for (Employee emp : emps) {
			System.out.println(emp);
		}
	}
	
	public static void main2(String[] args) {
		Employee emp1 = new Employee();
		emp1.empNo = 7369;
		emp1.empName = "SMITH";
		emp1.job = "CLERK";
		emp1.mgrNo = 7902;
		emp1.hireDate = Utility.toDate("1980-12-17");
		emp1.salary = 800;
		emp1.deptNo = 20;
		
		Employee emp2 = new Employee();
		emp2.empNo = 7499;
		emp2.empName = "ALLEN";
		emp2.job = "SALESMAN";
		emp2.mgrNo = 7698;
		emp2.hireDate = Utility.toDate("1981-02-20");
		emp2.salary = 1600;
		emp2.commission = 300;
		emp2.deptNo = 30;
		
		Employee emp3 = new Employee();
		emp3.empNo = 7521;
		emp3.empName = "WARD";
		emp3.job = "SALESMAN";
		emp3.mgrNo = 7698;
		emp3.hireDate = Utility.toDate("1981-02-22");
		emp3.salary = 1250;
		emp3.commission = 500;
		emp3.deptNo = 30;
		
		Employee emp4 = new Employee();
		emp4.empNo = 7844;
		emp4.empName = "TURNER";
		emp4.job = "SALESMAN";
		emp4.mgrNo = 7698;
		emp4.hireDate = Utility.toDate("1981-09-08");
		emp4.salary = 1500;
		emp4.commission = 0;
		emp4.deptNo = 30;
		
		System.out.println(">>> 직원 정보 (현재날짜: " + Utility.getCurrentDateToString() + ") <<<");
		System.out.println(emp1);
		System.out.println(emp2);
		System.out.println(emp3);
		System.out.println(emp4);
	}

}
