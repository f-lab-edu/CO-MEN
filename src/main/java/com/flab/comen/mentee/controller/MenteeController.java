package com.flab.comen.mentee.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.comen.global.auth.AuthMember;
import com.flab.comen.member.domain.Member;
import com.flab.comen.mentee.service.MenteeService;
import com.flab.comen.relationship.dto.RelationshipResponse;
import com.flab.comen.schedule.dto.ScheduleResponse;

@RestController
@RequestMapping("/api/v1/mentees")
public class MenteeController {
	private final MenteeService menteeService;

	public MenteeController(MenteeService menteeService) {
		this.menteeService = menteeService;
	}

	@GetMapping(value = {"", "{date}"})
	public ResponseEntity<Optional<ScheduleResponse>> getSchedule(@AuthMember Member member,
		@PathVariable(required = false) LocalDate date) {
		return ResponseEntity.ok(menteeService.getSchedule(member, date));
	}

	@GetMapping("/relationship")
	public ResponseEntity<Optional<RelationshipResponse>> getRelationship(@AuthMember Member member) {
		return ResponseEntity.ok(menteeService.getRelationship(member));
	}
}
