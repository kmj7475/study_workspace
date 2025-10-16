package com.example.demo.service.impl;

import com.example.demo.domain.Member;
import com.example.demo.dto.MemberDto;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDto.Response createMember(MemberDto.CreateRequest request) {
        Member member = request.toEntity();
        Member saved = memberRepository.save(member);
        return MemberDto.Response.fromEntity(saved);
    }

    @Transactional(readOnly = true)
    public MemberDto.Response getMember(Long memberNo) {
        return memberRepository.findById(memberNo)
                .map(MemberDto.Response::fromEntity)
                .orElse(null);
    }

    @Transactional
    public MemberDto.Response updateMember(MemberDto.UpdateRequest request) {
        if (request.getMemberNo() == null)
            return null;
        return memberRepository.findById(request.getMemberNo())
                .map(member -> {
                    request.applyTo(member);
                    Member saved = memberRepository.save(member);
                    return MemberDto.Response.fromEntity(saved);
                })
                .orElse(null);
    }

    @Transactional
    public boolean deleteMember(Long memberNo) {
        if (!memberRepository.existsById(memberNo))
            return false;
        memberRepository.deleteById(memberNo);
        return true;
    }

    @Transactional(readOnly = true)
    public List<MemberDto.Response> listMembers() {
        return memberRepository.findAll().stream()
                .map(MemberDto.Response::fromEntity)
                .collect(Collectors.toList());
    }
}