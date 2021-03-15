package com.cos.blog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{
   
   private final UserRepository UserRepository;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
      User principal = UserRepository.findByUsername(username);
      
      if(principal == null) {
         
         return null;
      }else {
         return new PrincipalDetails(principal);
      }
      
   }
}
