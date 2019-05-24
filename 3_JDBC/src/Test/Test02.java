package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test02 {

	public static void main(String[] args) {
		// emp 부서별 평균 급여와 인원 수 출력		
		String sql = "select round(avg(sal),2) as 평균,count(*) as 인원수 from emp group by deptno";
		// DB 정보 : Hard coding 하면 안 됨!
		String driver = "oracle.jdbc.OracleDriver"; // oracleDriver 클래스
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "scott";
		String pw = "TIGER";
		
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		
		try {
			Class.forName(driver); 
			con = DriverManager.getConnection(url, user, pw); 
			st = con.createStatement(); 
			rs = st.executeQuery(sql); 
			
			
			while (rs.next()) {
				System.out.print(rs.getString("평균") + "   ");
				System.out.print(rs.getString("인원수") + "   ");
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
		
		
	}

}
