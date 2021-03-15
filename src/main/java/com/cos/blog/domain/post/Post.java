package com.cos.blog.domain.post;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;

import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer id; // 시퀀스, auto_increment
	   
	   //연관관계
	   @Column(nullable = false,length = 100)
	   private String title;
	   
	   @Lob //대용량 데이터
	   private String content;
	   
	   @ColumnDefault("0")
	   private int count;
	   
	   //연관관계:여러개의 글을 한사람이 작성할 수 있다.
	   @ManyToOne
	   @JoinColumn(name="userId")
	   private User user;
	   
	   @CreationTimestamp
	   private Timestamp createDate;
}
