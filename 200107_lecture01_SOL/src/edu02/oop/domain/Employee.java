package edu02.oop.domain;

import java.util.Date;

/**
 * <pre>
 * # TODO : 직원 정보 모델링 클래스 
 * - 제공된 Class Diagram을 참고하세요.
 * 
 * # 직원 정보
 * 	1. 사번 : 정수
 * 	2. 이름 : 문자열
 * 	3. 직무 : 문자열
 * 	4. 상사 사번 : 정수
 * 	5. 입사일 : 날짜
 * 	6. 급여 : 실수
 * 	7. 수당 : 실수
 * 	8. 부서번호 : 정수
 * 
 * # Abstraction
 * - UML
 * - Class Structure
 * - Variable
 * - Method
 * - Constructor
 * - new instance
 * - Array
 * - Collection API
 *   
 * </pre>
 * @author Administrator
 *
 */
public class Employee {
	/** 1. 사번 : 정수 */
	public int empNo;
	
	/** 2. 이름 : 문자열 */
	public String empName;
	
	/** 3. 직무 : 문자열 */
	public String job;
	
	/** 4. 상사 사번 : 정수 */
	public int mgrNo;
	
	/** 5. 입사일 : 날짜 */
	public Date hireDate;
	
	/** 6. 급여 : 실수 */
	public float salary;
	
	/** 7. 수당 : 실수 */
	public float commission;
	
	/** 8. 부서번호 : 정수 */
	public int deptNo;
	
	/** 기본 생성자 */
	public Employee() {}
	
	/** 
	 * <pre> 
	 * 필수 데이터 초기화 생성자
	 * </pre>
	 * 
	 * @param empNo 사번
	 * @param empName 이름
	 * @param job 직무
	 * @param mgrNo 상사사번
	 * @param hireDate 입사일
	 * @param salary 급여
	 * @param deptNo 부서번호
	 */
	public Employee(int empNo, String empName, String job, int mgrNo, Date hireDate, float salary, int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgrNo = mgrNo;
		this.hireDate = hireDate;
		this.salary = salary;
		this.deptNo = deptNo;		
	}

	/** 
	 * <pre>
	 * 전체 데이터 초기화 생성자
	 * </pre>
	 * 
	 * @param empNo 사번
	 * @param empName 이름
	 * @param job 직무
	 * @param mgrNo 상사사번
	 * @param hireDate 입사일
	 * @param salary 급여
	 * @param commission 수당
	 * @param deptNo 부서번호
	 */
	public Employee(int empNo, String empName, String job, int mgrNo, Date hireDate, float salary, float commission,
			int deptNo) {
		this(empNo, empName, job, mgrNo, hireDate, salary, deptNo);
		this.commission = commission;
	}
	
	@Override
	public String toString() {
		return empNo + ", " + empName + ", " + job + ", " + mgrNo + ", " + Utility.getDateToString(hireDate) + ", " + salary + ", " + commission
				+ ", " + deptNo;
	}

//	@Override
//	public String toString2() {
//		return "Employee [empNo=" + empNo + ", empName=" + empName + ", job=" + job + ", mgrNo=" + mgrNo + ", hireDate="
//				+ Utility.getDateToString(hireDate) + ", salary=" + salary + ", commission=" + commission + ", deptNo=" + deptNo + "]";
//	}
	
}
