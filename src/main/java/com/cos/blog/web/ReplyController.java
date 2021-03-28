package com.cos.blog.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.service.ReplyService;
import com.cos.blog.web.dto.CommonRespDto;
import com.cos.blog.web.dto.reply.ReplySaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReplyController {

	private final ReplyService replyService;

	@GetMapping("/hello")
	public void hello() {
		System.out.println("aop 실행됨 hello");
	}

	@CrossOrigin
	@PostMapping("/reply")
	public CommonRespDto<?> save(@Valid @RequestBody ReplySaveReqDto replySaveReqDto, BindingResult bindingResult,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("reply save 호출됨");
		System.out.println("post reply:  " + replySaveReqDto);

		Reply replyEntity = replyService.댓글쓰기(replySaveReqDto, principalDetails.getUser());
		return new CommonRespDto<>(1, replyEntity);
	}

	@DeleteMapping("/reply/{id}")
	public CommonRespDto<?> deleteById(@PathVariable int id,
			@AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 모든 컨트롤러에 삭제하기, 수정하기는 무조건 동일인물이 로그인했는지 확인해야 한다
		int result = replyService.삭제하기(id, principalDetails.getUser().getId());
		return new CommonRespDto<>(result, null);
	}
}
