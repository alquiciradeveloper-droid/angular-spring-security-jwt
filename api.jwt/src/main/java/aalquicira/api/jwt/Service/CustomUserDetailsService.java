package aalquicira.api.jwt.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    // Hereda loadUserByUsername(String username) de Spring Security
}
