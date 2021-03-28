<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div>
		<button class="btn btn-secondary" onClick="history.go(-1)">목록</button>
		<c:if test="${post.user.id == principal.user.id}">
			<a href="/post/${post.id}/updateForm" class="btn btn-warning">수정</a>
			<button id="btn-delete" class="btn btn-danger" value="${post.id}">삭제</button>
		</c:if>
		<br /> <br />
		<div class="d-flex justify-content-between">
			<span>글 번호 : ${post.id}</span> <span>작성자 : ${post.user.username}</span>
		</div>
		<hr />
		<div>
			<h3>${post.title}</h3>
		</div>
		<hr />
		<div>
			<div>${post.content}</div>
		</div>
	</div>

	<!-- 댓글 시작 -->
	<div class="card">
		<form>
			<input type="hidden" id="postId" value="${post.id}" />
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1" required></textarea>
			</div>
			<div class="card-body">
				<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
			</div>
		</form>
	</div>

	<br />

	<div class="card">
		<div class="card-header">댓글 리스트</div>
		<ul id="reply-box" class="list-group">

			<c:forEach var="reply" items="${post.replys}">
				<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
					<div>${reply.content}</div> <!-- LAZY 로딩 시작 - 이유는 getter가 호출되니깐 (세션이 열려있음 open in view 모드에서만 -->
					<div class="d-flex">
						<div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
						<button onclick="deleteReply(${reply.id})" class="badge">삭제</button>
					</div>
				</li>
			</c:forEach>

		</ul>
	</div>
	<!-- 댓글 끝 -->
</div>

<script>
	$("#btn-reply-save").on("click",(e)=>{
		e.preventDefault(); //submit 막기, type=button 줘도 submit 방지가능

		let data = {
			postId: $("#postId").val(),				
			content: $("#reply-content").val()		
		};	
		
		console.log(data);	

		 $.ajax({
			type:"POST",
			url:"/reply",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"JSON"	
		}).done((res)=>{
			console.log(res);

			if(res.statusCode === 1){
				alert("댓글이 등록되었습니다.");
				location.reload();
			}else{ 
				alert("댓글쓰기에 실패하였습니다.");
			}		
		});   
	});  

	function deleteReply(id){
		//console.log(replyId);
		$.ajax({
			type:"DELETE",
			url:"/reply/"+id,
			dataType:"json"
		}).done((res)=>{
			console.log(res);
			if(res.statusCode === 1){
				$("#reply-"+id).remove();
				alert("댓글이 삭제되었습니다.");
			} else{
				alert("삭제에 실패하였습니다");
			}
		});
	}
</script>

<script>
	$("#btn-delete").on("click", (e)=>{
		let id = e.currentTarget.value;

		$.ajax({
			type:"DELETE",
			url:"/post/"+id,
			dataType:"json"
		}).done(res=>{
			if(res.statusCode === 1){
				alert("삭제에 성공하였습니다");
				history.go(-1);
			} else{
				alert("삭제에 실패하였습니다");
			}
		});
	});
</script>

<%@ include file="../layout/footer.jsp"%>