package com.flab.comen.member.service;

import static com.flab.comen.member.domain.ActiveType.*;
import static com.flab.comen.member.domain.Role.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flab.comen.member.domain.Member;
import com.flab.comen.member.dto.JoinRequest;
import com.flab.comen.member.exception.DuplicatedEmailException;
import com.flab.comen.member.exception.NotExistedMemberException;
import com.flab.comen.member.mapper.MemberMapper;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	MemberMapper memberMapper;

	@InjectMocks
	MemberService memberService;

	@Nested
	@DisplayName("회원가입")
	class JoinTest {
		@Test
		@DisplayName("email이 중복되었다면 DuplicatedEmailException이 발생한다.")
		void when_emailIsDuplicated_expect_throwsDuplicatedEmailException() {
			Member member = Member.of("email", "password", "name", MENTEE, ACTIVE);
			JoinRequest joinRequest = new JoinRequest("email", "password", "name", MENTEE);

			given(memberMapper.findByEmail(anyString())).willReturn(Optional.of(member));

			assertThrows(DuplicatedEmailException.class, () -> {
				memberService.join(joinRequest);
			});
		}

		@Test
		@DisplayName("등록된 회원 정보가 없다면 NotExistedMemberException이 발생한다.")
		void when_memberDoesNotExist_expect_throwsNotExistedMemberException() {
			String email = "comen@comen.com";

			given(memberMapper.findByEmail(anyString())).willThrow(NotExistedMemberException.class);

			assertThrows(NotExistedMemberException.class, () -> {
				memberService.getByEmail(email);
			});
		}
	}
}
