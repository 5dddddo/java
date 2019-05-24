package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import util.jdbcUtil;
import vo.BookVO;

public class Test07_select_Map {

	// table 정보를 collection 객체에 담기

	public static void main(String[] args) {
		Connection con = null;
		ResultSet rs = null;
		String sql = "select * from book";
		PreparedStatement ps = null;
		int row = 0, cnt = 0;
		Map<Integer, BookVO> books = new HashMap<Integer, BookVO>();
		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookVO book = new BookVO();
				book.setPrice(rs.getInt("price"));
				book.setTitle(rs.getString("title"));
				book.setBookno(rs.getInt("bookno"));
				book.setAuthor(rs.getString("author"));
				book.setPubdate(rs.getDate("pubdate").toString());
				books.put(book.getBookno(), book);

			}
			System.out.println("Select row : " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);

		}
		books.forEach((i,j)-> System.out.println(j));
	}

}
