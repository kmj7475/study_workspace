package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import java.util.List;

public interface MemberService2 {
    MemberDto.Response createMember(MemberDto.CreateRequest request);

    MemberDto.Response getMember(Long memberNo);

    MemberDto.Response updateMember(MemberDto.UpdateRequest request);

    boolean deleteMember(Long memberNo);

    List<MemberDto.Response> listMembers();
}