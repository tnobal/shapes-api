package program.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Value("${application.apiKey}")
    private String apiKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String givenKey = (String) authentication.getPrincipal();
        System.out.println("api: " + authentication);
        if (ObjectUtils.isEmpty(givenKey)) {
            throw new InsufficientAuthenticationException("No API key in request");
        } else {
            if (apiKey.equals(givenKey)) {
                return new ApiKeyAuthenticationToken(givenKey, true);
            }
            throw new BadCredentialsException("API Key is invalid");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ApiKeyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
