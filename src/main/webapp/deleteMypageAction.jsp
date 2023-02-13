<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="myPage.*"%>
<%@ page import="lecture.*"%>
<%@ page import="user.*"%>
<%@ page import="java.io.PrintWriter"%>
<%
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
	int lectureNum = 0;
	if(request.getParameter("lectureNum") != null) {
		try {
			lectureNum = Integer.parseInt(request.getParameter("lectureNum"));
		} catch (Exception e) {
			System.out.println("강의번호 데이터 오류");
		}
	}
	MypageDAO mypageDAO = new MypageDAO();
	if(userID.equals(mypageDAO.getUserID(lectureNum))) {
		int result = new MypageDAO().delete(lectureNum);
		if (result == 1) {
			session.setAttribute("userID", userID);
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('삭제가 완료되었습니다.');");
			script.println("location.href='myPage.jsp'");
			script.println("</script>");
			script.close();
			return;
		} else {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();
			return;
		}
	}
%>