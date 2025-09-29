package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public Member findById(Long memberNo) {
    return memberRepository.findById(memberNo)
        .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
  }
}