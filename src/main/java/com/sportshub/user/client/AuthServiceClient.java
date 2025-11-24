package com.sportshub.user.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthServiceClient {
    private final WebClient webClient;

    @Value("${auth.service.url:http://localhost:8080}")
    private String authServiceUrl;

    /**
     * Fetch account email by accountId from auth service
     */
    public String getAccountEmail(Long accountId) {
        try {
            AccountDto account = webClient.get()
                    .uri(authServiceUrl + "/api/auth/accounts/" + accountId)
                    .retrieve()
                    .bodyToMono(AccountDto.class)
                    .timeout(Duration.ofSeconds(3))
                    .onErrorResume(e -> {
                        log.warn("Failed to fetch account email for accountId {}: {}", accountId, e.getMessage());
                        return Mono.empty();
                    })
                    .block();

            return account != null ? account.getEmail() : null;
        } catch (Exception e) {
            log.error("Error fetching account email for accountId {}", accountId, e);
            return null;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountDto {
        private Long id;
        private String email;
        private String userid;
        private String role;
        private String status;
        private Boolean emailVerified;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
