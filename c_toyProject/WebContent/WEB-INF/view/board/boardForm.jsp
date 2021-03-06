<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/board.css" />
	<link rel="stylesheet" href="/resources/css/reset.css"/>
</head>
<body>
<div class="content">
	<h2 class="tit">*게시판</h2>
	<div class="desc_board">
		<form action="${context}/board/upload" method="post" enctype="multipart/form-data">
			<div>
				<div class="tit_board">
					제목 : <input type="text" name="title" required="required"/>
					<!-- multiple : 여러개 파일 선택을 허용하는 속성 -->
					파일 : <input type="file" name="files" id="contract_file" multiple/>
				</div>
				<div class="text">
					<textarea id="board-content" class="board-content" name="content" 
					style="width:99%; height:300px;" required="required"></textarea>
				</div>
				<div class="btn_section">
					<button style="background-color:red; color:white">전송</button>
				</div>
			</div>
		</form>
	</div>
</div>
</body>
</html>