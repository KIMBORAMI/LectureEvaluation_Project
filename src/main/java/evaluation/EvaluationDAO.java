package evaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluationDAO {
	private static final String driver="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="LectureEvaluation";
	private static final String pwd="1234";
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	
	public int write(EvaluationDTO evaluationDTO) {
		try {
			connDB();
			String SQL = "INSERT INTO EVALUATION VALUES (EVALUATION_SQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, evaluationDTO.getUserID().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(2, evaluationDTO.getLectureName().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(3, evaluationDTO.getProfessorName().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setInt(4, evaluationDTO.getLectureYear());
			pstmt.setString(5, evaluationDTO.getSemesterDivide().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(6, evaluationDTO.getLectureDivide().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(7, evaluationDTO.getEvaluationTitle().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(8, evaluationDTO.getEvaluationContent().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(9, evaluationDTO.getTotalScore().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(10, evaluationDTO.getCreditScore().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(11, evaluationDTO.getComfortableScore().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			pstmt.setString(12, evaluationDTO.getLectureScore().replaceAll("<", "&lt;").replaceAll(">", " &gt;").replaceAll("\r\n", "<br>"));
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(rs != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	//검색+리스트
	public ArrayList<EvaluationDTO> getList(String lectureDivide, String searchType, String search, int pageNumber) {
		if(lectureDivide.equals("전체")) {
			lectureDivide = "";
		}
		ArrayList<EvaluationDTO> evaluationList = null;
		PreparedStatement pstmt = null;
		String SQL = "";
		try {
			connDB();
			if(searchType.equals("최신순")) {
				SQL = "SELECT * FROM (SELECT  A.*, ROWNUM AS RN FROM (SELECT * FROM EVALUATION WHERE LECTUREDIVIDE LIKE ? AND LECTURENAME || PROFESSORNAME || EVALUATIONTITLE || EVALUATIONCONTENT LIKE ? order by evaluationID) A ) WHERE RN BETWEEN "+  pageNumber * 5 + " AND " +(pageNumber * 5 + 6);
			} else if(searchType.equals("추천순")) {
				SQL = "SELECT * FROM (SELECT  A.*, ROWNUM AS RN FROM (SELECT * FROM EVALUATION WHERE LECTUREDIVIDE LIKE ? AND LECTURENAME || PROFESSORNAME || EVALUATIONTITLE || EVALUATIONCONTENT LIKE ? order by LIKECOUNT) A ) WHERE RN BETWEEN "+  pageNumber * 5 + " AND " +(pageNumber * 5 + 6);
			}
			pstmt = con.prepareStatement(SQL);
			System.out.println(SQL);
			pstmt.setString(1, "%" + lectureDivide + "%");
			pstmt.setString(2, "%" + search + "%");
			rs = pstmt.executeQuery();
			evaluationList = new ArrayList<EvaluationDTO>();
			while(rs.next()) {
				EvaluationDTO evaluation = new EvaluationDTO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12),
					rs.getString(13),
					rs.getInt(14)
				);
				evaluationList.add(evaluation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return evaluationList;

	}
		//myPage(특정사용자)게시글 목록 메서드
				public List<EvaluationDTO> myEvaluationList(String userID) {
					List<EvaluationDTO> lmylist=new ArrayList<EvaluationDTO>();
					try {
						connDB();
						String query="select * from EVALUATION WHERE USERID=?";
						pstmt=con.prepareStatement(query);
						pstmt.setString(1, userID);
						ResultSet rs=pstmt.executeQuery();
						while(rs.next()) {
							Integer evaluationID=rs.getInt("evaluationID");
							String userID1=rs.getString("userID");
							String lectureName=rs.getString("lectureName");
							String professorName=rs.getString("professorName");
							Integer lectureYear=rs.getInt("lectureYear");
							String semesterDivide=rs.getString("semesterDivide");
							String lectureDivide=rs.getString("lectureDivide");
							String evaluationTitle=rs.getString("evaluationTitle");
							String evaluationContent=rs.getString("evaluationContent");
							String totalScore=rs.getString("totalScore");
							String creditScore=rs.getString("creditScore");
							String comfortableScore=rs.getString("comfortableScore");
							String lectureScore=rs.getString("lectureScore");
							Integer likeCount=rs.getInt("likeCount");
							EvaluationDTO vo=new EvaluationDTO();
							vo.setUserID(userID1);
							vo.setEvaluationID(evaluationID);
							vo.setLectureName(lectureName);
							vo.setProfessorName(professorName);
							vo.setLectureYear(lectureYear);
							vo.setSemesterDivide(semesterDivide);
							vo.setLectureDivide(lectureDivide);
							vo.setEvaluationTitle(evaluationTitle);
							vo.setEvaluationContent(evaluationContent);
							vo.setTotalScore(totalScore);
							vo.setCreditScore(creditScore);
							vo.setComfortableScore(comfortableScore);
							vo.setLectureScore(lectureScore);
							vo.setLikeCount(likeCount);
							lmylist.add(vo);
						}
						rs.close();
						pstmt.close();
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("글 목록 조회중 에러"+e);
					}
					return lmylist;
				}
		
		public int like(String evaluationID) {
			String SQL = "UPDATE EVALUATION SET likeCount = likeCount + 1 WHERE evaluationID = ?";
			try {
				connDB();
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, Integer.parseInt(evaluationID));
				return pstmt.executeUpdate();
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
			return -1;
		}
		

		public int delete(String evaluationID) {

			try {
				connDB();
				String SQL = "DELETE FROM EVALUATION WHERE evaluationID = ?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, Integer.parseInt(evaluationID));
				return pstmt.executeUpdate();
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
			return -1;
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
