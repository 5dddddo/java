package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.jdbcUtil;
import vo.DeptVO;

public class DeptManager {
	public List<DeptVO> getDepts() {
		List<DeptVO> list = new ArrayList<DeptVO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from Dept";
		try {
			con = jdbcUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				DeptVO d = new DeptVO();
				d.setDeptno(rs.getInt("deptno"));
				d.setDname(rs.getString("dname"));
				d.setLoc(rs.getString("loc"));
				list.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.closeResource(con, rs, ps);
		}
		return list;
	}

}
