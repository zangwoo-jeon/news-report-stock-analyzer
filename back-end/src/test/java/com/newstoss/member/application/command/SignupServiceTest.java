package com.newstoss.member.application.command;

import com.newstoss.global.handler.CustomException;
import com.newstoss.member.adapter.in.web.dto.requestDTO.AddressDTO;
import com.newstoss.member.adapter.in.web.dto.requestDTO.SignupRequestDTO;
import com.newstoss.member.adapter.out.persistence.JPAMemberRepository;
import com.newstoss.member.domain.Address;
import com.newstoss.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        AddressDTO address= new AddressDTO("238","서울","한경");
        SignupRequestDTO dto = new SignupRequestDTO("abc123", "pw123", "홍길동", "010-1234-5678", "test@test.com", UUID.randomUUID(),address);

        // when
        Member saved = signupService.exec(dto);

        // then
        assertThat(saved.getAccount()).isEqualTo("abc123");
        assertThat(memberRepository.findById(saved.getMemberId())).isPresent();
    }

    @Test
    void check_id() {
        // given
        AddressDTO address= new AddressDTO("238","서울","한경");
        SignupRequestDTO dto = new SignupRequestDTO("abc123", "pw123", "홍길동", "010-1234-5678", "test@test.com", UUID.randomUUID(),address);
        signupService.exec(dto); // 1회 가입

        // when & then
        assertThatThrownBy(() -> signupService.exec(dto))
                .isInstanceOf(CustomException.class)
                .hasMessage("이미 존재하는 계정입니다.");
    }
}
