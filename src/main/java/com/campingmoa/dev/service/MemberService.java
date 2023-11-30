package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Member;
import com.campingmoa.dev.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * 일반 회원가입
     */
    @Transactional
    public Long join(String email, String password, String nickname,
                     String contact){
        Member member = Member.createUser(email, password, nickname, contact,passwordEncoder);
        validateDuplicateEmail(member);
        validateDuplicateNickname(member);
        memberRepository.save(member);
        return member.getId();
    }
    /**
     * 판매자 회원가입
     */
    @Transactional
    public Long joinSeller(String email, String password, String nickname,
                     String contact){
        Member member = Member.createSeller(email, password, nickname, contact);
        validateDuplicateEmail(member);
        validateDuplicateNickname(member);
        memberRepository.save(member);
        return member.getId();
    }
    /**
     * 관리자 회원가입
     */
    @Transactional
    public Long joinAdmin(String email, String password, String nickname,
                           String contact){
        Member member = Member.createAdmin(email, password, nickname, contact);
        validateDuplicateEmail(member);
        validateDuplicateNickname(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateNickname(Member member) {
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    private void validateDuplicateEmail(Member member){
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() { return memberRepository.findAll();}

    public Member findOne(Long memberId){return memberRepository.findOne(memberId);}


}
