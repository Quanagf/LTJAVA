package test.blood.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import test.blood.donation.model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
