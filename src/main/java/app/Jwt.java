package app;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;  
import javax.xml.bind.DatatypeConverter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class Jwt {
		
		private final static String id = "01234567890";
		private final static String iss = "um usuario qualquer";
		private final static String sub = "uma autorizadora qualquer";
		private final static String key = "9d6f407b-ab09-4ffa-9672-de452905bf4e";
		static long exp = System.currentTimeMillis() + 300000; // EQ. 5 MIN
		static long ati = System.currentTimeMillis();
		
		
		final static String createJWT(String id, String issuer, String subject, long ttlMillis) {

			long nowMillis = System.currentTimeMillis();
		    Date now = new Date(nowMillis);

			
		    //The JWT signature algorithm we will be using to sign the token
		    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		    //We will sign our JWT with our ApiKey secret
		    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
		    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		    //Let's set the JWT Claims
		    JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);

		    //if it has been specified, let's add the expiration
		    if (ttlMillis >= 0) {
		    long expMillis = nowMillis + 3000;
		        Date exp = new Date(expMillis);
		        builder.setExpiration(exp);
		    }

		    //Builds the JWT and serializes it to a compact, URL-safe string
		    System.out.println(builder.compact());
		    return builder.compact();
		}


		//Sample method to validate and read the JWT
		private static void parseJWT(String jwt) {

		    //This line will throw an exception if it is not a signed JWS (as expected)
		    Claims claims = Jwts.parser()         
		       .setSigningKey(DatatypeConverter.parseBase64Binary(key))
		       .parseClaimsJws(jwt).getBody();
		    System.out.println("ID: " + claims.getId());
		    System.out.println("Subject: " + claims.getSubject());
		    System.out.println("Issuer: " + claims.getIssuer());
		    System.out.println("Expiration: " + claims.getExpiration());
		}
	    
//		public static void main(String[] args){
//			parseJWT(createJWT(id, iss, sub, exp));
//		}
}
