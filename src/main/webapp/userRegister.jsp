<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<html>
  <head>
    <title>강의평가 웹 사이트</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- 부트스트랩 CSS 추가하기 -->
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <!-- 커스텀 CSS 추가하기 -->
    <link rel="stylesheet" href="./css/custom.css">
  </head>
  <%
	String userID = null;
	if(session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if(userID != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인이 된 상태입니다.');");
		script.println("location.href = 'index.jsp'");
		script.println("</script>");
		script.close();	
	}
%>	
  <body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="index.jsp">강의평가 웹 사이트</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar">
        <ul class="navbar-nav mr-auto" >
          <li class="nav-item dropdown" style="line-height: 40px;">
            <a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown">
              강의
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown">
              <a class="dropdown-item" href="lecture.jsp">강의신청</a>
              <a class="dropdown-item" href="index.jsp">강의평가</a>
            </div>
          </li>
          <li class="nav-item dropdown" style="line-height: 40px;">
            <a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown">
              회원 관리
            </a>
            <div class="dropdown-menu" aria-labelledby="dropdown">
<%
	if(userID == null) {
%>
              <a class="dropdown-item" href="userLogin.jsp">로그인</a>
              <a class="dropdown-item" href="userRegister.jsp">회원가입</a>
<%
	} else {
%>
              <a class="dropdown-item" href="userLogout.jsp">로그아웃</a>
<%
	}
%>
            </div>
          </li>
<%
	if(userID != null) {
%>
          <li class="nav-item active" style="line-height: 40px;">
            <p class="nav-link" style="margin: 0;">${userID}님</p>
          </li>
<%
	}
%>
        </ul>
        <form class="d-flex">
           <a href="./myPage.jsp" class="btn btn-outline-dark" type="submit">
               <i class="bi-cart-fill me-1"></i>
               MYPAGE
           </a>
       </form>
      </div>
    </nav>
    <div class="container mt-3" style="max-width: 560px;">
      <form method="post" action="./userRegisterAction.jsp">
        <div class="form-group">
          <label>아이디</label>
          <input type="text" name="userID" class="form-control">
        </div>
        <div class="form-group">
          <label>비밀번호</label>
          <input type="password" name="userPassword" class="form-control">
        </div>
        <div class="form-group">
          <label>이메일</label>
          <input type="email" name="userEmail" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">회원가입</button>
      </form>
    </div>
   <footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">
      Copyright ⓒ 2023 김보람 All Rights Reserved.
    </footer>
    <!-- 제이쿼리 자바스크립트 추가하기 -->
    <script src="./js/jquery-3.3.1.min.js"></script>
    <!-- popper 자바스크립트 추가하기 -->
    <script src="./js/popper.js"></script>
    <!-- 부트스트랩 자바스크립트 추가하기 -->
    <script src="./js/bootstrap.min.js"></script>
  </body>
</html>