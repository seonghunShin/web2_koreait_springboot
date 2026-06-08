package com.study.koreait.controller;

import com.study.koreait.dto.req.SignInReqDto;
import com.study.koreait.dto.req.SignUpReqDto;
import com.study.koreait.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 회원가입 signup
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpReqDto dto) {
        authService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("계정생성완료.");
    }

    // 로그인 signIn
    // HTTPS를 쓰면 body는 암호화되서 가로채도 볼 수 없음
    // GET 요청의 url은 암호화되지 않기 때문에 가로채서 볼 수 있음.
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInReqDto dto) {
        return ResponseEntity.ok(authService.signIn(dto));
    }

    @GetMapping("/me-1")
    public ResponseEntity<?> meOne(){
        // 필터를 통해 저장된 Authentication을 내가 직접 꺼내오는 방법
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/me-2")
    public ResponseEntity<?> meTwo(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(userId);
    }

    // 리다이렉트된 요청을 받아주는 콜백 컨트롤러 메서드
    // http://localhost:8080/oauth2/authorization/google
    @GetMapping("/oauth2")
    public ResponseEntity<?> oAuth2SignIn(
            @RequestParam String provider,
            @RequestParam String providerUserId,
            @RequestParam String email
    ) {
        return ResponseEntity.ok(authService.oAuth2SignIn(provider, providerUserId, email));
    }

}
