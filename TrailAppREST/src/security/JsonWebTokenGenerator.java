package security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JsonWebTokenGenerator {
	@Autowired
	SecretKeyGenerator keyGen;

	public String generateUserJwt(User user) {
		// Expiration : Set the JWT to expire in 24 hours
		Date now = new Date();
		Date tomorrow = new Date(now.getTime() + (1000 * 60 * 60 * 24));

		// Value : Create a Hash Map
		Map<String, Object> userJson = new HashMap<>();
		userJson.put("user_id", user.getId());
		userJson.put("email", user.getEmail());

		// Use Jwts to create an encoded JWT as a String
		String jwt = "";
		try {
			jwt = Jwts.builder()
			          .setSubject("user")
			          .setClaims(userJson)
			          .setExpiration(tomorrow)
			          .signWith(SignatureAlgorithm.HS512, keyGen.getSecretKey())
			          .compact();
		} 
		catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		return jwt;
	}

}