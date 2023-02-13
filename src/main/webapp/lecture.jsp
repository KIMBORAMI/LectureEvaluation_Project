<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="lecture.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<!-- 부트스트랩 CSS 추가하기 -->
<link rel="stylesheet" href="./css/bootstrap.min.css">

<title>강의 웹 사이트</title>
</head>
<body>

<% 
	String userID = null;
	if(session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}

%>	
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
    <header class="bg-dark py-5">
        <div class="container px-4 px-lg-5 my-5">
            <div class="text-center text-white">
                <h1 class="display-4 ">강의신청</h1>
            </div>
        </div>
    </header>
     <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
<%
LectureDAO dao=new LectureDAO();
List list=dao.lectureList();
for(int i=0; i<list.size(); i++) {
	  LectureDTO dto=(LectureDTO)list.get(i);
	  String UserID=dto.getUserID();
	  String lectureName=dto.getLectureName();
	  String lecturePrice=dto.getLecturePrice();
	  String lectureImage=dto.getLectureImage();
	  Integer lectureNum=dto.getLectureNum();
%>
             <div class="col mb-5">
                 <div class="card h-100">
                     <!-- Product image-->
                     <img class="card-img-top" style="width: 237px; height: 154px;" src="<%=dto.getLectureImage() %>" alt="..." />
                     <!-- Product details-->
                     <div class="card-body p-4">
                         <div class="text-center">
                             <!-- Product name-->
                             <%
                             out.println("<h5 class='fw-bolder'>" + dto.getLectureName() + "</h5>");
                             out.println(dto.getLecturePrice());
                             %>
                         </div>
                     </div>
                     <!-- Product actions-->
                     <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                      <div class='text-center'>
                      <a class="btn btn-outline-dark mt-auto" onclick="return confirm('신청하시겠습니까?')" href="./mypageAction.jsp?lectureNum=<%=dto.getLectureNum() %>">신청</a>
                      </div>
                     </div>
                 </div>
             </div>
 <%
			}
		
%>
        </div>
    </div>
</section>
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