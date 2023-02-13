<%@page import="user.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lecture.LectureDTO"%>
<%@ page import="user.UserDTO"%>
<%@ page import="lecture.LectureDAO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="myPage.MypageDTO"%>
<%@ page import="myPage.MypageDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
request.setCharacterEncoding("UTF-8");
	String userID = null;
	if(session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if(userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.');");
		script.println("location.href = 'userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	request.setCharacterEncoding("UTF-8");
	String lectureName = null;
	String lecturePrice=null;
	int lectureNum = 0;
	String lectureImage=null;
	
	if(request.getParameter("userID") != null) {
		userID = (String) request.getParameter("userID");
	}
	if(request.getParameter("lectureName") != null) {
		lectureName = (String) request.getParameter("lectureName");
	}
	if(request.getParameter("lecturePrice") != null) {
		lecturePrice = (String) request.getParameter("lecturePrice");
	}
	if(request.getParameter("lectureNum") != null) {
		try {
			lectureNum = Integer.parseInt(request.getParameter("lectureNum"));
		} catch (Exception e) {
			System.out.println("강의번호 데이터 오류");
		}
	}
	if(request.getParameter("lectureImage") != null) {
		lectureImage = (String) request.getParameter("lectureImage");
	}
	UserDAO userDAO=new UserDAO();
	int userResult=userDAO.myUser(userID);
	if(userResult==1) {

		return ;
	}
	
	LectureDAO lectureDAO=new LectureDAO();
	int lectureResult=lectureDAO.myLecture(0);
	if(lectureResult==1) {
		return ;
	}
	
	MypageDAO mypageDAO = new MypageDAO();
	int result = mypageDAO.get(new MypageDTO(userID,lectureNum,lectureName,lecturePrice,lectureImage));
	if (result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('강의 등록에 실패했습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	} else {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('강의 신청이 완료되었습니다.');");
		script.println("location.href = './myPage.jsp';");
		script.println("</script>");
		script.close();
		return ;
	}
	
%>