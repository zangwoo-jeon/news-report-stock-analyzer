package com.newstoss.member.application.command;

import com.newstoss.member.adapter.in.web.dto.requestDTO.SignupRequestDTO;
import com.newstoss.member.domain.Member;
import com.newstoss.member.domain.MemberCommandPort;
import com.newstoss.member.domain.MemberQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final MemberQueryPort memberQueryPort;
    private final MemberCommandPort memberCommandPort;

    public Member exec(SignupRequestDTO signupRequestDTO){
        Optional<Member> existing = memberQueryPort.findByAccount(signupRequestDTO.getAccount());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 계정입니다.");
        }

        Member member = Member.builder()
                .memberId(UUID.randomUUID())
                .account(signupRequestDTO.getAccount())
                .password(signupRequestDTO.getPassword())
                .name(signupRequestDTO.getName())
                .phoneNumber(signupRequestDTO.getPhoneNumber())
                .email(signupRequestDTO.getEmail())
                .fgOffset(signupRequestDTO.getFgOffset())
                .build();
        return memberCommandPort.save(member);
    }
}
