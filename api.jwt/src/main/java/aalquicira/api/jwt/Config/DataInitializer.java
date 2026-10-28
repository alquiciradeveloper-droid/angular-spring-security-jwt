package aalquicira.api.jwt.Config; 

import aalquicira.api.jwt.Entity.Rol;
import aalquicira.api.jwt.Entity.User;
import aalquicira.api.jwt.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User(
                "admin",
                passwordEncoder.encode("Admin123*"),
                Set.of(Rol.ROLE_ADMIN, Rol.ROLE_USER) 
            );
            userRepository.save(admin);
        }
    }
}
