package myPage;

public class MypageDTO {
	String userID;
	int lectureNum;
	String lectureName;
	String lecturePrice;
	String lectureImage;
	
	
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getLecturePrice() {
		return lecturePrice;
	}
	public void setLecturePrice(String lecturePrice) {
		this.lecturePrice = lecturePrice;
	}
	public String getLectureImage() {
		return lectureImage;
	}
	public void setLectureImage(String lectureImage) {
		this.lectureImage = lectureImage;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getLectureNum() {
		return lectureNum;
	}
	public void setLectureNum(int lectureNum) {
		this.lectureNum = lectureNum;
	}
	
	public MypageDTO() {
		
	}
	public MypageDTO(String userID, int lectureNum, String lectureName, String lecturePrice, String lectureImage) {
		super();
		this.userID = userID;
		this.lectureNum = lectureNum;
		this.lectureName = lectureName;
		this.lecturePrice = lecturePrice;
		this.lectureImage = lectureImage;
	}
	
	
}
