package prob;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.jdbcUtil;

public class EmpTest {

	public static void main(String[] args) {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select count(*) 직속부하직원수,m.employee_id 관리자사번,m.last_name 관리자이름 " + 
				"from employees e join employees m " + 
				"on e.manager_id = m.employee_id " + 
				"group by e.manager_id, m.employee_id,m.last_name " + 
				"having count(*) >= 8 " + 
				"order by 직속부하직원수";

		try {
			con = jdbcUtil.getConnection();
			st = con.createStatement();

			//ps = con.prepareStatement(sql);

			// ? 세팅 작업 : preparestatement
			// ps.setString(parameterIndex, x); // (물음표 갯수, binding할 변수명)
			// 실행

			rs = st.executeQuery(sql);
			// 결과값
			while(rs.next()) {
				System.out.print(rs.getString("직속부하직원수") + "    ");
				System.out.print(rs.getString("관리자사번") + "    ");
				System.out.print(rs.getString("관리자이름") + "    ");
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			jdbcUtil.closeResource(con, rs, st);
		}

	}

}
