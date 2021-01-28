<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<%--
		${}  ==  <%= %>
		
		el표기법 : 자바 bean 객체의 값들을 편하게 사용하기 위해 제공되는 표기법
		자바 bean 규약을 따르는 객체 자바 bean객체라고 한다.
		자바 bean 규약 : 기본 생성자, 캡슐화, getter/setter
		request.setAttribute/getAttribute, session, servletContext
	
		${} : 객체 프로퍼티에서 값을 꺼낼 때 주로 사용
		#{} : 객체 프로퍼티에 값을 넣을 때 주로 사용 (x)
		
		el에서 접근 가능한 데이터들
		${requestScope.name} : request 객체의 Attribute에 저장된 값에 접근
		${sessionScope.name} : session 객체의 Attribute에 저장된 값에 접근
		${applicatopnScope.name} : servletContext 객체의 Attribute에 저장된 값에 접근
		
		scope를 명시하지 않고 속성명만 작성할 경우, 현재 page scope로 부터 
		가까운 scope를 탐색하며 해당 속성의 값을 찾는다. like js scope chain
		
		${param.name} : request객체에 저장된 parameter 접근
		${paramValues.name} : 같은 파라미터명으로 저장된 데이터가 여러 개일 경우 배열로 받아온다.
		${cookie.name} : 쿠키값 조회 
	 --%>

	<h1>EL 사용하기</h1>
	<%-- <%= request.getParameter("name") %> --%>
	<span>이름 : ${param.name}</span>
	<%-- <%= request.getAttribute("sum") %> --%>
	<span>합계 : ${requestScope.sum}</span>
	<%-- scope 없이 속성명만 작성하면 가까운 스코프부터 해당 속성명을 가진 값을 탐색 --%>
	<span>평균 : ${avg}</span>
	
	<h1>EL에서 pageContext 객체 접근하기</h1>
	<span>pageContext : ${pageContext}</span>
	
	
	<h1>EL에서 객체 데이터 꺼내쓰기</h1>
	
	<!-- 1. vo에서 데이터 꺼내쓰기 -->
	<h2>1. vo에서 데이터 꺼내쓰기</h2>
	<pre>
		vo.속성명 을 사용해 해당 속성값을 꺼내쓸 수 있다.
	</pre>
	<span>이름 : ${requestScope.student.name}</span>
	<span>국어 : ${student.kor}</span>
	<span>수학 : ${requestScope.student.math}</span>
	<span>영어 : ${student.eng}</span>
	<span>코딩 : ${requestScope.student.coding}</span>
	<span>합계 : ${student.sum}</span>
	<span>평균 : ${requestScope.student.avg}</span>
	
	<!-- 2. map에서 데이터 꺼내쓰기 -->
	<h2>2. map에서 데이터 꺼내쓰기</h2>
	<pre>
		map.키 형태로 키로 저장된 값을 꺼내쓸 수 있다.
	</pre>
	<span>이름 : ${requestScope.studentMap.name}</span>
	<span>국어 : ${studentMap.kor}</span>
	<span>수학 : ${requestScope.studentMap.math}</span>
	<span>영어 : ${studentMap.eng}</span>
	<span>코딩 : ${requestScope.studentMap.coding}</span>
	<span>합계 : ${studentMap.sum}</span>
	<span>평균 : ${requestScope.studentMap.avg}</span>

	<!-- 3. list/array에서 데이터 꺼내쓰기 -->
	<h2>3. array/list에서 데이터 꺼내쓰기</h2>
	<pre>
		[idx] 로 list의 인덱스에 접근할 수 있다.
	</pre>
	<span>이름 : ${requestScope.studentList[1].name}</span>
	<span>국어 : ${studentList[0].kor}</span>
	<span>수학 : ${requestScope.studentList[1].math}</span>
	<span>영어 : ${studentList[0].eng}</span>
	<span>코딩 : ${requestScope.studentList[1].coding}</span>
	<span>합계 : ${studentList[0].sum}</span>
	<span>평균 : ${requestScope.studentList[1].avg}</span>

	<h2>cookie.name</h2>
	<span>JSESSION : ${cookie.JSESSIONID.value}</span>

	<h2>session 확인</h2>
	<span>${sessionScope.name} 님 반갑습니다.</span>

	<h1>EL 리터럴 표현식</h1>
	<span>문자열 : ${"문자열 테스트"}</span>
	<span>문자열 : ${'문자열 테스트'}</span>
	<span>정수 : ${20}</span>
	<span>실수 : ${20.5}</span>
	<span>boolean : ${true}</span>
	<%-- null이 담길 경우 공백으로 출력 --%>
	<span>null : ${null}</span>
	
	<h1>EL 연산자</h1>
	<pre>산술연산자, 논리연산자, 비교연산자, 삼항연산자, empty 연산자</pre>
	<h2>산술연산자 (+ - * / %)</h2>
	<span> 1 + 1 : ${1 + 1}</span>
	<span> 1 - 1 : ${1 - 1}</span>
	<span> 2 * 3 : ${2 * 3}</span>
	<span> 2 / 3 : ${2 / 3}</span>
	<span> 2 % 3 : ${2 mod 3}</span>
	
	<h2>논리연산자</h2>
	<span> true && false : ${true && false}</span>
	<span> true || false : ${true || false}</span>
	<span> !false : ${!false}</span>
	<span> true and false : ${true and false}</span>
	<span> true or false : ${true or false}</span>
	<span> not false : ${not false}</span>
	
	<h2>비교연산자</h2>
	<pre>
		크다 : > gt
		작다 : &lt  lt
		크거나 같다 : >= ge
		작거나 같다 : &lt= le
	</pre>
	<span> 1 == 2 : ${1 == 2}</span>
	<span> 1 != 2 : ${1 != 2}</span>
	<span> 1 > 2 : ${1 gt 2}</span>
	<span> 1 lt 2 : ${1 < 2}</span>
	<span> 1 >= 2 : ${1 ge 2}</span>
	<span> 1 le 2 : ${1 <= 2}</span>
	
	
	<h2>삼항연산자</h2>
	<span>1>2? "크다" : "작다"  >>>  결과:  ${1>2?"크다":"작다"}</span>
	
	<h2>empty 연산자</h2>
	<pre>
		값이 null 이면 true
		문자, 배열, collection의 length가 0이면 true
		이외에는 false
	</pre>
	<span>
		empty null : ${empty null}
	</span>
	<span>
		empty sessionScope.name : ${empty sessionScope.name}
	</span>
	
	
	
	
	
	
	














</body>
</html>