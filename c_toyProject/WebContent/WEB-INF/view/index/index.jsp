<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<body>
	<h1>Toy Project Index Page</h1>
	
	<c:choose>
		<c:when test="${empty sessionScope.user}">
			<h2><a href="/member/join">회원가입</a></h2>
			<h2><a href="/member/login">로그인</a></h2>
		</c:when>
		<c:otherwise>
			<h2><a href="/member/mypage">마이페이지</a></h2>
			<h2><a href="/member/logout">로그아웃</a></h2>
		</c:otherwise>
	</c:choose>
	
</body>
</html>