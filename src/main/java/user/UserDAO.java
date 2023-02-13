package user;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;



public class UserDAO {

	private static final String driver="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="LECTUREEVALUATION";
	private static final String pwd="1234";
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;


	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM USERTABLE WHERE userID = ?";
		try {
			connDB();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 틀림
			}
			return -1; // 아이디 없음
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USERTABLE VALUES (?, ?, ?, ?)";
		try {
			connDB();
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 회원가입 실패

	}
	
	public int myUser(String userID) {
		try {
			connDB();
			String SQL = "SELECT * FROM USERTABLE WHERE userID = ?";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			con=DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection 생성 성공");
		} catch (Exception e) {
			System.out.println("연결오류" + e.getMessage());
		}
	}

}


