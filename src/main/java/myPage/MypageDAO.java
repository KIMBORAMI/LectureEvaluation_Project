package myPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import evaluation.EvaluationDTO;
import lecture.LectureDTO;


public class MypageDAO {
	private static final String driver="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE";
	private static final String user="LECTUREEVALUATION";
	private static final String pwd="1234";
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;


	public int get(MypageDTO mypageDTO) {
		try {
			connDB();
			String SQL = "INSERT INTO MYPAGE(userID, lectureNum, lectureName, lecturePrice, lectureImage) SELECT a.userid , b.lecturenum ,b.lecturename,b.lectureprice,b.lectureimage FROM USERTABLE a, lecture b WHERE a.USERID=? and b.lecturenum=?";      
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, mypageDTO.getUserID());
			pstmt.setInt(2, mypageDTO.getLectureNum());
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
			//myPage(특정사용자)게시글 목록 메서드
					public List<MypageDTO> mypageList(String userID) {
						List<MypageDTO> leMypageList=new ArrayList<MypageDTO>();
						try {
							connDB();
							String SQL="select * from MYPAGE WHERE USERID=?";
							pstmt=con.prepareStatement(SQL);
							System.out.println(SQL);
							pstmt.setString(1, userID);
							rs=pstmt.executeQuery();
							while(rs.next()) {
								String userID1=rs.getString("userID");
								String lectureName=rs.getString("lectureName");
								String lecturePrice=rs.getString("lecturePrice");
								Integer lectureNum=rs.getInt("lectureNum");
								String lectureImage=rs.getString("lectureImage");
								MypageDTO vo=new MypageDTO();
								vo.setUserID(userID1);
								vo.setLectureName(lectureName);
								vo.setLecturePrice(lecturePrice);
								vo.setLectureNum(lectureNum);
								vo.setLectureImage(lectureImage);
								leMypageList.add(vo);
							}
							rs.close();
							pstmt.close();
							con.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("글 목록 조회중 에러"+e);
						}
						return leMypageList;
					}
					
					public int delete(int lectureNum) {
						try {
							connDB();
							String SQL ="DELETE FROM MYPAGE WHERE lectureNum = ?";
							pstmt = con.prepareStatement(SQL);
							pstmt.setInt(1, lectureNum);
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
					
					public String getUserID(int lectureNum) {
						try {
							connDB();
							String SQL = "SELECT userID FROM MYPAGE WHERE lectureNum = ?";
							pstmt = con.prepareStatement(SQL);
							pstmt.setInt(1, lectureNum);
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
