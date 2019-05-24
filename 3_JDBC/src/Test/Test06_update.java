package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.jdbcUtil;

public class Test06_update {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null; // 변수 binding에 사용
		ResultSet rs = null;
		String sql = "update book set title = ?, price = ? where bookno = ?";
		int row = 0;

		try {
			con = jdbcUtil.getConnection();
			// oracle에 precompile 하라고 요청->
			// 컴파일 하면서 만든 prepareStatement 틀 재사용 ->
			// oracle 성능 향상
			ps = con.prepareStatement(sql);

			// ? 세팅 작업 : preparestatement
			ps.setString(1, "폰");
			ps.setInt(2, 30000);
			ps.setInt(3, 1);
			// 실행
			row = ps.executeUpdate(); 
			// 결과값
			System.out.println("Update row : " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);
		}
	}

}
