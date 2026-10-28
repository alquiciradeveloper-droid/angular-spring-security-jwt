package aalquicira.api.jwt.Service.Impl;

import aalquicira.api.jwt.Dto.AuthRequest;
import aalquicira.api.jwt.Dto.AuthResponse;
import aalquicira.api.jwt.Dto.RefreshTokenRequest;
import aalquicira.api.jwt.JWT.JwtTokenProvider;
import aalquicira.api.jwt.Service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String accessToken = tokenProvider.generateAccessToken(authentication);
        String refreshToken = tokenProvider.generateRefreshToken(authentication);

        return new AuthResponse(accessToken, "Bearer", refreshToken);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new IllegalArgumentException("Refresh token inválido o expirado");
        }

        String username = tokenProvider.getUsernameFromToken(request.getRefreshToken());
        String newAccessToken = tokenProvider.generateAccessTokenFromUsername(username);

        return new AuthResponse(newAccessToken, "Bearer", request.getRefreshToken());
    }
}
