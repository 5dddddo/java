package day06;

import util.Calc;

public class AccountTest {

	public static void main(String[] args) {

		Account a1 = new Account();
		Account a2 = new Account();
		a1.setAccount("001");
		a1.setMoney(10000);
		a2.setAccount("003");
		a2.setMoney(10000);
		Account.sendMoney(a1, a2, 1500);
		a1.print();
		a2.print();

		int sum = Calc.add(2, 4, 6, 8, 10);
		System.out.println(sum);
	}

}
