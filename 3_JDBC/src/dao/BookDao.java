package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.jdbcUtil;
import vo.BookVO;

public class BookDao {
	// select * from book;
	public List<BookVO> booklist() {
		List<BookVO> list = new ArrayList<BookVO>();

		String sql = "select * from book";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				BookVO book = new BookVO();
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setBookno(rs.getInt("bookno"));
				book.setPubdate(rs.getString("pubdate"));
				list.add(book);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);
		}

		return list;
	}

	public int addBook(BookVO vo) throws Exception {
		int row = 0;
		String sql = "insert into book(bookno,title,author,pubdate) "
				+ "values((select nvl(max(bookno),0)+1 from book),?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getAuthor());
			ps.setString(3, vo.getPubdate());
			row = ps.executeUpdate();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);
		}
		return row;

	}

}
