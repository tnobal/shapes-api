package program.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

@Component
//@EnableWebSecurity
public class IpAuthenticationProvider implements AuthenticationProvider {

    @Value("${application.whitelist}")
    private String whitelist;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String ip = (String) authentication.getPrincipal();
        if (!ObjectUtils.isEmpty(ip) && ip.equals(whitelist)) {
            return new PreAuthenticatedAuthenticationToken(ip, null);
        }
        throw new BadCredentialsException("IP not valid");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
