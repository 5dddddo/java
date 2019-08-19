package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import util.jdbcUtil;

public class Test04 {
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null; // 변수 binding에 사용
		ResultSet rs = null;
		String sname = JOptionPane.showInputDialog("검색할 사원의 이름을 입력하세요.");
		try {
			String sql = "select * from emp where lower(ename) like ?";
//		String sql = "select * from emp where lower(ename) like '%'||?||'%'";
			con = jdbcUtil.getConnection();
			// st = con.createStatement();

			// oracle에 precompile 하라고 요청->
			// 컴파일 하면서 만든 prepareStatement 틀을 재사용 함 ->
			// oracle 성능이 더 좋아짐
			ps = con.prepareStatement(sql);

			// ? 세팅 작업 : preparestatement
			ps.setString(1, "%" + sname.toLowerCase() + "%");// (물음표 갯수, binding할 변수명)
			// ps.setString(1, sname.toUpperCase());
			// 실행
			rs = ps.executeQuery();

			// 결과값

			while (rs.next()) {
				System.out.print(rs.getString("ename") + "    ");
				System.out.print(rs.getString("deptno") + "    ");
				System.out.print(rs.getDate("hiredate") + "    ");
				System.out.print(rs.getString("job") + "    ");
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs,ps);
		}
	}

}
