package com.newstoss.auth.application.command;

import com.newstoss.auth.adapter.in.web.dto.requestDTO.LoginDTO;
import com.newstoss.auth.adapter.in.web.dto.requestDTO.LogoutDTO;
import com.newstoss.auth.domain.AuthCommandPort;
import com.newstoss.auth.domain.AuthQueryPort;
import com.newstoss.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    private final AuthCommandPort authCommandPort;
    private final AuthQueryPort authQueryPort;

    public void exec(LogoutDTO logoutDTO){

    }
}
