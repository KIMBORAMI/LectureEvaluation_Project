package lecture;

public class LectureDTO {
	String userID;
	String LectureName;
	String LecturePrice;
	String LectureImage;
	int LectureNum;

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getLectureName() {
		return LectureName;
	}
	public void setLectureName(String lectureName) {
		LectureName = lectureName;
	}
	public String getLecturePrice() {
		return LecturePrice;
	}
	public void setLecturePrice(String lecturePrice) {
		LecturePrice = lecturePrice;
	}
	public String getLectureImage() {
		return LectureImage;
	}
	public void setLectureImage(String lectureImage) {
		LectureImage = lectureImage;
	}
	
	public int getLectureNum() {
		return LectureNum;
	}
	public void setLectureNum(int lectureNum) {
		LectureNum = lectureNum;
	}
	public LectureDTO() {
	
	}
	public LectureDTO(String userID, String lectureName, String lecturePrice, String lectureImage, int lectureNum) {
		super();
		this.userID = userID;
		LectureName = lectureName;
		LecturePrice = lecturePrice;
		LectureImage = lectureImage;
		LectureNum = lectureNum;
	}
	
	
	
}
