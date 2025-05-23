package com.newstoss.member.application.command;

import com.newstoss.member.adapter.in.web.dto.requestDTO.SignupRequestDTO;
import com.newstoss.member.adapter.out.persistence.JPAMemberRepository;
import com.newstoss.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class SignupServiceTest {

    @Autowired
    private SignupService signupService;

    @Autowired
    private JPAMemberRepository memberRepository;

    @Test
    void signuptest() {
        // given
        SignupRequestDTO dto = new SignupRequestDTO("abc123", "pw123", "홍길동", "010-1234-5678", "test@test.com", UUID.randomUUID());

        // when
        Member saved = signupService.exec(dto);

        // then
        assertThat(saved.getAccount()).isEqualTo("abc123");
        assertThat(memberRepository.findById(saved.getMemberId())).isPresent();
    }

    @Test
    void check_id() {
        // given
        SignupRequestDTO dto = new SignupRequestDTO("abc123", "pw123", "홍길동", "010-1234-5678", "test@test.com", UUID.randomUUID());
        signupService.exec(dto); // 1회 가입

        // when & then
        assertThatThrownBy(() -> signupService.exec(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 계정입니다.");
    }
}
