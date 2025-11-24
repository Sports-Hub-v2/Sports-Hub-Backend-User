package com.sportshub.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false, unique = true)
    private Long accountId;

    private String name;
    private String region;

    @Column(name = "sub_region")
    private String subRegion;  // 추가

    @Column(name = "preferred_position")
    private String preferredPosition;

    @Column(name = "skill_level")
    private String skillLevel;  // 추가

    @Column(name = "is_ex_player")
    private Boolean isExPlayer;  // String → Boolean 변경

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    // 신체 정보
    private Integer height; // cm 단위
    private Integer weight; // kg 단위

    // 조기축구 특화 정보
    @Column(name = "manner_score")
    private Double mannerScore = 36.5; // 기본 매너온도 36.5도

    @Column(name = "no_show_count")
    private Integer noShowCount = 0; // 노쇼 횟수

    @Column(name = "consecutive_attendance")
    private Integer consecutiveAttendance = 0; // 연속 출석

    // 활동 기간
    @Column(name = "activity_start_date")
    private LocalDate activityStartDate;

    @Column(name = "activity_end_date")
    private LocalDate activityEndDate;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}

