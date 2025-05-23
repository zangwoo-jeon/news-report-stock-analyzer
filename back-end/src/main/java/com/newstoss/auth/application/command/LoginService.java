package com.newstoss.auth.application.command;

import com.newstoss.auth.adapter.in.web.dto.requestDTO.LoginDTO;
import com.newstoss.auth.domain.AuthCommandPort;
import com.newstoss.auth.domain.AuthQueryPort;
import com.newstoss.auth.jwt.JWTProvider;
import com.newstoss.global.errorcode.UserErrorCode;
import com.newstoss.global.handler.CustomException;
import com.newstoss.member.application.query.GetMemberService;
import com.newstoss.member.domain.Member;
import com.newstoss.member.domain.MemberCommandPort;
import com.newstoss.member.domain.MemberQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final GetMemberService getMemberService;  //
    private final JWTProvider jwtProvider;

    public String exec(LoginDTO dto) {
        Member member = getMemberService.findByAccount(dto.getAccount());

        if (!dto.getPassword().equals(member.getPassword())) {
            throw new CustomException(UserErrorCode.INVALID_PASSWORD);
        }

        return jwtProvider.generateToken(member.getMemberId());
    }
}
