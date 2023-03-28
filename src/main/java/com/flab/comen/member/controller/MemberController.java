package com.flab.comen.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.comen.member.dto.JoinRequest;
import com.flab.comen.member.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping("/join")
	public ResponseEntity<Void> join(@RequestBody @Valid JoinRequest dto) {
		memberService.join(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
