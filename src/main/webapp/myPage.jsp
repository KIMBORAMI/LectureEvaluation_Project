<%@page import="myPage.MypageDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="evaluation.EvaluationDTO"%>
<%@ page import="lecture.LectureDAO"%>
<%@ page import="lecture.LectureDTO"%>
<%@ page import="myPage.MypageDTO"%>
<%@ page import="myPage.MypageDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<!-- 부트스트랩 CSS 추가하기 -->
<link rel="stylesheet" href="./css/custom.css">
<!-- 부트스트랩 CSS 추가하기 -->
<link rel="stylesheet" href="./css/bootstrap.min.css">


<title>Insert title here</title>
</head>
<body>

<% 
	int lecturenum=0;
	String lecturename=null;
	String lectureprice=null;
	String lectureimage=null;
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
    <div class="mt-5" style="border: 1px solid;">게시글</div>
    <%
    if(userID != null) {
      EvaluationDAO dao=new EvaluationDAO();
      List mylist=dao.myEvaluationList(userID);
      for(int i=0; i<mylist.size(); i++) {
    	  EvaluationDTO evaluationDTO=(EvaluationDTO)mylist.get(i);
    	  Integer evaluationID=evaluationDTO.getEvaluationID();
    	  String UserID=evaluationDTO.getUserID();
    	  String lectureName=evaluationDTO.getLectureName();
    	  String professorName=evaluationDTO.getProfessorName();
    	  Integer lectureYear=evaluationDTO.getLectureYear();
    	  String semesterDivide=evaluationDTO.getSemesterDivide();
    	  String lectureDivide=evaluationDTO.getLectureDivide();
    	  String evaluationTitle=evaluationDTO.getEvaluationTitle();
    	  String evaluationContent=evaluationDTO.getEvaluationContent();
    	  String totalScore=evaluationDTO.getTotalScore();
    	  String creditScore=evaluationDTO.getCreditScore();
    	  String comfortableScore=evaluationDTO.getComfortableScore();
    	  String lectureScore=evaluationDTO.getLectureScore();
    	  Integer likeCount=evaluationDTO.getLikeCount();
      %>

  <div>
  	<div class="card bg-light mt-3">
        <div class="card-header bg-light">
          <div class="row">
            <div class="col-8 text-left"><%=evaluationDTO.getLectureName() %>&nbsp;<small><%=evaluationDTO.getProfessorName() %></small></div>
            <div class="col-4 text-right">
              종합 <span style="color: red;"><%=evaluationDTO.getTotalScore() %></span>
            </div>
          </div>
        </div>
        <div class="card-body">
          <h5 class="card-title">
            <%=evaluationDTO.getEvaluationTitle() %>&nbsp;<small>(<%=evaluationDTO.getLectureYear() %>년 <%=evaluationDTO.getSemesterDivide() %>)</small>
          </h5>
          <p class="card-text">${evaluationDTO.evaluationContent}</p>
          <div class="row">
            <div class="col-9 text-left">
              성적 <span style="color: red;"><%=evaluationDTO.getCreditScore() %></span>
              널널 <span style="color: red;"><%=evaluationDTO.getComfortableScore() %></span>
              강의 <span style="color: red;"><%=evaluationDTO.getLectureScore() %></span>
              <span style="color: green;">(추천: <%=evaluationDTO.getLikeCount() %>)</span>
            </div>
            <div class="col-3 text-right">

              <a onclick="return confirm('추천하시겠습니까?')" href="./likeAction.jsp?evaluationID=<%=evaluationDTO.getEvaluationID() %>">추천</a>
              <a onclick="return confirm('삭제하시겠습니까?')" href="./deleteAction.jsp?evaluationID=<%=evaluationDTO.getEvaluationID() %>">삭제</a>

            </div>
          </div>
        </div>
      </div>
   </div>
<%
      }
    }else {
    	PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요');");
		script.println("location.href = './userLogin.jsp';");
		script.println("</script>");
		script.close();
    }
%>
       <section class="py-5">
       		<div class="mt-5" style="border: 1px solid;">강의</div>
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
<%
		MypageDAO ldao=new MypageDAO();
		List leMypageList=ldao.mypageList(userID);
			for(int i = 0; i < leMypageList.size(); i++) {
				MypageDTO mypageDTO=(MypageDTO)leMypageList.get(i);
				String UserID=mypageDTO.getUserID();
				String lectureName=mypageDTO.getLectureName();
				String lecturePrice=mypageDTO.getLecturePrice();
				String lectureImage=mypageDTO.getLectureImage();
				Integer lectureNum=mypageDTO.getLectureNum();
			
%>

                    <div class="col mb-5">
                        <div class="h-100" style="background-color: #fff; background-clip: border-box; border: 1px solid rgba(0, 0, 0, 0.125); border-radius: 0.25rem;">
                            <!-- Product image-->
                            <img class="card-img-top" style="width: 237px; height: 154px;" src="<%=mypageDTO.getLectureImage() %>" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder"><%=mypageDTO.getLectureName() %></h5>
                                    <!-- Product price-->
                                    <%=mypageDTO.getLecturePrice() %>
                               	</div>
                       		</div>
                     <!-- Product actions-->
                     <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                      <div class='text-center'>
                      <a class="btn btn-outline-dark mt-auto" onclick="return confirm('취소하시겠습니까?')" href="./deleteMypageAction.jsp?lectureNum=<%=mypageDTO.getLectureNum() %>">취소</a>
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
    
<!-- 제이쿼리 자바스크립트 추가하기 -->
<script src="./js/jquery-3.3.1.min.js"></script>
<!-- popper 자바스크립트 추가하기 -->
<script src="./js/popper.js"></script>
<!-- 부트스트랩 자바스크립트 추가하기 -->
<script src="./js/bootstrap.min.js"></script>
<script type="text/javascript">


function fnPay(){
	alert("결제 기능을 지원하지 않습니다.");
}

function fnClear(){
	if(confirm("장바구니를 비우시겠습니까?")) {
		location.href = "../pro/CartClear.jsp";	
	}
}

function fnGo(){
	location.href = "../../UserMain.jsp";
}
</script>
</body>
</html>