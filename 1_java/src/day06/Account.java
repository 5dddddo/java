package day06;

public class Account {
	private String account;
	private int money;

	public Account() {
		this("000",0) ; //다른 생성자 호출
	};

	public Account(String a, int m) {
		setAccount(a);
		setMoney(m);
	};

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money > 0)
			this.money = money;
	}

	public void deposit(int m) {
		if (money > 0) {
			money += m;
			System.out.printf("%d원이 입금되었습니다. 잔액은 %d원입니다.\n", m, money);
		}
	}

	public int withdraw(int m) {
		if (money >= m) {
			System.out.printf("%d원이 출금되었습니다.\n", m);
			money -= m;
			return money;
		} else {
			System.out.println("잔액이 부족합니다.");
			return 0;
		}
	}

	/**
	 * 
	 * @param from 출금 계좌
	 * @param to   입금 계좌
	 * @param m    송금액
	 */

	public static void sendMoney(Account from, Account to, int m) {
		if (from.money >= m && to.account != null) {
			from.withdraw(m);
			to.deposit(m);
			System.out.printf("%s 계좌에서 %s 계좌로 %d원을 송금합니다. \n%s 계좌의 잔액은 %d원입니다. %s 계좌의 잔액은 %d입니다.\n", from.account,
					to.account, m, from.account, from.money, to.account, to.money);
		} else
			System.out.println("잔액이 부족합니다.");
	}

	public void print() {
		System.out.printf("[계좌 번호 : %s 잔고 : %d]\n", account, money);
	}

}
