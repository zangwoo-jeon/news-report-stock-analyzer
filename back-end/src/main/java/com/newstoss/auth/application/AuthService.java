package com.newstoss.auth.application;

import com.newstoss.auth.adapter.in.web.dto.requestDTO.LoginDTO;
import com.newstoss.auth.adapter.in.web.dto.requestDTO.LogoutDTO;
import com.newstoss.auth.application.command.LoginService;
import com.newstoss.auth.application.command.LogoutService;
import com.newstoss.member.application.MemberService;
import com.newstoss.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    //로그읜
    //로그아웃
    private final LoginService loginService;
    private final LogoutService logoutService;

    public String login(LoginDTO loginDTO){
        return loginService.exec(loginDTO);
    }

    void logout(LogoutDTO logoutDTO){
        logoutService.exec(logoutDTO);
    }

}
