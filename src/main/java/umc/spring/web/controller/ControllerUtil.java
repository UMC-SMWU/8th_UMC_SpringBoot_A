package umc.spring.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import umc.spring.config.security.jwt.JwtTokenProvider;

@Component
@RequiredArgsConstructor
public class ControllerUtil {

    private JwtTokenProvider jwtTokenProvider;

    public String findMemberByEmail(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        return authentication.getName();
    }
}
