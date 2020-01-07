package edu03.oop.encapsulation.dto;

import java.util.Date;

/**
 * <pre>
 * # TODO : 직원 정보 모델링 클래스 
 * - 제공된 Utility 클래스의 메서드를 활용하세요.
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
 * # Encapsulation
 * - information hiding
 * - access modifier 
 * - setter() / getter()
 *  
 * </pre>
 * @author Administrator
 *
 */
public class Employee {
	private int empNo;
	private String empName;
	private String job;
	private int mgrNo;
	private Date hireDate;
	private float salary;
	private float commission;
	private int deptNo;
	
	public Employee() {}
	
	public Employee(int empNo, String empName, String job, int mgrNo, Date hireDate, float salary, int deptNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.job = job;
		this.mgrNo = mgrNo;
		this.hireDate = hireDate;
		this.salary = salary;
		this.deptNo = deptNo;		
	}

	public Employee(int empNo, String empName, String job, int mgrNo, Date hireDate, float salary, float commission,
			int deptNo) {
		this(empNo, empName, job, mgrNo, hireDate, salary, deptNo);
		this.commission = commission;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgrNo() {
		return mgrNo;
	}

	public void setMgrNo(int mgrNo) {
		this.mgrNo = mgrNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getCommission() {
		return commission;
	}

	public void setCommission(float commission) {
		this.commission = commission;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("사  번: " + empNo);
		builder.append("\n");
		builder.append("이  름: " + empName);
		builder.append("\n");
		builder.append("직  무: " + job);
		builder.append("\n");
		builder.append("상  사: " + mgrNo);
		builder.append("\n");
		builder.append("입사일: " + Utility.getDateToString(hireDate));
		builder.append("\n");
		builder.append("급  여: " + salary);
		builder.append("\n");
		builder.append("수  당: " + commission);
		builder.append("\n");
		builder.append("부  서: " + deptNo);
		builder.append("\n");
		builder.append("-------------------");
		
		return builder.toString();
	}
	
}
