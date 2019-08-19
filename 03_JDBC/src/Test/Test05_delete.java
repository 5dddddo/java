package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.jdbcUtil;

public class Test05_delete {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null; // 변수 binding에 사용
		ResultSet rs = null;
		String sql = "delete from book where bookno = ?";
		int row = 0;

		try {
			con = jdbcUtil.getConnection();

			// oracle에 precompile 하라고 요청->
			// 컴파일 하면서 만든 prepareStatement 틀을 재사용 함 ->
			// oracle 성능이 더 좋아짐
			ps = con.prepareStatement(sql);

			// ? 세팅 작업 : preparestatement
			ps.setInt(1, 1); // (물음표 갯수, binding할 변수명)
			// 실행
			row = ps.executeUpdate();
			// 결과값
			System.out.println("Delete row : " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			jdbcUtil.closeResource(con, rs, ps);
		}
	}

}
