package user;

import java.sql.*;

public class UserDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/itbank";

	/*
	@ 데이터베이스 연결
	@ param getConnection(url, userName, password);
	@ return Connection
	*/
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. 드라이버 로딩
			/* 설명
			 * 1) 드라이버 인터페이스를 구현한 클래스를 로딩
			 * 2) mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
			 * 3) mysql 은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링 하면 된다.
			 * 4) 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다. */
			Class.forName("com.mysql.jdbc.Driver");

			// 2. 연결하기
			/* 설명
			 * 1) 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
			 * 2) Connection 을 얻기 위해 필요한 url 역시, 벤더사마다 다르다
			 * 3) mysql 은 "jdbc:mysql://localhost/dbName" 이다.*/
			conn = DriverManager.getConnection(URL, "iu", "iu1004");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed: Connected to database");
		}
		// 3. 연결된 Connection 반환
		System.out.println("Success: Connected to database");
		return conn;
	}

	public void select() {
		// 1. SQL 쿼리 작성
		/* 주의사항
		 * 1) JDBC 에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
		 * 2) SELECT 할 때 * 으로 모든 칼럼을 가져오는 것보다 가져와야 할 칼럼을 직접 명시해주는 것이 좋다.
		 * 3) 원하는 결과는 쿼리로써 마무리 짓고, java 코드로 후작업 하는 것은 권하지 않음
		 * 4) 쿼리를 한 줄로 쓰기 어려운 경우 들여쓰기를 사용해도 되지만 띄어쓰기에 유의 !! */

		String sql = "";

		// 2. 쿼리 수행을 위한 객체 생성
		// 레코드들은 ResultSet 객체에 추가된다.
		try (
				Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)
		) {
			// 3. 실행 결과 출력하기
			while (rs.next()) {
				/* 참고
				* 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				* 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다. */
				String name = rs.getString(1);
				String owner = rs.getString(2);
				String date = rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean insert(UserVO vo) {
		// 1. SQL 쿼리 작성
		/* 주의사항
		 * 1) 추가하려는 데이터의 값은 전달된 인자를 통해 동적으로 할당되는 값이다.
		 * 2) 즉 어떤 값이 전달될지 모르므로 Select 할 때와 달리
		 *      stmt = conn.createStatement(); 를 작성하지 않고,
		 *      pstmt = conn.prepareStatement(sql); 로 작성하여 데이터를 추가할 것임을 알립니다.
		 * 3) 물론 sql 쿼리 내에서 + 연산자로 한 줄로 작성할 수 있지만 가독성이 너무 떨어지게 되므로
		 *      이 방법을 권합니다. */

		// Column : PK, name, email, pwd
		String sql = "INSERT INTO user VALUES (null, ?, ?, ?)";
		boolean result = false;

		// 2. 쿼리 수행을 위한 객체 생성
		try (
				Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			// 3. 데이터 binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPwd());

			// 4. 쿼리 실행 및 결과 처리
			/* 참고
			* 1) SELECT 와 달리 INSERT 는 반환되는 데이터들이 없으므로 ResultSet 객체가 필요 없고,
			*       바로 pstmt.executeUpdate() 메서드를 호출하면 됩니다.
			* 2) INSERT, UPDATE, DELETE 쿼리는 이와 같이 메서드를 호출하며,
			*       SELECT 에서는 stmt.executeQuery(sql); 메서드를 사용했었습니다.
			* 3) @return int - 몇 개의 row 가 영향을 미쳤는지를 반환 */
			int count = pstmt.executeUpdate();
			System.out.println("가입 " + (count > 0 ? "성공" : "실패"));
			result = (count == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
