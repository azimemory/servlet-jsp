<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	span{
		display: block;
		font-size:1.5vw;
		color:red;
	}
</style>
</head>
<body>
<h1>JSTL</h1>
<pre>
	JSP에서 사용하는 스크립틀릿의 복잡함을 해결하기 위해 등장한 사용자정의 태그
	변수 생성, 출력, 조건문, 반복문, formatting 등을 지원해준다.
</pre>

<h2>1. c:set 변수 생성</h2>
<%-- var : 변수, value : 값 --%>
<c:set var="num1" value="100"/>
<c:set var="num2" value="200"/>
<c:set var="html" value="<a href='https://www.naver.com'>naver로 이동</a>" />
<c:set var="js" value="<script>console.dir('el 표현식은 콘솔창에 출력이 된다.')</script>" />

<h2>2. c:out 변수 출력</h2>
<span>num1 : <c:out value="${num1}"/></span>
<span>num2 : ${num2}</span>

<span>c:out html : <c:out value="${html}"/></span>
<span>el html : ${html}</span>

<span>c:out js : <c:out value="${js}"/></span>
<span>el js : ${js}</span>

<h2>3. jstl을 사용한 배열생성</h2>
<c:set var="jstlArr">
	red,blue,yellow,pink,green
</c:set>
<span>jstlArr : <c:out value="${jstlArr}" /></span>

<h2>4. jstl 조건문</h2>
<h3>c:if</h3>
<c:if test="${num1 < num2}">
	<span>조건문의 조건식 결과가 true 입니다.</span>
</c:if>
<c:if test="${true and false}">
	<span>조건문의 조건식 결과가 false 입니다.</span>
</c:if>

<h3>c:choose when otherwise</h3>
<c:set var="score" value="89"/>
<c:choose>
	<c:when test="${score ge 90 }">
		<span>당신의 학점은 A 입니다.</span>
	</c:when>
	<c:when test="${score ge 80 }">
		<span>당신의 학점은 B 입니다.</span>
	</c:when>
	<c:when test="${score ge 70 }">
		<span>당신의 학점은 C 입니다.</span>
	</c:when>
	<c:when test="${score ge 60 }">
		<span>당신의 학점은 D 입니다.</span>
	</c:when>
	<c:otherwise>
		<span>당신의 학점은 F 입니다.</span>
	</c:otherwise>
</c:choose>

<h2>5. jstl 반복문</h2>
<h3>c:forEach</h3>
<h4>for문처럼 사용하기</h4>
<pre>
	var : i
	begin : 시작값
	end : 끝값
	step : 증가하는 크기
</pre>
<span>
	<c:forEach var="i" begin="0" end="10" step="1">
		${i}
	</c:forEach>
</span>
<span>
	<c:forEach var="i" begin="0" end="10" step="2">
		${i}
	</c:forEach>
</span>

<h4>forEach문처럼 쓰기</h4>
<pre>
	var : 배열 또는 리스트의 요소를 받을 변수
	items : forEach에서 탐색할 배열 또는 리스트
	varStatus : index, count 속성을 가진 객체
			index : 현재 탐색 중인 요소의 인덱스 (시작이 0)
			count : 현재 탐색 중인 요소의 차례 (시작이 1)
</pre>

<table border="1">
	<tr>
		<th>index</th>
		<th>count</th>
		<th>value</th>
	</tr>
	
	<c:forEach var="color" items="${jstlArr}" varStatus="status">
		<tr>
			<td><span>${status.index}</span></td>
			<td><span>${status.count}</span></td>
			<td><span>${color}</span></td>
		</tr>
	</c:forEach>
</table>

<h4>c:forTokens : StringTokernizer와 유사한 기능</h4>
<pre>
	var : 잘라진 문자열를 받을 변수
	items : 자를 문자열
	delims : 구분자
</pre>
<ul>
	<c:forTokens var='lang' items="java html css javascript oracle servlet" delims=" ">
		<li>
			${lang}
		</li>
	</c:forTokens>
</ul>


















</body>
</html>