package aalquicira.api.jwt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import aalquicira.api.jwt.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
