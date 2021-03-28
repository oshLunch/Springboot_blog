<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${post.id}" />

		<div class="form-group">
			<input type="text" class="form-control" placeholder="Enter Title" id="title" value="${post.title}" />
		</div>

		<div class="form-group">
			<textarea rows="" cols="5" class="form-control" id="content">
			${post.content}
			</textarea>
		</div>

		<button id="btn-update" class="btn btn-primary">수정완료</button>
	</form>
</div>

<script>
	$('#content').summernote({
		tabsize : 2,
		height : 300
	});
</script>

<<script>
	$("#btn-update").on("click", (e)=> {
		e.preventDefault();	// 항상 넣어준다
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		let id = $("#id").val();
	
		$.ajax({
			type:"PUT",
			url:"/post/"+id,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json"
		}).done((res)=>{
			console.log(res);
			if(res.statusCode === 1){
				alert("수정에 성공하였습니다");
				history.go(-1);
			} else{
				alert("수정에 실패하였습니다");
			}
		});
	
	});
</script>

<%@ include file="../layout/footer.jsp"%>