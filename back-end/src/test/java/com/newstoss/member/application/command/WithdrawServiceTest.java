package com.newstoss.member.application.command;

import com.newstoss.member.adapter.in.web.dto.requestDTO.SignupRequestDTO;
import com.newstoss.member.adapter.out.persistence.JPAMemberRepository;
import com.newstoss.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class WithdrawServiceTest {

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private JPAMemberRepository memberRepository;

    @Autowired
    private SignupService signupService;

    @Test
    void Withdraw(){
        SignupRequestDTO dto = new SignupRequestDTO("abc123", "pw123", "홍길동", "010-1234-5678", "test@test.com", UUID.randomUUID());

        // when
         Member member=signupService.exec(dto);

        // then
        withdrawService.exec(member.getMemberId());
        Optional<Member> result = memberRepository.findById(member.getMemberId());
        assertThat(result).isEmpty();
    }
}