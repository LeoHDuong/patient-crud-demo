package com.springboot.patientdemo.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

public class JwtTokenUtil {

    private static final String SECRET = "eyJhbGciOiJIUzUxMiJ9.ew0KICAic3ViIjogInVzZW5hbWUiLA0KICAibmFtZSI6ICJMYW4gRHVvbmciLA0KICAiaWF0IjogMTUxNjIzOTAyMg0KfQ.9a21XVSj1msuGK9w75IR32jERX3ZJylQWmblbGukD1_hyTTH6MhO_UiVFWBQeDH7SrIIfjVeGAf9xEwyOHLhpg";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    public static String generateToken(String username) throws Exception {
        JWSSigner signer = new MACSigner(SECRET.getBytes());

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + EXPIRATION_TIME))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public static boolean validateToken(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        MACVerifier verifier = new MACVerifier(SECRET.getBytes());

        return signedJWT.verify(verifier) &&
                new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
    }

    public static String getUsernameFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }
}
