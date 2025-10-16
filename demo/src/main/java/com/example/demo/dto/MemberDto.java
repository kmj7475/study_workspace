package com.example.demo.dto;

import com.example.demo.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateRequest {
        private String memberId;
        private String memberName;
        private String password;
        private String email;
        private String phone;
        private String likes;
        private String sms;
        private Long point;

        public Member toEntity() {
            Member m = new Member();
            m.setMemberId(this.memberId);
            m.setMemberName(this.memberName);
            m.setPassword(this.password);
            m.setEmail(this.email);
            m.setPhone(this.phone);
            m.setLikes(this.likes);
            m.setSms(this.sms);
            m.setPoint(this.point == null ? 0L : this.point);
            return m;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateRequest {
        private Long memberNo;
        private String memberName;
        private String password;
        private String email;
        private String phone;
        private String likes;
        private String sms;
        private Long point;

        public void applyTo(Member member) {
            if (member == null)
                return;
            if (this.memberName != null)
                member.setMemberName(this.memberName);
            if (this.password != null)
                member.setPassword(this.password);
            if (this.email != null)
                member.setEmail(this.email);
            if (this.phone != null)
                member.setPhone(this.phone);
            if (this.likes != null)
                member.setLikes(this.likes);
            if (this.sms != null)
                member.setSms(this.sms);
            if (this.point != null)
                member.setPoint(this.point);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long memberNo;
        private String memberId;
        private String memberName;
        private String email;
        private String phone;
        private String likes;
        private String sms;
        private Long point;

        public static Response fromEntity(Member m) {
            if (m == null)
                return null;
            return Response.builder()
                    .memberNo(m.getMemberNo())
                    .memberId(m.getMemberId())
                    .memberName(m.getMemberName())
                    .email(m.getEmail())
                    .phone(m.getPhone())
                    .likes(m.getLikes())
                    .sms(m.getSms())
                    .point(m.getPoint())
                    .build();
        }
    }
}