package com.siid.webapi.message.messageapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.Date;

/**
 * Jwt工具类，用于统一token生成、验证过程的参数
 */
@Component
public class JWTUtils {
    /**
     * 过期时间,以秒为单位
     */
    @Value("${jwt.token.expirationSecond}")
    private long expirationSecond;

    /**
     * 密钥
     */
    @Value("${jwt.token.secretKey}")
    private String secretKey;

    /**
     * 签名算法
     */
    @Value("${jwt.token.signatureAlgorithm}")
    private SignatureAlgorithm signatureAlgorithm;

    private String base64EncodedSecretKey = null;

    private String getBase64EncodedSecretKey() {
        if (base64EncodedSecretKey == null) {
            byte[] completeKey = DigestUtils.md5Digest(secretKey.getBytes());
            base64EncodedSecretKey = Base64Utils.encodeToString(completeKey);
        }
        return base64EncodedSecretKey;
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public Claims parseTokenClaims(String token) {
        return Jwts.parser().setSigningKey(getBase64EncodedSecretKey()).parseClaimsJws(token).getBody();
    }

    /**
     * 更新token
     * @param claims
     * @return
     * @throws IOException
     */
    public String renewToken(Claims claims) throws IOException {
        Date expirationDate = new Date(System.currentTimeMillis() + expirationSecond * 1000);

        claims.setExpiration(expirationDate);
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(claims);
        String jwtToken = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setPayload(payload)
                .signWith(signatureAlgorithm, getBase64EncodedSecretKey())
                .compact();
        return jwtToken;
    }

    /**
     * 创建token
     * @param userId
     * @param userName
     * @param customerId
     * @return
     */
    public String buildToken(String userId, String userName, Integer customerId) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(userName)
                .claim("userid", userId)
                .claim("customerid", customerId);

        //生成token
        String jwtToken = jwtBuilder
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationSecond * 1000))
                .signWith(signatureAlgorithm, getBase64EncodedSecretKey())
                .compact();

        return jwtToken;
    }
}
