package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.jdbcUtil;
import vo.BookVO;

public class Test07_select_List {

	// table 정보를 collection 객체에 담기

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null; // 변수 binding에 사용
		ResultSet rs = null;
		String sql = "select * from book";
		int row = 0;
		List<BookVO> books = new ArrayList<BookVO>();
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

				books.add(book);

			}
			System.out.println("Select row : " + row);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);
			
		}
		for (BookVO b : books)
			System.out.println(b);
	}

}
