package pet.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {  
//    private final JwtSettings jwtSettings;
//
//    @Autowired
//    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
//        this.jwtSettings = jwtSettings;
//    }
	  static final long EXPIRATIONTIME = 864_000_000; // 10 days
	  static final String SECRET = "ThisIsASecret";
	  static final String TOKEN_PREFIX = "Bearer";
	  static final String HEADER_STRING = "Authorization";
    @Override
    public Authentication authenticate(Authentication authentication) throws org.springframework.security.core.AuthenticationException {
//        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
//
//        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
//        String subject = jwsClaims.getBody().getSubject();
//        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
//        List<GrantedAuthority> authorities = scopes.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority))
//                .collect(Collectors.toList());
//
//        UserContext context = UserContext.create(subject, authorities);
    	 String token = (String) authentication.getCredentials();
    	    if (token != null) {
    	      // parse the token.
    	      String user = Jwts.parser()
    	          .setSigningKey(SECRET)
    	          .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
    	          .getBody()
    	          .getSubject();
    	 List<String> scopes = Jwts.parser()
                 .setSigningKey(SECRET)
                 .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                 .getBody().get("scopes", List.class);
         List<GrantedAuthority> authorities = scopes.stream()
                 .map(authority -> new SimpleGrantedAuthority(authority))
                 .collect(Collectors.toList());
         
         return user != null ?
             new UsernamePasswordAuthenticationToken(user, null, authorities) :
             null;}
    	    return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}