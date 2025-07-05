package test.blood.donation.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import test.blood.donation.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(Long userId);
}
