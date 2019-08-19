package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.jdbcUtil;

public class Test04_insert {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null; // 변수 binding에 사용
		ResultSet rs = null;
		String sql = "insert into book (bookno,title,price) "
				+ "values((select nvl(max(bookno),0) + 1 from book),?,?)";
		int row = 0;

		try {
			con = jdbcUtil.getConnection();
			// oracle에 precompile 하라고 요청->
			// 컴파일 하면서 만든 prepareStatement 틀 재사용 ->
			// oracle 성능 향상
			ps = con.prepareStatement(sql);

			// ? 세팅 작업 : preparestatement
			ps.setString(1, "c++");
			ps.setInt(2, 15000);
			// 실행
			row = ps.executeUpdate(); 
			//row == 2면 성공
			// 결과값

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);
		}
	}

}
