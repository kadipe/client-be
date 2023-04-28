package com.kadipe.fw;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kadipe.oauth.flow.exception.SecretKeyInvalidException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

public class SecurityUtil {

    private final static String ISSUER = "Kadipe :: Identity to Data";

    public static String crypto(String phrase) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(phrase.getBytes());
        byte[] arrCrypto = messageDigest.digest();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrCrypto.length; i++) {
            stringBuilder.append(Integer.toString((arrCrypto[i] & 0xff) + 0x100, 16).substring(1));
        }

        return stringBuilder.toString();
    }

    public static String generateJWT(String keyPhrase, String idUser, String audience) {

        Algorithm algorithm = generateAlgorithm(keyPhrase);

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(idUser)
                .withAudience(audience)
                .withExpiresAt(Date.from(DateHelp.getGMTFromEuropeDublin().plusHours(2).atZone(ZoneId.of(DateHelp.TZ_GMT)).toInstant()))
                .sign(algorithm);
    }

    private static Algorithm generateAlgorithm(String keyPhrase) {

        return Algorithm.HMAC256(keyPhrase);
    }

    private static JWTVerifier generateVirifier(String keyPhrase) {

        Algorithm algorithm = generateAlgorithm(keyPhrase);

        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
    }

    public static DecodedJWT verifyJWT(String keyPhrase, String jwtToken) throws JWTVerificationException {

        return generateVirifier(keyPhrase).verify(jwtToken);
    }

    public static String[] getDecodeSecret(String basicAuthorization) throws SecretKeyInvalidException {
        String[] decodeSecret;
        try {
            decodeSecret = new String(Base64.getDecoder().decode(basicAuthorization.substring(6))).split(":");
        } catch (IllegalArgumentException e) {
            throw new SecretKeyInvalidException("Invalid client and secret key");
        }
        return decodeSecret;
    }

}
