/**
 * 
 */
package day10;

/**
 * @author student
 *
 */
public class MoneyException extends Exception {

	public MoneyException() {
		super("MoneyException : 금액 확인");
	}

	public MoneyException(String msg) {
		super(msg);
	}

}
