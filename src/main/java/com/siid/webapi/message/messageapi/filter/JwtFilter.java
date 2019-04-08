package com.siid.webapi.message.messageapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtFilter extends GenericFilterBean {
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final long TOKEN_EXP = 1000 * 60 * 30;//过期时间,测试使用三十分钟
        final String base64EncodedSecretKey = "secretkey";// 密钥

        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        // Get authorization from Http request
         final String authHeader = request.getHeader("authorization");

        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {

            // Check the authorization, check if the token is started by "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }
            // Then get the JWT token from authorization
            final String token = authHeader.substring(7);

            try {
                // Use JWT parser to check if the signature is valid with the Key "secretkey"
                final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();

                // 尚未过期，则刷新令牌有效期
                if (claims.getExpiration().getTime() > new Date((System.currentTimeMillis())).getTime()) {

                    claims.setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP));
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = mapper.writeValueAsString(claims);
                    // Create New token
                    String jwtToken = Jwts.builder()
                            .setHeaderParam("type", "JWT")
                            .setPayload(payload)
//                            .setSubject(claims.getSubject())
//                            .claim("userid", claims.get("userid").toString())
//                            .setIssuedAt(claims.getIssuedAt())
//                            .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP))
                            .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                            .compact();
                    ((HttpServletResponse) res).setHeader("JWT", jwtToken);
                }

                // Add the claim to request header
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        }
    }
}
