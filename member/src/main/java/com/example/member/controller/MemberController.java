package com.example.member.controller;

import com.example.member.dto.MemberRequestDto;
import com.example.member.dto.MemberResponseDto;
import com.example.member.entity.Member;
import com.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDto save(@RequestBody MemberRequestDto dto) {
        return memberService.save(dto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/members/{id}")
    public MemberResponseDto findOne(@PathVariable Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/members/{id}")
    public MemberResponseDto update(@PathVariable Long id, @RequestBody MemberRequestDto dto) {
        return memberService.update(id, dto);
    }

    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}
