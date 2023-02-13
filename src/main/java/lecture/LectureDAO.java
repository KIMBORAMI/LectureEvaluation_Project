package lecture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import evaluation.EvaluationDTO;

public class LectureDAO {
	private static final String driver="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="LECTUREEVALUATION";
	private static final String pwd="1234";
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	//강의목록 메서드
	public List<LectureDTO> lectureList() {
		List<LectureDTO> list=new ArrayList<LectureDTO>();
		try {
			connDB();
			String query="select * from LECTURE";
			pstmt=con.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				String userID=rs.getString("userID");
				String lectureName=rs.getString("lectureName");
				String lecturePrice=rs.getString("lecturePrice");
				String lectureImage=rs.getString("lectureImage");
				int lectureNum=rs.getInt("lectureNum");
				LectureDTO vo=new LectureDTO();
				vo.setUserID(userID);
				vo.setLectureName(lectureName);
				vo.setLecturePrice(lecturePrice);
				vo.setLectureImage(lectureImage);
				vo.setLectureNum(lectureNum);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("글 목록 조회중 에러");
		}
		return list;
	}
	
	//MYPAGE강의목록 메서드
		public List<LectureDTO> myLectureList(String userID) {
			List<LectureDTO> mylist=new ArrayList<LectureDTO>();
			try {
				connDB();
				String query="select * from LECTURE WHERE userID=?";
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, userID);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					String userID1=rs.getString("userID");
					String lectureName=rs.getString("lectureName");
					String lecturePrice=rs.getString("lecturePrice");
					String lectureImage=rs.getString("lectureImage");
					int lectureNum=rs.getInt(0);
					LectureDTO vo=new LectureDTO();
					vo.setUserID(userID1);
					vo.setLectureName(lectureName);
					vo.setLecturePrice(lecturePrice);
					vo.setLectureImage(lectureImage);
					vo.setLectureNum(lectureNum);
					mylist.add(vo);
				}
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println("글 목록 조회중 에러");
			}
			return mylist;
		}
		public String getUserID(String evaluationID) {

			try {
				connDB();
				String SQL = "SELECT userID FROM EVALUATION WHERE evaluationID = ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, Integer.parseInt(evaluationID));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					return rs.getString(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
					if(rs != null) rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	
		public int myLecture(int lecutreNum) {
			try {
				connDB();
				String SQL = "SELECT * FROM LECTURE WHERE lectureNum = ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, lecutreNum);
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
