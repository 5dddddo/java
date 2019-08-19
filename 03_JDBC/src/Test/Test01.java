package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01 {

	public static void main(String[] args) {
		System.out.println(" JDBC TEST ");
		String sql = "select * from dept";
		// DB 정보 : Hard coding 하면 안 됨!
		String driver = "oracle.jdbc.OracleDriver"; // oracleDriver 클래스
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "scott";
		String pw = "TIGER";

		// DB 연결
		// con , st, rs : 연결 지향형 객체들로 자원 반납 확실하게 할 것
		// 연결 끊긴 후 DB 데이터 쓸 수 없음
		// 계속 연결하고 반납 안하면 자원 낭비 -> DB 정보를 java collection으로 생성해서 쓸 것!
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName(driver); // 1.드라이버 로딩 : 메모리에 띄우는 Method
			con = DriverManager.getConnection(url, user, pw); // 2. oracle(DB)과 연결 생성
			st = con.createStatement(); // 3.con을 통해 넘어갈 sql 구문을 관리하는 statement 객체 생성
			rs = st.executeQuery(sql); // 4. sql 구문 실행

			// select 명령어는 set 구조로 return 하기 때문에 resultSet 객체로 받아옴
			// getstring(int col index)을 col 갯수보다 적거나 같게 해야함
			// getstring(String col name)도 가능
			while (rs.next()) {
				System.out.print(rs.getString("deptno") + "   ");
				System.out.print(rs.getString("dname") + "   ");
				System.out.print(rs.getString("loc") + "   ");
				System.out.println();
			}

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 확인 필요");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		System.out.println("end");
	}

}
