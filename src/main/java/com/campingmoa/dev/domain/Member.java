package com.campingmoa.dev.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String email;
    @Column(length = 100)
    private String password; // 소셜 로그인을 위해 null 허용
    @Column(nullable = false,unique = true, length=20)
    private String nickname;
    @Column(length=11)
    private String contact;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    // 생성메서드

    /**
     * 일반 회원생성
     */
    public static Member createUser(String email, String password, String nickname,
                                      String contact) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.nickname = nickname;
        member.contact = contact;
        member.authority = Authority.USER;
        member.status = MemberStatus.ACTIVE;
        return member;
    }
    /**
     * 판매자 회원생성
     */
    public static Member createSeller(String email, String password, String nickname,
                                    String contact) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.nickname = nickname;
        member.contact = contact;
        member.authority = Authority.SELLER;
        member.status = MemberStatus.ACTIVE;
        return member;
    }
    /**
     * 관리자 회원생성
     */
    public static Member createAdmin(String email, String password, String nickname,
                                      String contact) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.nickname = nickname;
        member.contact = contact;
        member.authority = Authority.ADMIN;
        member.status = MemberStatus.ACTIVE;
        return member;
    }

}
