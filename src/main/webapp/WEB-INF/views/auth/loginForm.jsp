<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<body>
	<div style="min-height: 400px; margin-top: 100px; display: flex; justify-content: center;">
		<div style="width: 500px;">
			<h1 align="center">로그인</h1>
			<hr />
			<form action="/login" method="POST">
				<input style="width: 100%; margin-bottom: 10px; padding-left: 10px;" type="text" placeholder="Username" name="username" /> <br />
				<input style="width: 100%; margin-bottom: 10px; padding-left: 10px;" type="password" placeholder="Password" name="password" />
				<br />
				<button class="btn btn-outline-primary" style="width: 100%; margin-bottom: 10px;">로그인</button>
				<br />
			</form>

			<div class="d-flex">			
				<a href="/oauth2/authorization/facebook">
					<img alt="" src="/image/facebook_login.png" style="height: 50px; width: 250px; margin-left: -5px;"/>
				</a>
				
				<a href="/oauth2/authorization/google">
					<img alt="" class="ml-2" src="/image/google_login.PNG" style="height: 50px; width: 250px"/>
				</a>
			</div>
			
			<div class="mt-2 d-flex">
				<a href="/oauth2/authorization/kakao">
					<img alt="" src="/image/kakao_login.png" style="height: 50px; width: 250px; margin-left: -5px;"/>
				</a>
				
				<a href="/oauth2/authorization/naver">
					<img alt="" class="ml-2" src="/image/naver_login.PNG" style="height: 50px; width: 250px"/>
				</a>
			</div>
			<br />
			<span>아직 회원가입이 안되셨나요?</span> <a href="/joinForm">회원가입</a>
		</div>
	</div>
</body>

<%@ include file="../layout/footer.jsp"%>