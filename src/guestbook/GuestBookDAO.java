package guestbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestBookDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/itbank";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed: Connected to database");
			e.printStackTrace();
		}
		System.out.println("Success: Connected to database");
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, "iu", "iu1004");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	private PreparedStatement setPrepareStatement(Connection conn, String sql, Object status) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if (status instanceof String) {
			pstmt.setString(1, (String) status);
		} else {
			pstmt.setInt(1, (int) status);
		}
		return pstmt;
	}

	// 방명록 게시글 리스트를 조회하는 메서드
	public List<GuestBookVO> getList() {
		List<GuestBookVO> list = new ArrayList<>();
		String sql = "SELECT no, name, pwd, content, reg_date " +
				"FROM guestbook ORDER BY no desc";

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()
		) {
			while (rs.next()) {
				GuestBookVO vo = new GuestBookVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setPwd(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegDate(rs.getString(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시글을 등록하는 메서드
	public boolean insert(GuestBookVO vo) {
		boolean result = false;
		String sql = "INSERT INTO guestbook VALUES (" +
				"null, ?, ?, ?, (SELECT SYSDATE()))";

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getContent());
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 삭제를 위해 게시글에 설정된 비밀번호를 조회하는 메서드
	public String getPwd(int no) {
		String pwd = null;
		String sql = "SELECT pwd FROM guestbook WHERE no = ?";

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = setPrepareStatement(conn, sql, no);
				ResultSet rs = pstmt.executeQuery()
		) {
			// 조회되는 데이터가 1개여도 rs.next() 메서드를 호출해야 한다.
			if (rs.next()) {
				pwd = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pwd;
	}

	// 게시글 삭제를 위해 삭제하려는 사용자가 입력한 비밀번호를 db 에서 암호화해서 조회하는 메서드
	public String getInputPwd(String pwd) {
		String parsePwd = null;
		String sql = "SELECT ?";

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = setPrepareStatement(conn, sql, pwd);
				ResultSet rs = pstmt.executeQuery()
		) {
			// 조회되는 데이터가 1개여도 rs.next() 메서드를 호출해야 한다.
			if (rs.next()) {
				parsePwd = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parsePwd;
	}

	// 게시글을 삭제하는 메서드
	public boolean delete(int no) {
		boolean result = false;
		String sql = "DELETE FROM guestbook WHERE no = ?";

		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setInt(1, no);
			int count = pstmt.executeUpdate();
			result = (count == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
