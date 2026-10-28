package aalquicira.api.jwt.Service;

import aalquicira.api.jwt.Dto.AuthRequest;
import aalquicira.api.jwt.Dto.AuthResponse;
import aalquicira.api.jwt.Dto.RefreshTokenRequest;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
}
