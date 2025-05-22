package com.newstoss.member.application.command;

import com.newstoss.member.domain.Member;
import com.newstoss.member.domain.MemberCommandPort;
import com.newstoss.member.domain.MemberQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final MemberCommandPort memberCommandPort;
    private final MemberQueryPort memberQueryPort;

    public boolean exec(UUID memberId){
        Optional<Member> member = memberQueryPort.findById(memberId);
        if (member.isEmpty()){
            throw new IllegalArgumentException("회원 id가 db에 없습니다.");
        }
        memberCommandPort.deleteById(memberId);
        return true;
    }
}
