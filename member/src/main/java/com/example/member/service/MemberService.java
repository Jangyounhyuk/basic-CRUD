package com.example.member.service;

import com.example.member.dto.MemberRequestDto;
import com.example.member.dto.MemberResponseDto;
import com.example.member.entity.Member;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto save(MemberRequestDto dto) {
        Member member = new Member(dto.getName(), dto.getAge(), dto.getGender());
        Member savedMember = memberRepository.save(member);
        return new MemberResponseDto(savedMember.getId(), savedMember.getName(), savedMember.getAge(), savedMember.getGender());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();

        List<MemberResponseDto> dtos = new ArrayList<>();
        for (Member member : members) {
            MemberResponseDto dto = new MemberResponseDto(member.getId(), member.getName(), member.getAge(), member.getGender());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id 없음")
        );
        return new MemberResponseDto(member.getId(), member.getName(), member.getAge(), member.getGender());
    }

    @Transactional
    public MemberResponseDto update(Long id, MemberRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id 없음")
        );
        member.update(dto.getName(), dto.getAge(), dto.getGender());
        return new MemberResponseDto(member.getId(), member.getName(), member.getAge(), member.getGender());
    }

    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("id 없음");
        }
        memberRepository.deleteById(id);
    }
}
