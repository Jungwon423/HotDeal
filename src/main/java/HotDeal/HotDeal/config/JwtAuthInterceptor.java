package HotDeal.HotDeal.config;

import HotDeal.HotDeal.Util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.isEmpty()) {
            return true;
        }

        String jwtToken = JwtUtils.getJwtTokenFromAuthorizationHeader(authorizationHeader);
        if (!JwtUtils.validateJwtToken(jwtToken)) {
            System.out.println("invalid jwt token.");
            return true;
        }

        String userId = JwtUtils.getUserIdByJwtToken(jwtToken);
        request.setAttribute("userId", userId);
        return true;
    }
}