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
	public int empNo;
	public String empName;
	public String job;
	public int mgrNo;
	public Date hireDate;
	public float salary;
	public float commission;
	public int deptNo;
	
	public Employee() {}
	
}
