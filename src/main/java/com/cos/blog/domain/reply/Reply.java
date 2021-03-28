package com.cos.blog.domain.reply;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.domain.post.Post;
import com.cos.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id	//Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	// 시퀸스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// 유저
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	// 포스트
	@ManyToOne
	@JoinColumn(name = "postId")
	private Post post;
	
	@CreationTimestamp
	private Timestamp createDate;
}
