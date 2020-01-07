package edu04.inheritance;

/**
 * <pre>
 * 우수 회원 모델링 클래스
 * </pre>
 * @author Administrator
 *
 */
public class SpecialCustomer extends Customer {
	/** 담당자 이름 */
	private String manager;

	public SpecialCustomer() {}

	public SpecialCustomer(String userid, String userpw, String username, String phone, String email, String grade) {
		super(userid, userpw, username, phone, email, grade);
	}

	public SpecialCustomer(String userid, String userpw, String username, String phone, String email, String grade, String manager) {
		this(userid, userpw, username, phone, email, grade);
		this.manager = manager;
	}

	public String getmanager() {
		return manager;
	}

	public void setmanager(String manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + manager;
	}

}
