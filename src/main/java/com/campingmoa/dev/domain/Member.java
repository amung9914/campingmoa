package com.campingmoa.dev.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    @Column(length = 100)
    private String password; // 소셜 로그인을 위해 null 허용
    @Column(nullable = false, length=20)
    private String nickname;
    @Column(nullable = false,unique = true,length = 50)
    private String email;
    @Column(length=11)
    private String contact;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

}
