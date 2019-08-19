package Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.jdbcUtil;

public class Test03 {

	public static void main(String[] args) {
		String sql = "select round(avg(sal),2) as 평균,count(*) as 인원수 from emp group by deptno";
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		try {
			con = jdbcUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				System.out.print(rs.getString("평균") + "   ");
				System.out.print(rs.getString("인원수") + "   ");
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			//jdbcUtil.closeResource(con, rs, st);
		}
	}
}
