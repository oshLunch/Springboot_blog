package com.cos.blog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.post.PostRepository;
import com.cos.blog.domain.reply.Reply;
import com.cos.blog.domain.reply.ReplyRepository;
import com.cos.blog.domain.user.User;
import com.cos.blog.web.dto.reply.ReplySaveReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final PostRepository postRepository;
	
	@Transactional
	public int 삭제하기(int id, int userId) {
		Reply replyEntity = replyRepository.findById(id).get();
		
		if(replyEntity.getUser().getId() == userId) {
			replyRepository.deleteById(id);
			return 1;
		} else {
			return -1;
		}
	}
	
	@Transactional
	public Reply 댓글쓰기(ReplySaveReqDto replySaveReqDto, User principal) {
		
		Post postEntity = postRepository.findById(replySaveReqDto.getPostId()).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
				
		Reply reply = replySaveReqDto.toEntity();
		reply.setUser(principal);
		reply.setPost(postEntity);
				
		return replyRepository.save(reply);
	}
}
