<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>    
<body>
   <h1>메뉴 주문 결과 확인</h1>
   <hr>
   
   <ol>      
         <c:forEach var="food" items="${requestScope.list}">
         	<li>${food.name} : ${food.price}</li>
         </c:forEach>
   </ol>
   
   <span>
   		주문하신 
 		<c:forEach var="food" items="${list}" varStatus="status">
 			<c:if test="${status.index != 0}">
 			/
 			</c:if>${food.name}</c:forEach>의
 		결재 금액은 ${price}원 입니다.
   </span>
   
   <h3>* 이용해 주셔서 감사합니다.</h3>
</body>
</html>