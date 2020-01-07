package edu04.inheritance;

/**
 * <pre>
 * 일반 회원 모델링 클래스
 * </pre>
 * @author Administrator
 *
 */
public class GeneralCustomer extends Customer {
	/** 마일리지 */
	private int mileage;

	/** 기본 생성자 */
	public GeneralCustomer() {}

	/** 필수 데이터 초기화 생성자 */
	public GeneralCustomer(String userid, String userpw, String username, String phone, String email, String grade) {
		super(userid, userpw, username, phone, email, grade);
	}

	/** 전체 데이터 초기화 생성자 */
	public GeneralCustomer(String userid, String userpw, String username, String phone, String email, String grade, int mileage) {
		this(userid, userpw, username, phone, email, grade);
		this.mileage = mileage;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + mileage;
	}

}
