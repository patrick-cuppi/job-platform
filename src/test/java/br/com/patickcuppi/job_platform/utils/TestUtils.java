package br.com.patickcuppi.job_platform.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

  public static String objectToJSON(Object obj) {

    try {
      final ObjectMapper mapper = new ObjectMapper();

      return mapper.writeValueAsString(obj);

    } catch (Exception e) {
      throw new RuntimeException("Failed to convert object to JSON string", e);
    }
  }

  public static String generateToken(UUID companyId, String secret) {

    Algorithm algorithm = Algorithm.HMAC256(secret);

    var expiresIn = Instant.now().plus(Duration.ofHours(2));

    var token = JWT.create().withIssuer("Enterprise")
        .withExpiresAt(expiresIn)
        .withSubject(companyId.toString())
        .withClaim("roles", Arrays.asList("COMPANY"))
        .sign(algorithm);

    return token;

  }
}
