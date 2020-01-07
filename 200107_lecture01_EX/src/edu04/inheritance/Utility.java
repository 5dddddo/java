package edu04.inheritance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * 공통으로 사용위한 유틸리티 클래스
 * </pre>
 * @author Administrator
 *
 */
public class Utility {
	/**
	 * <pre>
	 * 문자열 형식의 날짜를 Date 타입으로 변환 메서드
	 * </pre>
	 * @param date
	 * @return
	 */
	public static Date toDate(String date) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <pre>
	 * Date 타입의 날짜를 "년도4자리-월2자리-일2자리" 형식의 문자열 변환 메서드
	 * </pre>
	 * @param date
	 * @return 문자열 형식의 변환된 날짜
	 */
	public static String getDateToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * <pre>
	 * 현재날짜를 년도4자리-월2자리-일2자리 형식의 문자열 반환 메서드
	 * </pre>
	 * @return 현재날짜
	 */
	public static String getCurrentDateToString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
}
