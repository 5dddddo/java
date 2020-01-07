package edu04.inheritance;

/**
 * <pre>
 * 회원 공통 모델링 클래스
 * 
 * TODO :
 * - 회원의 등급코드를 추가해보세요 : 일반회원(G), 우수회원(S)
 * - 회원의 주소 정보를 추가해보세요
 * 
 * </pre>
 * 
 * @author Administrator
 *
 */
public class Customer {
	/** 아이디 */
	private String userid = "Guest";
	/** 비밀번호 */
	private String userpw;
	/** 이름 */
	private String username;
	/** 휴대폰 */
	private String phone;
	/** 이메일 */
	private String email;
	
	/** 등급 : 설계변경 추가 */
	private String grade;

	/** 기본 생성자 */
	public Customer() {}

	/** 필수 데이터 초기화 생성자 */
	public Customer(String userid, String userpw, String username) {
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
	}
	
	/** 전체 데이터 초기화 생성자 */
	public Customer(String userid, String userpw, String username, String phone, String email, String grade) {
		this(userid, userpw, username);
		this.phone = phone;
		this.email = email;
		this.grade = grade;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		String gradeLabel = grade.equals("G") ? "일반회원" : "우수회원";
		return userid + ", " + userpw + ", " + username + ", " + phone + ", " + email + ", " + gradeLabel;
	}

	
//	@Override
//	public String toString() {
//		return userid + ", " + userpw + ", " + username + ", " + phone + ", " + email + ", " + grade;
//	}

}
