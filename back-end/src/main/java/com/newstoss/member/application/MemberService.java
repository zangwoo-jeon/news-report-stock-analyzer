package com.newstoss.member.application;

import com.newstoss.member.adapter.in.web.dto.requestDTO.SignupRequestDTO;
import com.newstoss.member.application.command.SignupService;
import com.newstoss.member.application.command.WithdrawService;
import com.newstoss.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MemberService {
//회원가입
//회원탈퇴
    private final SignupService signupService;
    private final WithdrawService withdrawService;

    public Member signup(SignupRequestDTO signupRequestDTO){
        return signupService.exec(signupRequestDTO);
    }

    public boolean withdraw(UUID memberId){
        return withdrawService.exec(memberId);
    }
}
