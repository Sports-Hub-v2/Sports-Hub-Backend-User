package com.sportshub.user.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProfileDtos {
    @Data
    public static class CreateRequest {
        @NotNull
        private Long accountId;
        private String name;
        private String region;
        private String subRegion;  // 추가
        private String preferredPosition;
        private String skillLevel;  // 추가
        private Boolean isExPlayer;  // String → Boolean 변경
        private String phoneNumber;
        private LocalDate birthDate;
        private LocalDate activityStartDate;
        private LocalDate activityEndDate;
    }

    @Data
    public static class Response {
        private Long id;
        private Long accountId;
        private String name;
        private String region;
        private String subRegion;  // 추가
        private String preferredPosition;
        private String skillLevel;  // 추가
        private Boolean isExPlayer;  // String → Boolean 변경
        private String phoneNumber;
        private LocalDate birthDate;
        private LocalDate activityStartDate;
        private LocalDate activityEndDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Data
    public static class UpdateRequest {
        private String name;
        private String region;
        private String subRegion;  // 추가
        private String preferredPosition;
        private String skillLevel;  // 추가
        private Boolean isExPlayer;  // String → Boolean 변경
        private String phoneNumber;
        private LocalDate birthDate;
        private LocalDate activityStartDate;
        private LocalDate activityEndDate;
    }
}
