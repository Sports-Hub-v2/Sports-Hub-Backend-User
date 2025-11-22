package com.sportshub.user.web;

import com.sportshub.user.domain.Profile;
import com.sportshub.user.service.ProfileService;
import com.sportshub.user.web.dto.ProfileDtos.CreateRequest;
import com.sportshub.user.web.dto.ProfileDtos.Response;
import com.sportshub.user.web.dto.ProfileDtos.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/profiles")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@Validated @RequestBody CreateRequest req) {
        Profile p = new Profile();
        BeanUtils.copyProperties(req, p);
        return toResponse(profileService.create(p));
    }

    @GetMapping("/profiles/{id}")
    public Response get(@PathVariable Long id) {
        return toResponse(profileService.get(id));
    }

    @GetMapping("/profiles/by-account/{accountId}")
    public Response getByAccount(@PathVariable Long accountId) {
        return toResponse(profileService.getByAccountId(accountId));
    }

    @PatchMapping("/profiles/{id}")
    public Response update(@PathVariable Long id, @RequestBody UpdateRequest req) {
        Profile patch = new Profile();
        BeanUtils.copyProperties(req, patch);
        return toResponse(profileService.update(id, patch));
    }

    // 관리자용 - 전체 사용자 목록 조회
    @GetMapping("/profiles")
    public List<Response> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Profile> profiles = profileService.findAll(pageable);
        return profiles.getContent().stream()
                .map(this::toResponse)
                .toList();
    }

    private Response toResponse(Profile p) {
        Response r = new Response();
        BeanUtils.copyProperties(p, r);
        return r;
    }
}
