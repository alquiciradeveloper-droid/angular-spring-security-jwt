package aalquicira.api.jwt.JWT;

import aalquicira.api.jwt.Config.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {

    private JwtTokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        JwtProperties properties = new JwtProperties();
        // Clave de al menos 256 bits conforme a OWASP
        properties.setSecretKey("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        properties.setExpirationMs(900000);
        properties.setRefreshExpirationMs(604800000);

        tokenProvider = new JwtTokenProvider(properties);
    }

    @Test
    @DisplayName("Debe generar un token válido y extraer el username correctamente")
    void generateAndValidateToken_Success() {
        // Arrange
        UserDetails userDetails = new User("admin", "password", Collections.emptyList());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // Act
        String token = tokenProvider.generateAccessToken(auth);

        // Assert
        assertNotNull(token);
        assertTrue(tokenProvider.validateToken(token));
        assertEquals("admin", tokenProvider.getUsernameFromToken(token));
    }

    @Test
    @DisplayName("Debe retornar false si el token es inválido o alterado")
    void validateToken_InvalidToken() {
        String invalidToken = "eyJhbGciOiJIUzI1NiJ9.invalidpayload.signature";
        assertFalse(tokenProvider.validateToken(invalidToken));
    }
}
