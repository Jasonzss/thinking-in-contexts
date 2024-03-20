package com.jason.tics.common.security.filter;

import com.jason.tics.common.security.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 解析JWT的令牌为SpringSecurity的令牌用于后续验证
 * 
 * @author Jason
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    public static final String HTTP_TOKEN_HEADER = "token";
    
    public static final String CLAIMS_PERMISSION_KEY = "authorities";
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws IOException, ServletException {
        if (isJWTTokenPresent(request)) {
            //解析token中的认证信息，获取claims
            Optional<Claims> claimsOptional = parseToken(request)
                    .filter(claims -> claims.get(CLAIMS_PERMISSION_KEY) != null);
            if (claimsOptional.isPresent()) {
                //从claims中获取权限
                List<String> authoritiesList = castStringListSafely(claimsOptional.get().get(CLAIMS_PERMISSION_KEY));
                List<SimpleGrantedAuthority> authorities = authoritiesList
                        .stream().map(String::valueOf)
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                
                //构建 SpringSecurity 的验证令牌，并存于安全上下文
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(claimsOptional.get().getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * 安全的将Object对象转换为 String List
     */
    private static List<String> castStringListSafely(Object obj) {
        List<String> result = new ArrayList<>();
        if (obj instanceof Iterable) {
            for (Object o : (List<?>) obj) {
                result.add((String) o);
            }
            return result;
        }
        return null;
    }

    
    private Optional<Claims> parseToken(HttpServletRequest req) {
        String jwtToken = req.getHeader(HTTP_TOKEN_HEADER);
        try {
            return JwtUtils.parseAccessTokenClaims(jwtToken);
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            //解析token失败，输出日志
            log.error("token parse failed", e);
            return Optional.empty();
        }
    }

    /**
     * 检查请求头中是否存在 token
     */
    private boolean isJWTTokenPresent(HttpServletRequest request) {
        return request.getHeader(HTTP_TOKEN_HEADER) != null;
    }
}