package com.siid.webapi.message.messageapi.filter;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT验证过滤器,检查是否有Token,Token是否符合规范
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(
            AuthenticationManager authenticationManager,
            JWTUtils jwtUtils) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.addHeader("WWW-authenticate", "Bearer "+"Missing or invalid Authorization header");

            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private JWTUtils jwtUtils;

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        if (token != null) {
            // parse the token.
            try {
                final Claims claims = jwtUtils.parseTokenClaims(token);

                request.setAttribute("claims", claims);
                String user = (String) claims.get("userid");

                String jwtToken = jwtUtils.renewToken(claims);
                response.setHeader("JWT", jwtToken);
                response.addHeader("Authorization", "Bearer " + jwtToken);

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch(Exception ex) {
                response.addHeader("WWW-authenticate", "Bearer "+ex.getLocalizedMessage());
            }
            return null;
        }
        return null;
    }

}
